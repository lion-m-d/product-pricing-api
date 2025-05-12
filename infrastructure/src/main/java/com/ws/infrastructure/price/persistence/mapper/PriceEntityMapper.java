package com.ws.infrastructure.price.persistence.mapper;

import com.ws.domain.price.model.Price;
import com.ws.infrastructure.price.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;


/**
 * Mapper interface for converting {@link PriceEntity} to {@link Price}.
 *
 * <p>Uses MapStruct to map {@link PriceEntity} (database record) to {@link Price} (domain model) at compile-time.</p>
 *
 * @see PriceEntity
 * @see Price
 * @see Mapper
 */
@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    /**
     * Maps a {@link PriceEntity} to a {@link Price} domain object.
     *
     * @param entity the {@link PriceEntity} to map
     * @return the corresponding {@link Price} object
     */
    Price toDomain(PriceEntity entity);
}
