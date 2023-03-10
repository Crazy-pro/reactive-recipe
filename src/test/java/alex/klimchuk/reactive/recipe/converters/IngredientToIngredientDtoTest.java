package alex.klimchuk.reactive.recipe.converters;

import alex.klimchuk.reactive.recipe.domain.Ingredient;
import alex.klimchuk.reactive.recipe.domain.UnitOfMeasure;
import alex.klimchuk.reactive.recipe.dto.IngredientDto;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class IngredientToIngredientDtoTest {
    public static final Double AMOUNT = 1.0;
    public static final String DESCRIPTION = "CheeseBurger";
    public static final String ID_VALUE = "1";
    public static final String UOM_ID = "2";

    IngredientToIngredientDto converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientDto(new UnitOfMeasureToUnitOfMeasureDto());
    }

    @Test
    public void testNullConvert() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertNullUOM() {
        Ingredient ingredient = Ingredient.builder()
                .id(ID_VALUE)
                .amount(AMOUNT)
                .description(DESCRIPTION)
                .build();

        IngredientDto ingredientDto = converter.convert(ingredient);

        assertNull(Objects.requireNonNull(ingredientDto).getUnitOfMeasureDto());
        assertEquals(ID_VALUE, ingredientDto.getId());
        assertEquals(AMOUNT, ingredientDto.getAmount());
        assertEquals(DESCRIPTION, ingredientDto.getDescription());
    }

    @Test
    public void testConvertWithUom() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);

        Ingredient ingredient = Ingredient.builder()
                .id(ID_VALUE)
                .amount(AMOUNT)
                .description(DESCRIPTION)
                .unitOfMeasure(unitOfMeasure)
                .build();

        IngredientDto ingredientDto = converter.convert(ingredient);

        assertEquals(ID_VALUE, Objects.requireNonNull(ingredientDto).getId());
        assertNotNull(ingredientDto.getUnitOfMeasureDto());
        assertEquals(UOM_ID, ingredientDto.getUnitOfMeasureDto().getId());
        assertEquals(AMOUNT, ingredientDto.getAmount());
        assertEquals(DESCRIPTION, ingredientDto.getDescription());
    }

}
