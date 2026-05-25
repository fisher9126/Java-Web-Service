package re.bt1.service;

import com.example.inventory.dto.InventoryResponseDTO;

public interface ProductService {

    void stockIn(String sku,
                 Integer quantity,
                 String username,
                 String role);

    void stockOut(String sku,
                  Integer quantity,
                  String username,
                  String role);

    InventoryResponseDTO inspectInventory(
            String username,
            String role
    );

    void deleteProduct(Long id,
                       String username,
                       String role);
}