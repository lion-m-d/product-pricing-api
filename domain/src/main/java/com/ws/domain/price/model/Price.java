package com.ws.domain.price.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents product pricing details for a specific price list and brand.
 * @param priceList  ID of the price list
 * @param productId  ID of the product
 * @param priority   price priority (for ranking)
 * @param currency   currency used, see {@link Currency}
 * @param price      price amount
 * @param startDate  start date of price validity
 * @param endDate    end date of price validity
 * @param brand      associated {@link Brand}
 */
public record Price(
        Integer priceList,
        Integer productId,
        Integer priority,
        Currency currency,
        BigDecimal price,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Brand brand
) {}
