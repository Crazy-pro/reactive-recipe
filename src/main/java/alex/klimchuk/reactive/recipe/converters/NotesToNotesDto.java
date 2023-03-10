package alex.klimchuk.reactive.recipe.converters;

import alex.klimchuk.reactive.recipe.domain.Notes;
import alex.klimchuk.reactive.recipe.dto.NotesDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Component
public class NotesToNotesDto implements Converter<Notes, NotesDto> {

    @Override
    @Nullable
    @Synchronized
    public NotesDto convert(Notes notes) {
        return NotesDto.builder()
                .id(notes.getId())
                .recipeNotes(notes.getRecipeNotes())
                .build();
    }

}
