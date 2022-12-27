package alex.klimchuk.recipe.controllers;

import alex.klimchuk.recipe.configs.WebFluxConfig;
import alex.klimchuk.recipe.domain.Recipe;
import alex.klimchuk.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
public class RouterFunctionTest {

    WebTestClient webTestClient;

    @Mock
    RecipeService recipeService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        WebFluxConfig webFluxConfig = new WebFluxConfig();

        RouterFunction<?> routerFunction = webFluxConfig.routes(recipeService);

        webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    public void testGetRecipesWithoutData(){
        when(recipeService.getRecipes()).thenReturn(Flux.just());

        webTestClient.get().uri("/api/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGetRecipesWithData(){
        when(recipeService.getRecipes()).thenReturn(Flux.just(new Recipe()));

        webTestClient.get().uri("/api/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Recipe.class);
    }

}
