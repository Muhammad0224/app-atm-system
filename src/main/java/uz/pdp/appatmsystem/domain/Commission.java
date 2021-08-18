package uz.pdp.appatmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appatmsystem.enums.OperationType;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean additional = true;  // true -> karta banki bilan bankomat banki bitta,    false -> har xil

    @Enumerated(EnumType.STRING)
    private OperationType key;      // GET    PUT

    @Column
    private String value;
}
