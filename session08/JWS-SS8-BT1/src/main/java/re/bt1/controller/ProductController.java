package re.bt1.controller;




import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import re.bt1.dto.StockRequestDTO;
import re.bt1.service.ProductService;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/stock-in")
    public String stockIn(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody StockRequestDTO dto
    ) {

        productService.stockIn(
                dto.getSku(),
                dto.getQuantity(),
                username,
                role
        );

        return "Stock in success";
    }

    @PostMapping("/stock-out")
    public String stockOut(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody StockRequestDTO dto
    ) {

        productService.stockOut(
                dto.getSku(),
                dto.getQuantity(),
                username,
                role
        );

        return "Stock out success";
    }

    @GetMapping("/inspect")
    public com.example.inventory.dto.InventoryResponseDTO inspect(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {

        return productService.inspectInventory(
                username,
                role
        );
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {

        productService.deleteProduct(
                id,
                username,
                role
        );

        return "Delete success";
    }
}