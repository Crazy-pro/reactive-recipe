package alex.klimchuk.recipe.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    private String id = UUID.randomUUID().toString();

    private String description;

    private Double amount;

    private UnitOfMeasure unitOfMeasure;

    public Ingredient(String description, Double amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

}
