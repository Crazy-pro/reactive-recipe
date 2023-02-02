package alex.klimchuk.reactive.recipe.converters;

import alex.klimchuk.reactive.recipe.domain.Difficulty;
import alex.klimchuk.reactive.recipe.domain.Recipe;
import alex.klimchuk.reactive.recipe.dto.CategoryDto;
import alex.klimchuk.reactive.recipe.dto.IngredientDto;
import alex.klimchuk.reactive.recipe.dto.NotesDto;
import alex.klimchuk.reactive.recipe.dto.RecipeDto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class RecipeDtoToRecipeTest {

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
    public static final String CATEGORY_ID2 = "2";
    public static final String INGREDIENT_ID_1 = "3";
    public static final String INGREDIENT_ID_2 = "4";
    public static final String NOTES_ID = "9";

    RecipeDtoToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeDtoToRecipe(
                new IngredientDtoToIngredient(new UnitOfMeasureDtoToUnitOfMeasure()),
                new CategoryDtoToCategory(),
                new NotesDtoToNotes());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeDto()));
    }

    @Test
    public void testConvert() {
        NotesDto notes = new NotesDto();
        notes.setId(NOTES_ID);

        CategoryDto category = new CategoryDto();
        category.setId(CATEGORY_ID_1);

        CategoryDto category2 = new CategoryDto();
        category2.setId(CATEGORY_ID2);

        IngredientDto ingredient = new IngredientDto();
        ingredient.setId(INGREDIENT_ID_1);

        IngredientDto ingredient2 = new IngredientDto();
        ingredient2.setId(INGREDIENT_ID_2);

        RecipeDto recipeDto = RecipeDto.builder()
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
                .categories(List.of(category, category2))
                .ingredients(List.of(ingredient, ingredient2))
                .build();

        Recipe recipe = converter.convert(recipeDto);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }

}
