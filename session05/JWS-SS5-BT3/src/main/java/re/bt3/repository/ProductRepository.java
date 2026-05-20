package re.bt3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import re.bt3.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

