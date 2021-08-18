package uz.pdp.appatmsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appatmsystem.domain.ATMHistory;
import uz.pdp.appatmsystem.enums.OperationType;

import java.time.LocalDateTime;
import java.util.List;

public interface ATMHistoryRepo extends JpaRepository<ATMHistory, Long> {
    @Query("select a from ATMHistory a where a.atm.id = ?1 and a.createdAt between ?2 and ?3 and a.fromEmployee = false")
    List<ATMHistory> getIntervalReport(Long atm_id, LocalDateTime createdAt, LocalDateTime createdAt2);

    @Query("select a from ATMHistory a where a.atm.id = ?1 and a.createdAt between ?2 and ?3 and a.fromEmployee = false and a.operation = ?4")
    List<ATMHistory> getIntervalReportOp(Long atm_id, LocalDateTime createdAt, LocalDateTime createdAt2, OperationType operation);

    @Query("select a from ATMHistory a where a.atm.id = ?1 and a.fromEmployee = true")
    List<ATMHistory> getFillReport(Long atm_id);
}
