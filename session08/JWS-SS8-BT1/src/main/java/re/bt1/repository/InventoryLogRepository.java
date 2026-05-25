package re.bt1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.bt1.entity.InventoryLog;

public interface InventoryLogRepository
        extends JpaRepository<InventoryLog, Long> {
}