package uz.pdp.appatmsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appatmsystem.domain.Commission;
import uz.pdp.appatmsystem.enums.OperationType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommissionRepo extends JpaRepository<Commission, Long> {
    Optional<Commission> findByAdditionalAndKey(boolean additional, OperationType key);
}
