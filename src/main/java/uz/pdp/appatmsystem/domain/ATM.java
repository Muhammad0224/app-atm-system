package uz.pdp.appatmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appatmsystem.enums.CardType;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ATM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long maxLimit;

    @Column(nullable = false)
    private Long minLimit;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(nullable = false)
    private String bankCode;

    @ManyToMany
    private List<Commission> commissions;

    @ManyToMany
    private List<Penny> pennies;

    @ManyToOne(optional = false)
    private Employee employee;
}
