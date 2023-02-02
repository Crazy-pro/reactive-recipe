package alex.klimchuk.reactive.recipe.repositories;

import alex.klimchuk.reactive.recipe.bootstrap.RecipeBootstrap;
import alex.klimchuk.reactive.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@DataMongoTest
@RunWith(SpringRunner.class)
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
        categoryRepository.deleteAll();

        RecipeBootstrap recipeBootstrap = new RecipeBootstrap(unitOfMeasureRepository, categoryRepository, recipeRepository);

        recipeBootstrap.onApplicationEvent(null);
    }

    @Test
    //@DirtiesContext
    public void testFindByDescription() {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("TeaSpoon");

        assertEquals("TeaSpoon", uomOptional.get().getDescription());
    }

    @Test
    public void testFindByDescriptionCup() {
        Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", uomOptional.get().getDescription());
    }

}
