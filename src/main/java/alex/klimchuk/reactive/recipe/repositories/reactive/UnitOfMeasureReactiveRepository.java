package alex.klimchuk.reactive.recipe.repositories.reactive;

import alex.klimchuk.reactive.recipe.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {

    Mono<UnitOfMeasure> findByDescription(String description);

}
