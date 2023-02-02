package alex.klimchuk.reactive.recipe.controllers;

import alex.klimchuk.reactive.recipe.dto.IngredientDto;
import alex.klimchuk.reactive.recipe.dto.UnitOfMeasureDto;
import alex.klimchuk.reactive.recipe.services.IngredientService;
import alex.klimchuk.reactive.recipe.services.RecipeService;
import alex.klimchuk.reactive.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Slf4j
@Controller
@RequestMapping("/recipe/{recipeId}/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    private WebDataBinder webDataBinder;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @InitBinder("ingredient")
    public void initBinder(WebDataBinder webDataBinder) {
        this.webDataBinder = webDataBinder;
    }

    @GetMapping
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        model.addAttribute("recipe", recipeService.findDtoById(recipeId));
        return "recipe/ingredients/list";
    }

    @GetMapping("/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        return "recipe/ingredients/show";
    }

    @GetMapping("/new")
    public String newRecipe(@PathVariable String recipeId, Model model) {
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientDto);

        ingredientDto.setUnitOfMeasureDto(new UnitOfMeasureDto());

        return "recipe/ingredients/ingredientsForm";
    }

    @GetMapping("/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id).block());
        return "recipe/ingredients/ingredientsForm";
    }

    @PostMapping
    public String saveOrUpdate(@ModelAttribute("ingredient") IngredientDto ingredientDto, Model model ){
        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(ex -> log.debug("Something wrong here: ", ex.toString()));

            return "recipe/ingredients/ingredientsForm";
        }

        IngredientDto savedDto = ingredientService.saveIngredientDto(ingredientDto).block();

        log.debug("Saved recipe id: " + savedDto.getRecipeId());
        log.debug("Saved ingredient id: " + savedDto.getId());

        return "redirect:/recipe/" + savedDto.getRecipeId() + "/ingredients/" + savedDto.getId() + "/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id) {
        log.debug("Deleting ingredient id: " + id);
        ingredientService.deleteById(recipeId, id).block();

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @ModelAttribute("unitOfMeasureList")
    public Flux<UnitOfMeasureDto> populateUnitOfMeasureList(){
        return unitOfMeasureService.findAll();
    }

}
