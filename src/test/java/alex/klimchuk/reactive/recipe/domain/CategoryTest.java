package alex.klimchuk.reactive.recipe.domain;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    public void testGetId() {
        String id = "4";
        category.setId(id);

        assertEquals(id, category.getId());
    }

    @Test
    public void testGetDescription() {

    }

    @Test
    public void testGetRecipes() {

    }

}
