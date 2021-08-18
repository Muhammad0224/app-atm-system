package uz.pdp.appatmsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.appatmsystem.domain.ATM;
import uz.pdp.appatmsystem.domain.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
