package alex.klimchuk.reactive.recipe.repositories.reactive;

import alex.klimchuk.reactive.recipe.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {

}
