package uz.pdp.appatmsystem.service;

import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.LoginDto;

public interface AuthService {
    ApiResponse login(LoginDto dto);
}
