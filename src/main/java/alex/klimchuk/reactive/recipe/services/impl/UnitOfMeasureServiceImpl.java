package alex.klimchuk.reactive.recipe.services.impl;

import alex.klimchuk.reactive.recipe.dto.UnitOfMeasureDto;
import alex.klimchuk.reactive.recipe.converters.UnitOfMeasureToUnitOfMeasureDto;
import alex.klimchuk.reactive.recipe.repositories.reactive.UnitOfMeasureReactiveRepository;
import alex.klimchuk.reactive.recipe.services.UnitOfMeasureService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;
    private final UnitOfMeasureToUnitOfMeasureDto unitOfMeasureToUnitOfMeasureDto;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository,
                                    UnitOfMeasureToUnitOfMeasureDto unitOfMeasureToUnitOfMeasureDto) {
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
        this.unitOfMeasureToUnitOfMeasureDto = unitOfMeasureToUnitOfMeasureDto;
    }

    @Override
    public Flux<UnitOfMeasureDto> findAll() {
        return unitOfMeasureReactiveRepository.findAll()
                .mapNotNull(unitOfMeasureToUnitOfMeasureDto::convert);
    }

}
