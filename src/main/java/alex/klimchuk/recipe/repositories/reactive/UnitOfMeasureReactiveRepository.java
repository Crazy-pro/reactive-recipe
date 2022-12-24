package alex.klimchuk.recipe.repositories.reactive;

import alex.klimchuk.recipe.domain.UnitOfMeasure;
import com.mongodb.Mongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {

    Mono<UnitOfMeasure> findByDescription(String description);

}
