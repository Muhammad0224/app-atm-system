package uz.pdp.appatmsystem.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appatmsystem.domain.Commission;
import uz.pdp.appatmsystem.domain.Employee;
import uz.pdp.appatmsystem.domain.Penny;
import uz.pdp.appatmsystem.enums.CardType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ATMDto {
    @NotNull
    private Long maxLimit;

    @NotNull
    private Long minLimit;

    @NotNull
    private String address;

    @NotNull
    @ApiModelProperty(example = "UZCARD")
    private String cardType;

    @NotNull
    private String bankCode;

    @NotNull
    private Long employeeId;
}
