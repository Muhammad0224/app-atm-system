package uz.pdp.appatmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.LoginDto;
import uz.pdp.appatmsystem.service.AuthService;

import javax.validation.Valid;

import static uz.pdp.appatmsystem.controller.ApiController.API_PATH;

@RestController
@RequestMapping(API_PATH + "/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto dto){
        ApiResponse apiResponse = authService.login(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

}
