package alex.klimchuk.reactive.recipe.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {

    private String id;

    private String recipeId;

    @NotBlank
    private String description;

    @Min(1)
    @NotNull
    private Double amount;

    @NotNull
    private UnitOfMeasureDto unitOfMeasureDto;

}
