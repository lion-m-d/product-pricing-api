package com.ws.infrastructure.price.persistence.adapter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ws.domain.price.model.Brand;
import com.ws.domain.price.model.Price;
import com.ws.infrastructure.price.persistence.PriceJpaRepository;
import com.ws.infrastructure.price.persistence.entity.BrandEntity;
import com.ws.infrastructure.price.persistence.entity.Currency;
import com.ws.infrastructure.price.persistence.entity.PriceEntity;
import com.ws.infrastructure.price.persistence.mapper.PriceEntityMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private PriceJpaRepository priceJPARepository;
    @Mock
    private PriceEntityMapper mapper;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryImpl;

    @Test
    void getPreferredPrice_ShouldReturnMappedPrice_WhenPriceExists() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;

        PriceEntity mockEntity = createMockPriceEntity();
        Price mockDto = createMockPrice();

        when(priceJPARepository.findTopPrice(
                any(Integer.class), any(Integer.class), any(LocalDateTime.class))).thenReturn(Optional.of(mockEntity));
        when(mapper.toDomain(any(PriceEntity.class))).thenReturn(mockDto);

        Optional<Price> result = priceRepositoryImpl.getPreferredPrice(applicationDate, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(mockDto, result.get());
    }

    @Test
    void getPreferredPrice_ShouldReturnEmpty_WhenPriceDoesNotExist() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;

        when(priceJPARepository.findTopPrice(any(Integer.class), any(Integer.class),
                any(LocalDateTime.class))).thenReturn(Optional.empty());

        Optional<Price> result = priceRepositoryImpl.getPreferredPrice(applicationDate, productId, brandId);

        assertFalse(result.isPresent());
    }


    public static PriceEntity createMockPriceEntity() {
        PriceEntity price = new PriceEntity();
        price.setPriceList(1);
        price.setProductId(35455);
        price.setPriority(0);
        price.setCurrency(Currency.EUR);
        price.setPrice(new BigDecimal("35.50"));
        price.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        price.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));

        BrandEntity brand = new BrandEntity();
        brand.setId(1);
        brand.setName("Zara");
        price.setBrand(brand);

        return price;
    }

    public static Price createMockPrice() {
        return new Price(1, 35455, 0,
                com.ws.domain.price.model.Currency.EUR,
                BigDecimal.ZERO, LocalDateTime.now(), LocalDateTime.now(),
                new Brand(1, "Zara"));
    }
}
