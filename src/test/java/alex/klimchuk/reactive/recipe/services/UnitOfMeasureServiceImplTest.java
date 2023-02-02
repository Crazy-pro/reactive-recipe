package alex.klimchuk.reactive.recipe.services;

import alex.klimchuk.reactive.recipe.converters.UnitOfMeasureToUnitOfMeasureDto;
import alex.klimchuk.reactive.recipe.domain.UnitOfMeasure;
import alex.klimchuk.reactive.recipe.dto.UnitOfMeasureDto;
import alex.klimchuk.reactive.recipe.repositories.reactive.UnitOfMeasureReactiveRepository;
import alex.klimchuk.reactive.recipe.services.impl.UnitOfMeasureServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureDto unitOfMeasureToUnitOfMeasureDto;
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        unitOfMeasureToUnitOfMeasureDto = new UnitOfMeasureToUnitOfMeasureDto();
        service = new UnitOfMeasureServiceImpl(unitOfMeasureReactiveRepository, unitOfMeasureToUnitOfMeasureDto);
    }

    @Test
    public void testFindAll() {
        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId("1");

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId("2");

        when(unitOfMeasureReactiveRepository.findAll()).thenReturn(Flux.just(unitOfMeasure1, unitOfMeasure2));

        List<UnitOfMeasureDto> unitOfMeasures = service.findAll().collectList().block();

        assertEquals(2, unitOfMeasures.size());
        verify(unitOfMeasureReactiveRepository, times(1)).findAll();
    }

}
