package uz.pdp.appatmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import uz.pdp.appatmsystem.enums.CardType;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number;

    @Column(nullable = false)
    private Integer CVV;

    @Column(nullable = false)
    private String ownerName;

    @Column
    private Double balance;

    @Column(nullable = false)
    private String expiry;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardType cardType;

    @Column(nullable = false)
    private String bankCode;

    private int attempts = 0;

    private boolean active = true;

    private boolean blocked = false;

    public static User getCurrentCard(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Boolean checkValidity(String expiry){
        String[] split = expiry.split("/");
        LocalDate expire = LocalDate.of(Integer.parseInt("20" + split[1]), Integer.parseInt(split[0]), 1);
        return LocalDate.now().isAfter(expire);
    }
}
