package alex.klimchuk.reactive.recipe.services;

import alex.klimchuk.reactive.recipe.converters.RecipeDtoToRecipe;
import alex.klimchuk.reactive.recipe.converters.RecipeToRecipeDto;
import alex.klimchuk.reactive.recipe.domain.Recipe;
import alex.klimchuk.reactive.recipe.dto.RecipeDto;
import alex.klimchuk.reactive.recipe.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeDtoToRecipe recipeDtoToRecipe;

    @Autowired
    RecipeToRecipeDto recipeToRecipeDto;

    @Test
    public void testSaveRecipeDto() {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();

        RecipeDto recipeDtoMock = recipeToRecipeDto.convert(testRecipe);

        assert Objects.nonNull(recipeDtoMock);
        recipeDtoMock.setDescription(NEW_DESCRIPTION);

        RecipeDto savedRecipeDto = recipeService.saveRecipeDto(recipeDtoMock).block();

        assertEquals(NEW_DESCRIPTION, savedRecipeDto.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeDto.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeDto.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeDto.getIngredients().size());
    }

}
