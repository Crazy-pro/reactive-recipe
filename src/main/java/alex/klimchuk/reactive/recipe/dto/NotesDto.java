package alex.klimchuk.reactive.recipe.dto;

import lombok.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotesDto {

    private String id;

    private String recipeNotes;

}
