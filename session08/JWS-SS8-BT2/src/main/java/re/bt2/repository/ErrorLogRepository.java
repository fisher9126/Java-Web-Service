package re.bt2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import re.bt2.entity.ErrorLog;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}