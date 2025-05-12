package com.ws.application.price.port.in;

import com.ws.application.price.model.PriceDto;
import java.time.LocalDateTime;

/**
 * Inbound port for retrieving the preferred price of a product by date and brand.
 * <p>Defines the contract for fetching pricing data from external sources.</p>
 * <p>Returns a {@link PriceDto} with price list ID, product ID, price, dates, and brand ID.</p>
 */
public interface PriceInboundPort {

  /**
   * Gets the preferred price for a product, brand, and application date.
   * @param applicationDate the date the price applies
   * @param productId       the product ID
   * @param brandId         the brand ID
   * @return the {@link PriceDto} with the preferred price
   */
  PriceDto getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
