package alex.klimchuk.recipe.dto;

import alex.klimchuk.recipe.domain.Difficulty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer servings;

    private String source;

    @URL
    @NotBlank
    private String url;

    @NotBlank
    private String directions;

    private List<IngredientDto> ingredients = new ArrayList<>();

    private Byte[] image;

    @NotBlank
    private Difficulty difficulty;

    private NotesDto notes;

    private List<CategoryDto> categories = new ArrayList<>();

}
