package com.ws.infrastructure.price.rest.controller;

import com.ws.application.price.port.in.PriceInboundPort;
import com.ws.infrastructure.price.rest.dto.PriceResponseDTO;
import com.ws.infrastructure.price.rest.mapper.PriceResponseMapper;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling HTTP requests related to prices.
 *
 * <p>Implements {@link PricesApi} and provides an endpoint to retrieve the preferred price for a product and brand
 * at a specified date. Uses {@link PriceInboundPort} to fetch price data and maps it to {@link PriceResponseDTO} for the response.</p>
 *
 * @see PricesApi
 * @see PriceInboundPort
 * @see PriceResponseDTO
 */
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    private final PriceInboundPort priceInboundPort;
    private final PriceResponseMapper mapper;


    /**
     * Retrieves the preferred price for a given product, brand, and application date.
     *
     * <p>This method interacts with the {@link PriceInboundPort} to get the preferred price based on the
     * provided {@code applicationDate}, {@code productId}, and {@code brandId}. The resulting price is then
     * mapped to a {@link PriceResponseDTO} and returned in the response body.</p>
     *
     * @param applicationDate the date for which the price is requested
     * @param productId the ID of the product for which the price is requested
     * @param brandId the ID of the brand for which the price is requested
     * @return a {@link ResponseEntity} containing the {@link PriceResponseDTO} with the preferred price
     */
    @Override
    public ResponseEntity<PriceResponseDTO> getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        var response = mapper.toResponse(priceInboundPort.getPreferredPrice(applicationDate, productId, brandId));
        log.info("response 200 : {}", response);
        return ResponseEntity.ok(response);
    }

}
