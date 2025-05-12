package com.ws.application.price.mapper;

import com.ws.application.price.model.PriceDto;
import com.ws.domain.price.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Maps {@link Price} domain models to {@link PriceDto} DTOs.
 *
 * <p>Implemented by MapStruct and registered as a Spring Bean via {@code componentModel = "spring"}.</p>
 *
 * <p>Example: {@code PriceDto dto = priceDtoMapper.toDto(price);}</p>
 *
 * <p>Note: Maps {@code brand.id} in {@link Price} to {@code brandId} in {@link PriceDto}.</p>
 *
 * @see Price
 * @see PriceDto
 */
@Mapper(componentModel = "spring")
public interface PriceDtoMapper {

  /**
   * Maps a {@link Price} to its {@link PriceDto} representation.
   * @param price the domain model
   * @return the mapped {@link PriceDto}
   */
  @Mapping(source = "brand.id", target = "brandId")
  PriceDto toDto(Price price);
}
