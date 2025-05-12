package com.ws.infrastructure.price.rest.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ws.application.price.model.PriceDto;
import com.ws.application.price.port.in.PriceInboundPort;
import com.ws.infrastructure.price.rest.dto.PriceResponseDTO;
import com.ws.infrastructure.price.rest.mapper.PriceResponseMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceInboundPort priceInboundPort;

    @Mock
    private PriceResponseMapper mapper;

    @InjectMocks
    private PriceController priceController;

    @Test
    void getPrices_ShouldReturnResponseEntity_WhenPriceExists() {
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;
        PriceDto mockDto = createDtoMock();
        PriceResponseDTO mockPriceResponseDTO = createResponseMock();


        when(mapper.toResponse(any(PriceDto.class))).thenReturn(mockPriceResponseDTO);

        when(priceInboundPort.getPreferredPrice(any(LocalDateTime.class),
                any(Integer.class), any(Integer.class))).thenReturn(mockDto);

        ResponseEntity<PriceResponseDTO> response = priceController.getPrices(applicationDate, productId, brandId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    public static PriceResponseDTO createResponseMock() {
        return new PriceResponseDTO()
                .productId(35455)
                .brandId(1)
                .priceList(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(new BigDecimal("35.50"));
    }

    public static PriceDto createDtoMock() {
        return new PriceDto(
                1,
                35455,
                new BigDecimal("35.50"),
                LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"),
                1
        );
    }
}
