package alex.klimchuk.reactive.recipe.repositories;

import alex.klimchuk.reactive.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface CategoryRepository extends CrudRepository<Category, String> {

    Optional<Category> findByDescription(String description);

}
