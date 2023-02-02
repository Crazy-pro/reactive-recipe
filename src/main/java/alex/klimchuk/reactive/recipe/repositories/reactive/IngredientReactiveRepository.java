package alex.klimchuk.reactive.recipe.repositories.reactive;

import alex.klimchuk.reactive.recipe.domain.Ingredient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface IngredientReactiveRepository extends ReactiveMongoRepository<Ingredient, String> {

}
