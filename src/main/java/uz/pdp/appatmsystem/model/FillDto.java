package uz.pdp.appatmsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FillDto {
    @NotNull
    private Long atmId;

    @NotNull
    private List<PennyDto> pennies;
}
