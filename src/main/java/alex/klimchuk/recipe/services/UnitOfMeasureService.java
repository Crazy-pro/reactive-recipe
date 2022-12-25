package alex.klimchuk.recipe.services;

import alex.klimchuk.recipe.dto.UnitOfMeasureDto;
import reactor.core.publisher.Flux;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface UnitOfMeasureService {

    Flux<UnitOfMeasureDto> findAll();

}
