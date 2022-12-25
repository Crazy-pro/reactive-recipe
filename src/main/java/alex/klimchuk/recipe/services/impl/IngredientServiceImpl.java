package alex.klimchuk.recipe.services.impl;

import alex.klimchuk.recipe.dto.IngredientDto;
import alex.klimchuk.recipe.converters.IngredientDtoToIngredient;
import alex.klimchuk.recipe.converters.IngredientToIngredientDto;
import alex.klimchuk.recipe.domain.Ingredient;
import alex.klimchuk.recipe.domain.Recipe;
import alex.klimchuk.recipe.repositories.RecipeRepository;
import alex.klimchuk.recipe.repositories.UnitOfMeasureRepository;
import alex.klimchuk.recipe.repositories.reactive.RecipeReactiveRepository;
import alex.klimchuk.recipe.repositories.reactive.UnitOfMeasureReactiveRepository;
import alex.klimchuk.recipe.services.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientDto ingredientToIngredientDto;
    private final IngredientDtoToIngredient ingredientDtoToIngredient;
    private final RecipeReactiveRepository recipeReactiveRepository;
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    public IngredientServiceImpl(IngredientToIngredientDto ingredientToIngredientDto,
                                 IngredientDtoToIngredient ingredientDtoToIngredient,
                                 RecipeReactiveRepository recipeReactiveRepository,
                                 UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository) {
        this.ingredientToIngredientDto = ingredientToIngredientDto;
        this.ingredientDtoToIngredient = ingredientDtoToIngredient;
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
    }

    @Override
    public Mono<IngredientDto> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        return recipeReactiveRepository.findById(recipeId)
                .flatMapIterable(Recipe::getIngredients)
                .filter(ingredient -> ingredient.getId().equalsIgnoreCase(ingredientId))
                .single()
                .map(ingredient -> {
                    IngredientDto ingredientDto = ingredientToIngredientDto.convert(ingredient);
                    ingredientDto.setRecipeId(recipeId);
                    return ingredientDto;
                });
    }

    @Override
    public Mono<IngredientDto> saveIngredientDto(IngredientDto ingredientDto) {
        Recipe recipe = recipeReactiveRepository.findById(ingredientDto.getRecipeId()).block();

        if (Objects.isNull(recipe)) {
            //todo toss error if not found!
            log.error("Recipe not found for id: " + ingredientDto.getRecipeId());
            return Mono.just(new IngredientDto());
        } else {
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientDto.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientDto.getDescription());
                ingredientFound.setAmount(ingredientDto.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureReactiveRepository
                        .findById(ingredientDto.getUnitOfMeasure().getId()).block());
                if (Objects.isNull(ingredientFound.getUnitOfMeasure()) ) {
                    new RuntimeException("UnitOfMeasure Not Found!");
                }
            } else {
                Ingredient ingredient = ingredientDtoToIngredient.convert(ingredientDto);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeReactiveRepository.save(recipe).block();

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientDto.getId()))
                    .findFirst();

            if (savedIngredientOptional.isEmpty()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientDto.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientDto.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(ingredientDto.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            // TODO check for fail
            IngredientDto ingredientDtoSaved = ingredientToIngredientDto.convert(savedIngredientOptional.get());
            ingredientDtoSaved.setRecipeId(recipe.getId());

            return Mono.just(ingredientDtoSaved);
        }
    }

    @Override
    public Mono<Void> deleteById(String recipeId, String id) {
        log.debug("Deleting ingredient: " + recipeId + ":" + id);

       Recipe recipe = recipeReactiveRepository.findById(recipeId).block();

        if (Objects.nonNull(recipe)) {
            log.debug("Found recipe");

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(id))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                log.debug("Found Ingredient");

                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeReactiveRepository.save(recipe).block();
            }
        } else {
            log.debug("Recipe Id Not found. Id: " + recipeId);
        }
        return Mono.empty();
    }

}
