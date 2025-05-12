package com.ws.application.price.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO representing the price details of a product.
 * <p>Includes price list ID, product ID, price amount, validity dates, and brand ID.</p>
 * <p>Used to transfer price data between application layers.</p>
 * @param priceList the price list ID
 * @param productId the product ID
 * @param price     the price amount
 * @param startDate the start date of validity
 * @param endDate   the end date of validity
 * @param brandId   the brand ID
 */
public record PriceDto(
    Integer priceList,
    Integer productId,
    BigDecimal price,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer brandId
) {
}
