package uz.pdp.appatmsystem.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appatmsystem.domain.ATM;
import uz.pdp.appatmsystem.enums.Currency;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PennyDto {
    @NotNull
    @ApiModelProperty(example = "UZS")
    private String currency;

    @NotNull
    @ApiModelProperty(example = "1000")
    private String penny;

    @NotNull
    @ApiModelProperty(example = "100")
    private Integer quantity;

}
