package re.bt4.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    static class Product {

        private String productId;
        private String name;
        private double price;
        private int quantity;

        public Product() {
        }

        public Product(String productId, String name,
                       double price, int quantity) {

            this.productId = productId;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

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

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    private List<Product> products = new ArrayList<>();

    public ProductController() {

        products.add(new Product("P001", "Laptop", 1500, 10));
        products.add(new Product("P002", "Mouse", 20, 50));
    }

    @PutMapping("/{productId}")
    public Object updateProduct(@PathVariable String productId,
                                @RequestBody Product updatedProduct) {

        for (Product product : products) {

            if (product.getProductId().equals(productId)) {

                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setQuantity(updatedProduct.getQuantity());

                return product;
            }
        }

        return "Product with ID " + productId + " not found!";
    }


    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable String productId) {

        for (Product product : products) {

            if (product.getProductId().equals(productId)) {

                products.remove(product);

                return "Deleted product with ID: " + productId;
            }
        }

        return "Product with ID " + productId + " not found!";
    }
}