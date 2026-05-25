package re.bt1.service.impl;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.bt1.repository.ProductRepository;
import re.bt1.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void stockIn(String sku,
                        Integer quantity,
                        String username,
                        String role) {

        int updated = productRepository.stockIn(sku, quantity);

        if (updated == 0) {
            throw new RuntimeException("Không tìm thấy sản phẩm");
        }
    }

    @Override
    public void stockOut(String sku,
                         Integer quantity,
                         String username,
                         String role) {

        int updated = productRepository.stockOut(sku, quantity);

        if (updated == 0) {
            throw new RuntimeException(
                    "Xuất kho thất bại hoặc không đủ hàng"
            );
        }
    }

    @Override
    public com.example.inventory.dto.InventoryResponseDTO inspectInventory(
            String username,
            String role
    ) {

        Long totalQuantity = productRepository.totalQuantity();
        Double totalValue = productRepository.totalValue();

        return new com.example.inventory.dto.InventoryResponseDTO(
                totalQuantity,
                totalValue
        );
    }

    @Override
    public void deleteProduct(Long id,
                              String username,
                              String role) {

        productRepository.deleteById(id);
    }
}
