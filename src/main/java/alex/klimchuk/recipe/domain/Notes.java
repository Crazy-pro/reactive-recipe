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
@NoArgsConstructor
@AllArgsConstructor
public class Notes {

    @Id
    private String id;

    private String recipeNotes;

}
