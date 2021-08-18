package uz.pdp.appatmsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appatmsystem.domain.Card;

import java.util.Optional;

public interface CardRepo extends JpaRepository<Card, Long> {
    @Query("select c from Card c where c.number = ?1 and c.active = true and c.blocked = false")
    Optional<Card> findActiveCard(String number);

    Optional<Card> findByNumber(String number);

    boolean existsByNumber(String number);
}
