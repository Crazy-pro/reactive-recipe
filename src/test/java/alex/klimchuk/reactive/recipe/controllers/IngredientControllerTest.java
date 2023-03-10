package alex.klimchuk.reactive.recipe.controllers;

import alex.klimchuk.reactive.recipe.dto.IngredientDto;
import alex.klimchuk.reactive.recipe.dto.RecipeDto;
import alex.klimchuk.reactive.recipe.dto.UnitOfMeasureDto;
import alex.klimchuk.reactive.recipe.services.IngredientService;
import alex.klimchuk.reactive.recipe.services.RecipeService;
import alex.klimchuk.reactive.recipe.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Ignore
public class IngredientControllerTest {

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    RecipeService recipeService;

    IngredientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        controller = new IngredientController(ingredientService, recipeService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        RecipeDto recipeDtoMock = new RecipeDto();

        when(recipeService.findDtoById(anyString())).thenReturn(Mono.just(recipeDtoMock));

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findDtoById(anyString());
    }

    @Test
    public void testShowIngredient() throws Exception {
        IngredientDto ingredientDtoMock = new IngredientDto();

        when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString()))
                .thenReturn(Mono.just(ingredientDtoMock));

        mockMvc.perform(get("/recipe/1/ingredients/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void testNewingredientsForm() throws Exception {
        RecipeDto recipeDtoMock = new RecipeDto();
        recipeDtoMock.setId("1");

        when(recipeService.findDtoById(anyString())).thenReturn(Mono.just(recipeDtoMock));
        when(unitOfMeasureService.findAll()).thenReturn(Flux.just(new UnitOfMeasureDto()));

        mockMvc.perform(get("/recipe/1/ingredients/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/ingredientsForm"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

        verify(recipeService, times(1)).findDtoById(anyString());
    }

    @Test
    public void testUpdateingredientsForm() throws Exception {
        IngredientDto ingredientDtoMock = new IngredientDto();

        when(ingredientService.findByRecipeIdAndIngredientId(anyString(), anyString()))
                .thenReturn(Mono.just(ingredientDtoMock));
        when(unitOfMeasureService.findAll()).thenReturn(Flux.just(new UnitOfMeasureDto()));

        mockMvc.perform(get("/recipe/1/ingredients/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/ingredientsForm"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        IngredientDto ingredientDtoMock = new IngredientDto();
        ingredientDtoMock.setId("3");
        ingredientDtoMock.setRecipeId("2");

        when(ingredientService.saveIngredientDto(any())).thenReturn(Mono.just(ingredientDtoMock));

        mockMvc.perform(post("/recipe/2/ingredients")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients/3/show"));
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        when(ingredientService.deleteById(anyString(),anyString())).thenReturn(Mono.empty());

        mockMvc.perform(get("/recipe/2/ingredients/3/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients"));

        verify(ingredientService, times(1)).deleteById(anyString(), anyString());
    }

}
