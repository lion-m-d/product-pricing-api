package com.ws.infrastructure.price.persistence.adapter;


import com.ws.application.price.port.out.PriceOutboundPort;
import com.ws.domain.price.model.Price;
import com.ws.infrastructure.price.persistence.PriceJpaRepository;
import com.ws.infrastructure.price.persistence.mapper.PriceEntityMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Adapter implementing {@link PriceOutboundPort} to interact with the database.
 * <p>Acts as a bridge between the domain and data layers, using {@link PriceJpaRepository} to fetch price data
 * and {@link PriceEntityMapper} to map {@link com.ws.infrastructure.price.persistence.entity.PriceEntity} to {@link Price}.</p>
 * @see PriceOutboundPort
 * @see PriceJpaRepository
 * @see PriceEntityMapper
 */
@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceOutboundPort {

    private final PriceJpaRepository priceJPARepository;
    private final PriceEntityMapper mapper;

    /**
     * Retrieves the preferred price for a product and brand at a specified date.
     * <p>Queries {@link PriceJpaRepository} for the price based on product ID, brand ID, and application date,
     * and maps the result to {@link Price} using {@link PriceEntityMapper}.</p>
     * @param applicationDate the date for which the price is retrieved
     * @param productId the product ID
     * @param brandId the brand ID
     * @return an {@link Optional} with the {@link Price}, or empty if not found
     */
    @Override
    public Optional<Price> getPreferredPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceJPARepository.findTopPrice(productId, brandId, applicationDate)
                .map(mapper::toDomain);
    }
}
