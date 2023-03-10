package alex.klimchuk.reactive.recipe.converters;

import alex.klimchuk.reactive.recipe.domain.*;
import alex.klimchuk.reactive.recipe.dto.RecipeDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class RecipeToRecipeDtoTest {

    public static final String RECIPE_ID = "1";
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final String CATEGORY_ID_1 = "1";
    public static final String CATEGORY_ID_2 = "2";
    public static final String INGREDIENT_ID_1 = "3";
    public static final String INGREDIENT_ID_2 = "4";
    public static final String NOTES_ID ="9";
    RecipeToRecipeDto converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeDto(
                new IngredientToIngredientDto(new UnitOfMeasureToUnitOfMeasureDto()),
                new CategoryToCategoryDto(),
                new NotesToNotesDto());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void testConvert() {
        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        Category category = new Category();
        category.setId(CATEGORY_ID_1);

        Category category2 = new Category();
        category2.setId(CATEGORY_ID_2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);

        Recipe recipe = Recipe.builder()
                .id(RECIPE_ID)
                .cookTime(COOK_TIME)
                .prepTime(PREP_TIME)
                .description(DESCRIPTION)
                .difficulty(DIFFICULTY)
                .directions(DIRECTIONS)
                .servings(SERVINGS)
                .source(SOURCE)
                .url(URL)
                .notes(notes)
                .categories(new HashSet<>(List.of(category, category2)))
                .ingredients(new HashSet<>(List.of(ingredient, ingredient2)))
                .build();

        RecipeDto recipeDto = converter.convert(recipe);

        assertNotNull(recipeDto);
        assertEquals(RECIPE_ID, recipeDto.getId());
        assertEquals(COOK_TIME, recipeDto.getCookTime());
        assertEquals(PREP_TIME, recipeDto.getPrepTime());
        assertEquals(DESCRIPTION, recipeDto.getDescription());
        Assert.assertEquals(DIFFICULTY, recipeDto.getDifficulty());
        assertEquals(DIRECTIONS, recipeDto.getDirections());
        assertEquals(SERVINGS, recipeDto.getServings());
        assertEquals(SOURCE, recipeDto.getSource());
        assertEquals(URL, recipeDto.getUrl());
        assertEquals(NOTES_ID, recipeDto.getNotes().getId());
        assertEquals(2, recipeDto.getCategories().size());
        assertEquals(2, recipeDto.getIngredients().size());
    }

}
