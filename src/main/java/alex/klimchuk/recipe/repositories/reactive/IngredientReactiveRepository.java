package alex.klimchuk.recipe.repositories.reactive;

import alex.klimchuk.recipe.domain.Ingredient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface IngredientReactiveRepository extends ReactiveMongoRepository<Ingredient, String> {

}
