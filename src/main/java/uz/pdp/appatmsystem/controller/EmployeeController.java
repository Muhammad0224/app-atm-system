package uz.pdp.appatmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.EmployeeDto;
import uz.pdp.appatmsystem.service.EmployeeService;

import javax.validation.Valid;

import static uz.pdp.appatmsystem.controller.ApiController.API_PATH;

@RestController
@RequestMapping(API_PATH + "/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('DIRECTOR')")
    public ResponseEntity<?> register(@Valid @RequestBody EmployeeDto dto) {
        ApiResponse apiResponse = employeeService.register(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 201 : 400).body(apiResponse);
    }
}
