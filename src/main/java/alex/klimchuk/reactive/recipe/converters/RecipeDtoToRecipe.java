package alex.klimchuk.reactive.recipe.converters;

import alex.klimchuk.reactive.recipe.domain.Recipe;
import alex.klimchuk.reactive.recipe.dto.RecipeDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Component
public class RecipeDtoToRecipe implements Converter<RecipeDto, Recipe> {

    private final IngredientDtoToIngredient ingredientConverter;
    private final CategoryDtoToCategory categoryConverter;
    private final NotesDtoToNotes notesConverter;

    public RecipeDtoToRecipe(IngredientDtoToIngredient ingredientConverter, CategoryDtoToCategory categoryConverter,
                             NotesDtoToNotes notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public Recipe convert(RecipeDto recipeDto) {
        Recipe recipe = Recipe.builder()
                .id(recipeDto.getId())
                .cookTime(recipeDto.getCookTime())
                .prepTime(recipeDto.getPrepTime())
                .description(recipeDto.getDescription())
                .difficulty(recipeDto.getDifficulty())
                .directions(recipeDto.getDirections())
                .servings(recipeDto.getServings())
                .source(recipeDto.getSource())
                .image(recipeDto.getImage())
                .url(recipeDto.getUrl())
                .notes(notesConverter.convert(recipeDto.getNotes()))
                .build();

        boolean isHasCategories = Objects.nonNull(recipeDto.getCategories()) && !recipeDto.getCategories().isEmpty();
        boolean isHasIngredients = Objects.nonNull(recipeDto.getIngredients()) && !recipeDto.getIngredients().isEmpty();

        if (isHasCategories) {
            recipeDto.getCategories()
                    .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if (isHasIngredients) {
            recipeDto.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }

}
