package com.ws.application.price.service;

import com.ws.application.price.mapper.PriceDtoMapper;
import com.ws.application.price.model.PriceDto;
import com.ws.application.price.port.in.PriceInboundPort;
import com.ws.application.price.port.out.PriceOutboundPort;
import com.ws.domain.price.exception.NotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Service implementing {@link PriceInboundPort} to retrieve the preferred price
 * for a product by date and brand.
 *
 * <p>Interacts with {@link PriceOutboundPort} to fetch price data and uses
 * {@link PriceDtoMapper} to map {@link com.ws.domain.price.model.Price} to {@link PriceDto}.</p>
 *
 * <p>Throws {@link NotFoundException} if no price is found.</p>
 */

@Component
@RequiredArgsConstructor
public class PriceService implements PriceInboundPort {


  private static final String MSG_NOTFOUND_ERROR = "No price available.";
  private final PriceOutboundPort priceOutboundPort;
  private final PriceDtoMapper mapper;

  /**
   * Retrieves the preferred price for a product and brand at a specific date.
   *
   * @param applicationDate the date the price applies
   * @param productId       the product ID
   * @param brandId         the brand ID
   * @return the {@link PriceDto} if a price is found
   * @throws NotFoundException if no price is found
   */
  @Override
  public PriceDto getPreferredPrice(LocalDateTime applicationDate, Integer productId,
                                    Integer brandId) {
    return priceOutboundPort.getPreferredPrice(applicationDate, productId, brandId)
        .map(mapper::toDto)
        .orElseThrow(() -> new NotFoundException(MSG_NOTFOUND_ERROR));
  }
}
