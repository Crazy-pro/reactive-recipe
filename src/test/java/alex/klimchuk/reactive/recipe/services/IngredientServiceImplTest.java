package alex.klimchuk.reactive.recipe.services;

import alex.klimchuk.reactive.recipe.converters.*;
import alex.klimchuk.reactive.recipe.domain.*;
import alex.klimchuk.reactive.recipe.dto.IngredientDto;
import alex.klimchuk.reactive.recipe.repositories.reactive.RecipeReactiveRepository;
import alex.klimchuk.reactive.recipe.repositories.reactive.UnitOfMeasureReactiveRepository;
import alex.klimchuk.reactive.recipe.services.impl.IngredientServiceImpl;
import org.junit.*;
import org.mockito.*;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class IngredientServiceImplTest {

    private final IngredientToIngredientDto ingredientToIngredientDto;
    private final IngredientDtoToIngredient ingredientDtoToIngredient;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientDto = new IngredientToIngredientDto(new UnitOfMeasureToUnitOfMeasureDto());
        this.ingredientDtoToIngredient = new IngredientDtoToIngredient(new UnitOfMeasureDtoToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientDto, ingredientDtoToIngredient,
                recipeReactiveRepository, unitOfMeasureReactiveRepository);
    }

    @Test
    public void testFindByRecipeIdAndRecipeIdHappyPath() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("1");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        IngredientDto ingredientDto = ingredientService.findByRecipeIdAndIngredientId("1","3").block();

        assertEquals("3", ingredientDto.getId());
        assertEquals("1", ingredientDto.getRecipeId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
    }

    @Test
    public void testSaveRecipeDto() {
        IngredientDto ingredientDtoMock = new IngredientDto();
        ingredientDtoMock.setId("3");
        ingredientDtoMock.setRecipeId("2");

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId("3");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(new Recipe()));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(savedRecipe));

        IngredientDto savedDto = ingredientService.saveIngredientDto(ingredientDtoMock).block();

        assertEquals("3", savedDto.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testDeleteById() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId("3");

        Recipe recipe = new Recipe();
        recipe.addIngredient(ingredient);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(new Recipe()));

        ingredientService.deleteById("1","3").block();

        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }

}
