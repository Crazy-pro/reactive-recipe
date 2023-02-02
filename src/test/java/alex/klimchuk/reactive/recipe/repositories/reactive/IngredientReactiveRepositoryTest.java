package alex.klimchuk.reactive.recipe.repositories.reactive;

import alex.klimchuk.reactive.recipe.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@DataMongoTest
@RunWith(SpringRunner.class)
public class IngredientReactiveRepositoryTest {

    @Autowired
    private IngredientReactiveRepository ingredientReactiveRepository;

    @Before
    public void setUp() {
        ingredientReactiveRepository.deleteAll().block();
    }

    @Test
    public void testIngredientSave(){
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription("Cola");

        ingredientReactiveRepository.save(ingredient).block();

        Long count = ingredientReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

}
