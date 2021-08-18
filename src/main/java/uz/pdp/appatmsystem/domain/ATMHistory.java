package uz.pdp.appatmsystem.domain;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appatmsystem.enums.OperationType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ATMHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;

    private boolean fromEmployee = false;

    @Enumerated(EnumType.STRING)
    private OperationType operation;

    @ManyToOne
    private ATM atm;

    private LocalDateTime createdAt = LocalDateTime.now();

    public static ATMHistory createHistory(OperationType operationType, ATM atm, Map<String, String> details, boolean fromEmployee){
        ATMHistory atmHistory = new ATMHistory();
        atmHistory.setFromEmployee(fromEmployee);
        atmHistory.setAtm(atm);
        atmHistory.setOperation(operationType);
        atmHistory.setDetails(new Gson().toJson(details));
        return atmHistory;
    }
}
