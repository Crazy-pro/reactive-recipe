package alex.klimchuk.recipe.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Data
@Builder
@Document
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UnitOfMeasure {

    @Id
    private Long id;

    private String description;

}
