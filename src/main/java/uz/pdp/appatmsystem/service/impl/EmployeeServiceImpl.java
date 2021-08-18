package uz.pdp.appatmsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appatmsystem.domain.Employee;
import uz.pdp.appatmsystem.enums.RoleEnum;
import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.EmployeeDto;
import uz.pdp.appatmsystem.repository.EmployeeRepo;
import uz.pdp.appatmsystem.repository.RoleRepo;
import uz.pdp.appatmsystem.service.EmployeeService;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepo roleRepo;

    @Override
    public ApiResponse register(EmployeeDto dto) {
        if (employeeRepo.existsByEmail(dto.getEmail()))
            return new ApiResponse("Employee already existed", false);
        Employee employee = new Employee();
        employee.setEmail(dto.getEmail());
        employee.setFullName(dto.getFullName());
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employee.getRoles().add(roleRepo.findByName(RoleEnum.ROLE_EMPLOYEE.name()));
        employeeRepo.save(employee);
        return new ApiResponse("OK", true);
    }
}
