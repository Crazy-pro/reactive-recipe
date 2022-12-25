package alex.klimchuk.recipe.dto;

import alex.klimchuk.recipe.domain.Difficulty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    private String id;

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;

    private String source;

    private String url;

    private String directions;

    private List<IngredientDto> ingredients = new ArrayList<>();

    private Byte[] image;

    private Difficulty difficulty;

    private NotesDto notes;

    private List<CategoryDto> categories = new ArrayList<>();

}
