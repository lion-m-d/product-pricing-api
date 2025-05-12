package com.ws.domain.price.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PriceTest {

    @Test
    void shouldCreatePriceRecordCorrectly() {
        Integer priceList = 1;
        Integer productId = 12345;
        Integer priority = 0;
        BigDecimal price = new BigDecimal("99.99");
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(1);
        Brand brand = new Brand(1, "ZARA");


        Price priceRecord = new Price(priceList, productId, priority, Currency.EUR, price, startDate, endDate, brand);

        assertEquals(priceList, priceRecord.priceList());
        assertEquals(productId, priceRecord.productId());
        assertEquals(priority, priceRecord.priority());
        assertEquals(price, priceRecord.price());
        assertEquals(startDate, priceRecord.startDate());
        assertEquals(endDate, priceRecord.endDate());
        assertEquals(brand, priceRecord.brand());
    }
}