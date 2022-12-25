package alex.klimchuk.recipe.services;

import alex.klimchuk.recipe.domain.Recipe;
import alex.klimchuk.recipe.dto.RecipeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public interface RecipeService {

    Flux<Recipe> getRecipes();

    Mono<Recipe> findById(String id);

    Mono<RecipeDto> findDtoById(String id);

    Mono<RecipeDto> saveRecipeDto(RecipeDto recipeDto);

    void deleteById(String id);

}
