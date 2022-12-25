package alex.klimchuk.recipe.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Data
@Builder
@Document
@ToString
@EqualsAndHashCode(exclude = {"recipe"})
@NoArgsConstructor
@AllArgsConstructor
public class Notes {

    @Id
    private String id;

    @DBRef
    private Recipe recipe;

    private String recipeNotes;

}
