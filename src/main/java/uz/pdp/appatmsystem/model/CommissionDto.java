package uz.pdp.appatmsystem.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appatmsystem.enums.OperationType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommissionDto {
    @ApiModelProperty(example = "true")
    private boolean sameBank;

    private String key;      // GET    PUT

    @NotNull
    @ApiModelProperty(example = "PUT")
    private String value;
}
