package com.ws.infrastructure.price.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 * Entity representing a brand in the database.
 * <p>Maps to the {@code BRAND} table and contains the brand's ID {@link #id} and name {@link #name}.</p>
 * @see Entity
 * @see Table
 */
@Setter
@Getter
@Entity
@Table(name = "BRAND")
public class BrandEntity {
    @Id
    private Integer id;
    private String name;
}
