package alex.klimchuk.reactive.recipe.services;

import alex.klimchuk.reactive.recipe.dto.UnitOfMeasureDto;
import reactor.core.publisher.Flux;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface UnitOfMeasureService {

    Flux<UnitOfMeasureDto> findAll();

}
