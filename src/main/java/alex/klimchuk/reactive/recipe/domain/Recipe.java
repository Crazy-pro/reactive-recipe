package alex.klimchuk.reactive.recipe.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Data
@Builder
@Document
@ToString(exclude = {"difficulty", "ingredients", "categories", "notes"})
@EqualsAndHashCode(exclude = {"difficulty", "ingredients", "categories", "notes"})
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    private String id;

    private String description;

    private Integer prepTime;

    private Integer cookTime;

    private Integer servings;

    private String source;

    private String url;

    private String directions;

    private Difficulty difficulty;

    private Set<Ingredient> ingredients = new HashSet<>();

    private Byte[] image;

    private Notes notes;

    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        if (Objects.nonNull(notes)) {
            this.notes = notes;
        }
    }

    public Recipe addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

}
