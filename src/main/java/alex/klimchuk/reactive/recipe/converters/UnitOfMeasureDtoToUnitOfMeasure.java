package alex.klimchuk.reactive.recipe.converters;

import alex.klimchuk.reactive.recipe.domain.UnitOfMeasure;
import alex.klimchuk.reactive.recipe.dto.UnitOfMeasureDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Copyright Alex Klimchuk (c) 2022.
 */
@Component
public class UnitOfMeasureDtoToUnitOfMeasure implements Converter<UnitOfMeasureDto, UnitOfMeasure> {

    @Override
    @Nullable
    @Synchronized
    public UnitOfMeasure convert(UnitOfMeasureDto unitOfMeasureDto) {
        return UnitOfMeasure.builder()
                .id(unitOfMeasureDto.getId())
                .description(unitOfMeasureDto.getDescription())
                .build();
    }

}
