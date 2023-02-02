package alex.klimchuk.reactive.recipe.converters;

import alex.klimchuk.reactive.recipe.domain.UnitOfMeasure;
import alex.klimchuk.reactive.recipe.dto.UnitOfMeasureDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class UnitOfMeasureDtoToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final String ID_VALUE = "1";

    UnitOfMeasureDtoToUnitOfMeasure unitOfMeasureDtoConverter;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureDtoConverter = new UnitOfMeasureDtoToUnitOfMeasure();
    }

    @Test
    public void testNullObject() {
        assertNull(unitOfMeasureDtoConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(unitOfMeasureDtoConverter.convert(new UnitOfMeasureDto()));
    }

    @Test
    public void testConvert() {
        UnitOfMeasureDto unitOfMeasureDto = UnitOfMeasureDto.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .build();

        UnitOfMeasure unitOfMeasure = unitOfMeasureDtoConverter.convert(unitOfMeasureDto);

        assertNotNull(unitOfMeasure);
        assertEquals(ID_VALUE, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }

}
