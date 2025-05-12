package com.ws.infrastructure.price.persistence;


import com.ws.infrastructure.price.persistence.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link PriceEntity}.
 *
 * <p>Extends {@link JpaRepository} to query the {@link PriceEntity} table and includes a custom method to find
 * the highest-priority price for a product and brand, valid on a given date. Uses Spring Data JPA for data access.</p>
 *
 * @see PriceEntity
 * @see JpaRepository
 */

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {

    /**
     * Finds the highest-priority price for a product and brand, valid at a given date.
     *
     * <p>Fetches the {@link PriceEntity} with the highest priority for the specified {@code productId} and {@code brandId},
     * and checks its validity for the provided {@code date}. Returns the first result ordered by priority in descending order.</p>
     *
     * @param productId the product ID
     * @param brandId the brand ID
     * @param date the valid date for the price
     * @return an {@link Optional} with the {@link PriceEntity}, or empty if no matching price is found
     */
    @Query("""
                SELECT p FROM PriceEntity p
                WHERE p.productId = :productId
                AND p.brand.id = :brandId
                AND p.startDate <= :date
                AND p.endDate >= :date
                ORDER BY p.priority DESC LIMIT 1
            """)
    Optional<PriceEntity> findTopPrice(@Param("productId") Integer productId,
                                       @Param("brandId") Integer brandId,
                                       @Param("date") LocalDateTime date);

}
