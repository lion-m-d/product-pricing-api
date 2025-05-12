package com.ws.infrastructure.price.rest.mapper;

import com.ws.application.price.model.PriceDto;
import com.ws.infrastructure.price.rest.dto.PriceResponseDTO;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting {@link PriceDto} to {@link PriceResponseDTO}.
 *
 * <p>Uses MapStruct to map {@link PriceDto} (service layer DTO) to {@link PriceResponseDTO} (response DTO) at compile-time.</p>
 *
 * @see PriceDto
 * @see PriceResponseDTO
 * @see Mapper
 */
@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    /**
     * Maps a {@link PriceDto} to a {@link PriceResponseDTO}.
     * @param price the {@link PriceDto} to map
     * @return the corresponding {@link PriceResponseDTO}
     */
    PriceResponseDTO toResponse(PriceDto price);
}
