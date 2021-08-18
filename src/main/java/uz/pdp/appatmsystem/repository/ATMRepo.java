package uz.pdp.appatmsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appatmsystem.domain.ATM;

public interface ATMRepo extends JpaRepository<ATM, Long> {
}
