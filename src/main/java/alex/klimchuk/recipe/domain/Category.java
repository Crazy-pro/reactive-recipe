package alex.klimchuk.recipe.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Data
@Builder
@Document
@ToString
@EqualsAndHashCode(exclude = {"recipes"})
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private Long id;

    private String description;

    @DBRef
    private Set<Recipe> recipes;

}
