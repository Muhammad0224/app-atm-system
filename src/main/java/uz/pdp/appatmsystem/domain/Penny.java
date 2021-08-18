package uz.pdp.appatmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appatmsystem.enums.Currency;
import uz.pdp.appatmsystem.model.PennyDto;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Penny {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column
    private String key;

    @Column
    private Integer value;

    public Penny(Currency currency, String key, Integer value) {
        this.currency = currency;
        this.key = key;
        this.value = value;
    }

    public static Long calculatePennies(List<Penny> pennies) {
        long sum = 0;
        for (Penny penny : pennies) {
            sum += Long.parseLong(penny.getKey()) * penny.getValue();
        }
        return sum;
    }

    public static List<Penny> getPennies(Long amount, List<Penny> pennies) {
        List<Penny> givingPennies = new ArrayList<>();
        pennies.sort((o1, o2) -> o2.getValue() - o1.getValue());

        for (Penny penny : pennies) {
            if (Long.parseLong(penny.getKey()) > amount)
                continue;
            int value = 0;
            for (int i = 1; i <= penny.value; i++) {
                if ((Long.parseLong(penny.getKey()) * i) <= amount) {
                    value++;
                } else break;
            }
            givingPennies.add(new Penny(penny.getCurrency(), penny.getKey(), value));
            if (Objects.equals(calculatePennies(givingPennies), amount)) {
                return givingPennies;
            }
        }

        return null;
    }

    public static ATM withdrawPennies(ATM atm, List<Penny> givingPennies) {
        for (Penny givingPenny : givingPennies) {
            for (Penny penny : atm.getPennies()) {
                if (givingPenny.getKey().equals(penny.getKey())) {
                    penny.setValue(penny.getValue() - givingPenny.getValue());
                }
            }
        }
        return atm;
    }

    public static Map<String, String> toDetail(List<Penny> pennies, ATM atm) {
        Map<String, String> details = new HashMap<>();
        for (Penny penny : pennies) {
            details.put(penny.getKey(), String.valueOf(penny.getValue()));
        }
        details.put("Currency", atm.getCardType().getCurrency());
        return details;
    }

    public static List<Penny> toPenny(List<PennyDto> dto) throws Exception {
        List<Penny> pennies = new ArrayList<>();
        for (PennyDto pennyDto : dto) {
            Penny penny = new Penny();
            penny.setValue(pennyDto.getQuantity());
            penny.setCurrency(Currency.valueOf(pennyDto.getCurrency()));
            penny.setKey(pennyDto.getPenny());
            pennies.add(penny);
        }
        return pennies;
    }
}
