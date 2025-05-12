package com.ws.infrastructure.price.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a price in the database.
 * <p>Maps to the {@code PRICES} table and contains price details like {@link #priceList}, {@link #productId},
 * {@link #brand}, {@link #priority}, {@link #currency}, {@link #startDate}, and {@link #endDate}. The entity is indexed
 * for efficient querying by product ID, brand ID, dates, and priority.</p>
 * @see Entity
 * @see Table
 * @see BrandEntity
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRICES", indexes = @Index(columnList = "productId, brandId, startDate, endDate, priority DESC"))
public class PriceEntity {

    @Id
    private Integer priceList;
    private Integer productId;
    private Integer priority;
    @Enumerated(EnumType.STRING)
    @Column(name = "CURR")
    private Currency currency;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    private BrandEntity brand;
}
