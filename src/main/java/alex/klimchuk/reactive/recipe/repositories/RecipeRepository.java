package alex.klimchuk.reactive.recipe.repositories;

import alex.klimchuk.reactive.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface RecipeRepository extends CrudRepository<Recipe, String> {

}
