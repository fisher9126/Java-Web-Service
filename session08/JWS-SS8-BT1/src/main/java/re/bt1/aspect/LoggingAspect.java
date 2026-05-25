package re.bt1.aspect;




import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import re.bt1.entity.InventoryLog;
import re.bt1.repository.InventoryLogRepository;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final InventoryLogRepository logRepository;

    @AfterReturning("""
        execution(* com.example.inventory.service.impl
        .ProductServiceImpl.stockIn(..))
        && args(sku, quantity, username, role)
    """)
    public void logStockIn(String sku,
                           Integer quantity,
                           String username,
                           String role) {

        saveLog(username,
                "STOCK_IN",
                quantity);
    }

    @AfterReturning("""
        execution(* re.bt1.service.impl
        .ProductServiceImpl.stockOut(..))
        && args(sku, quantity, username, role)
    """)
    public void logStockOut(String sku,
                            Integer quantity,
                            String username,
                            String role) {

        saveLog(username,
                "STOCK_OUT",
                quantity);
    }

    private void saveLog(String username,
                         String action,
                         Integer quantity) {

        InventoryLog log = InventoryLog.builder()
                .timestamp(LocalDateTime.now())
                .username(username)
                .action(action)
                .detail(
                        "Quantity changed: " + quantity
                )
                .build();

        logRepository.save(log);
    }
}