package alex.klimchuk.reactive.recipe.services.impl;

import alex.klimchuk.reactive.recipe.domain.Recipe;
import alex.klimchuk.reactive.recipe.dto.RecipeDto;
import alex.klimchuk.reactive.recipe.converters.RecipeDtoToRecipe;
import alex.klimchuk.reactive.recipe.converters.RecipeToRecipeDto;
import alex.klimchuk.reactive.recipe.repositories.reactive.RecipeReactiveRepository;
import alex.klimchuk.reactive.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepository recipeReactiveRepository;

    private final RecipeDtoToRecipe recipeDtoToRecipe;

    private final RecipeToRecipeDto recipeToRecipeDto;

    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeDtoToRecipe recipeDtoToRecipe,
                             RecipeToRecipeDto recipeToRecipeDto) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeDtoToRecipe = recipeDtoToRecipe;
        this.recipeToRecipeDto = recipeToRecipeDto;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        log.debug("Executed method get Recipes!");
        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        return recipeReactiveRepository.findById(id);
    }

    @Override
    public Mono<RecipeDto> findDtoById(String id) {
        return recipeReactiveRepository.findById(id)
                .map(recipe -> {
                    RecipeDto recipeDto = recipeToRecipeDto.convert(recipe);
                    recipeDto.getIngredients()
                            .forEach(rc -> rc.setRecipeId(recipeDto.getId()));
                    return recipeDto;
                });
    }

    @Override
    public Mono<RecipeDto> saveRecipeDto(RecipeDto recipeDto) {
        return recipeReactiveRepository.save(recipeDtoToRecipe.convert(recipeDto))
                .mapNotNull(recipeToRecipeDto::convert);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        recipeReactiveRepository.deleteById(id).then();
        return Mono.empty();
    }

}
