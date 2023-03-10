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
public class CategoryDtoToCategoryTest {

    public static final String ID_VALUE = "1";
    public static final String DESCRIPTION = "description";
    CategoryDtoToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryDtoToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryDto()));
    }

    @Test
    public void testConvert() {
        CategoryDto categoryDtoMock = CategoryDto.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .build();

        Category category = converter.convert(categoryDtoMock);

        assertEquals(ID_VALUE, Objects.requireNonNull(category).getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

}
