package alex.klimchuk.reactive.recipe.services;

import alex.klimchuk.reactive.recipe.dto.IngredientDto;
import reactor.core.publisher.Mono;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface IngredientService {

    Mono<IngredientDto> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientDto> saveIngredientDto(IngredientDto ingredientDto);

    Mono<Void> deleteById(String recipeId, String id);

}
