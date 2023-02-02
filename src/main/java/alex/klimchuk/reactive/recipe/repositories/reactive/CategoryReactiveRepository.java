package alex.klimchuk.reactive.recipe.repositories.reactive;

import alex.klimchuk.reactive.recipe.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {

    Mono<Category> findByDescription(String description);

}
