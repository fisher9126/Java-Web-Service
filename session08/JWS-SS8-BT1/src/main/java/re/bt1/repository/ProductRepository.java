package re.bt1.repository;


import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import re.bt1.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Product p
        SET p.quantity = p.quantity + :quantity
        WHERE p.sku = :sku
    """)
    int stockIn(@Param("sku") String sku,
                @Param("quantity") Integer quantity);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Product p
        SET p.quantity = p.quantity - :quantity
        WHERE p.sku = :sku
        AND p.quantity >= :quantity
    """)
    int stockOut(@Param("sku") String sku,
                 @Param("quantity") Integer quantity);

    @Query("""
        SELECT SUM(p.quantity)
        FROM Product p
    """)
    Long totalQuantity();

    @Query("""
        SELECT SUM(p.quantity * p.price)
        FROM Product p
    """)
    Double totalValue();
}
