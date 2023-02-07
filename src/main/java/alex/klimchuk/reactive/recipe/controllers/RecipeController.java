package alex.klimchuk.reactive.recipe.controllers;

import alex.klimchuk.reactive.recipe.dto.RecipeDto;
import alex.klimchuk.reactive.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    private WebDataBinder webDataBinder;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        this.webDataBinder = webDataBinder;
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeDto());
        return "recipe/recipeForm";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findDtoById(id).block());
        return "recipe/recipeForm";
    }

    @PostMapping
    public String saveOrUpdate(@ModelAttribute RecipeDto recipeDto) {
        webDataBinder.validate();
        BindingResult bindingResult = webDataBinder.getBindingResult();

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(ex -> log.debug("Something wrong here: ", ex.toString()));
            return "recipe/recipeForm";
        }
        RecipeDto savedRecipeDto = recipeService.saveRecipeDto(recipeDto).block();
        return "redirect:/recipe/show/" + savedRecipeDto.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable String id) {
        recipeService.deleteById(id).block();
        return "redirect:/";
    }

}
