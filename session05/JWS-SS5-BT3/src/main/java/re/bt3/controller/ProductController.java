// src/main/java/com/example/demo/product/ProductController.java
package re.bt3.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import re.bt3.entity.Product;
import re.bt3.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        product.setId(null); // để JPA tự sinh
        Product saved = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }




    public static class ProductPutRequest {
        @NotBlank(message = "Name must not be blank")
        private String name;

        @Min(value = 1, message = "Price must be greater than 0")
        private double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }


    public static class ProductPatchRequest {
        private String name;
        private Double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductPutRequest request
    ) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product existing = optional.get();
        existing.setName(request.getName());
        existing.setPrice(request.getPrice());

        Product saved = productRepository.save(existing);
        return ResponseEntity.ok(saved); // 200
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable Long id,
            @RequestBody ProductPatchRequest request
    ) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404
        }

        Product existing = optional.get();

        if (request.getName() != null) {
            if (request.getName().isBlank()) {
                return ResponseEntity.badRequest().build();
            }
            existing.setName(request.getName());
        }

        if (request.getPrice() != null) {
            if (request.getPrice() <= 0) {
                return ResponseEntity.badRequest().build();
            }
            existing.setPrice(request.getPrice());
        }

        Product saved = productRepository.save(existing);
        return ResponseEntity.ok(saved);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
