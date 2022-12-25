package alex.klimchuk.recipe.services;

import alex.klimchuk.recipe.converters.RecipeDtoToRecipe;
import alex.klimchuk.recipe.converters.RecipeToRecipeDto;
import alex.klimchuk.recipe.domain.Recipe;
import alex.klimchuk.recipe.dto.RecipeDto;
import alex.klimchuk.recipe.repositories.RecipeRepository;
import alex.klimchuk.recipe.repositories.reactive.RecipeReactiveRepository;
import alex.klimchuk.recipe.services.impl.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    RecipeToRecipeDto recipeToRecipeDto;

    @Mock
    RecipeDtoToRecipe recipeDtoToRecipe;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeReactiveRepository, recipeDtoToRecipe, recipeToRecipeDto);
    }

    @Test
    public void testGetRecipes() {
        Recipe recipe = new Recipe();

        when(recipeService.getRecipes()).thenReturn(Flux.just(recipe));

        List<Recipe> recipes = recipeService.getRecipes().collectList().block();

        assertEquals(1,recipes.size());
        verify(recipeReactiveRepository, times(1)).findAll();
        verify(recipeReactiveRepository, never()).findById(anyString());
    }

    @Test
    public void testGetRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        Recipe recipeReturned = recipeService.findById("1").block();

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();
    }

    @Test
    public void testGetRecipeDtoById() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        RecipeDto recipeDtoMock = new RecipeDto();
        recipeDtoMock.setId("1");

        when(recipeToRecipeDto.convert(any())).thenReturn(recipeDtoMock);

        RecipeDto recipeDto = recipeService.findDtoById("1").block();

        assertNotNull("Null recipe returned", recipeDto);
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();
    }

    @Test
    public void testDeleteById() {
        String idToDelete = "2";

        when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty());

        recipeService.deleteById(idToDelete).block();

        verify(recipeReactiveRepository, times(1)).deleteById(anyString());
    }

}
