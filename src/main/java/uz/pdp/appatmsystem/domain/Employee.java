package uz.pdp.appatmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    private List<ATM> atms = new ArrayList<>();

    public static User getCurrentEmployee(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
