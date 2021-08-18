package uz.pdp.appatmsystem.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.appatmsystem.enums.CardType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    @NotNull
    private String number;

    @NotNull
    private Integer CVV;

    @NotNull
    private String ownerName;

    private Double balance;

    @NotNull
    @ApiModelProperty(example = "10/24")
    private String expiry;

    @NotNull
    private String password;

    @NotNull
    @ApiModelProperty(example = "HUMO")
    private String cardType;

    @NotNull
    private String bankCode;
}
