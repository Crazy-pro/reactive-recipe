package alex.klimchuk.reactive.recipe.converters;

import alex.klimchuk.reactive.recipe.domain.Category;
import alex.klimchuk.reactive.recipe.dto.CategoryDto;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class CategoryToCategoryDtoTest {

    public static final String ID_VALUE = "1";
    public static final String DESCRIPTION = "description";
    CategoryToCategoryDto converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryDto();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void testConvert() {
        Category category = Category.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .build();

        CategoryDto categoryDto = converter.convert(category);

        assertEquals(ID_VALUE, Objects.requireNonNull(categoryDto).getId());
        assertEquals(DESCRIPTION, categoryDto.getDescription());
    }

}
