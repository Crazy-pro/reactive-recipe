package alex.klimchuk.reactive.recipe.repositories;

import alex.klimchuk.reactive.recipe.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, String> {

    Optional<UnitOfMeasure> findByDescription(String description);

}
