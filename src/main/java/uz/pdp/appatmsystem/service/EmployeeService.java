package uz.pdp.appatmsystem.service;

import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.EmployeeDto;

public interface EmployeeService {
    ApiResponse register(EmployeeDto dto);
}
