package alex.klimchuk.recipe.domain;

import lombok.*;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum Difficulty {

    EASY(0L, "Easy"),
    MODERATE(1L, "Moderate"),
    HARD(2L, "Hard");

    private Long id;

    private String name;

}
