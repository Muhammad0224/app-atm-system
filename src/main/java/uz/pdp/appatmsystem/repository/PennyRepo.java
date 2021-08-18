package uz.pdp.appatmsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appatmsystem.domain.Penny;

@Repository
public interface PennyRepo extends JpaRepository<Penny, Long> {
}
