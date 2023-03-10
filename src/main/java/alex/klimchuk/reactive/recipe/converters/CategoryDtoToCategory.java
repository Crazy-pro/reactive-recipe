package alex.klimchuk.reactive.recipe.converters;

import alex.klimchuk.reactive.recipe.domain.Category;
import alex.klimchuk.reactive.recipe.dto.CategoryDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Component
public class CategoryDtoToCategory implements Converter<CategoryDto, Category> {

    @Override
    @Nullable
    @Synchronized
    public Category convert(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .description(categoryDto.getDescription())
                .build();
    }

}
