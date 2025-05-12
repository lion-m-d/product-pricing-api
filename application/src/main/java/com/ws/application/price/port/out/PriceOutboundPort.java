package com.ws.application.price.port.out;

import com.ws.domain.price.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Outbound port for fetching a product's preferred price by date and brand.
 * <p>Retrieves a {@link Price} from an external source (e.g., database or API).</p>
 */
public interface PriceOutboundPort {

  /** Fetches the preferred price from an external source by date, product, and brand.
   * @param applicationDate the date the price applies
   * @param productId       the product ID
   * @param brandId         the brand ID
   * @return an {@link Optional} with the {@link Price}, or empty if not found
   */
  Optional<Price> getPreferredPrice(LocalDateTime applicationDate, Integer productId,
                                    Integer brandId);
}