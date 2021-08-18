package uz.pdp.appatmsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WithDrawDto {
    @NotNull
    private Long amount;
    @NotNull
    private Long atmId;
}
