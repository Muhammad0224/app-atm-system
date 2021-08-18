package uz.pdp.appatmsystem.model.resp;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appatmsystem.domain.ATM;
import uz.pdp.appatmsystem.enums.OperationType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ATMHistoryRespDto {

    private String details;

    private boolean fromEmployee = false;

    private OperationType operation;

    private Long atm;

    private LocalDateTime createdAt = LocalDateTime.now();
}
