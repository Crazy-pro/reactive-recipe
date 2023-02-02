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
public class CategoryToCategoryDto implements Converter<Category, CategoryDto> {

    @Override
    @Nullable
    @Synchronized
    public CategoryDto convert(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .description(category.getDescription())
                .build();
    }

}
