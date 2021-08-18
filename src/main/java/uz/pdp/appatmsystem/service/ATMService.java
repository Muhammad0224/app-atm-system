package uz.pdp.appatmsystem.service;

import uz.pdp.appatmsystem.domain.ATM;
import uz.pdp.appatmsystem.model.ATMDto;
import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.FillDto;
import uz.pdp.appatmsystem.model.LimitDto;

public interface ATMService {
    ApiResponse fill(FillDto dto);

    ApiResponse register(ATMDto dto);

    ApiResponse getIntervalReport(String from, String to, Long id);

    ApiResponse getIntervalInputReport(Long id);

    ApiResponse getIntervalOutputReport(Long id);

    ApiResponse getPenniesReport(Long id);

    ApiResponse getFillReport(Long id);

    ApiResponse setMaxLimit(LimitDto dto);

    ApiResponse setMinLimit(LimitDto dto);

    ApiResponse setMaxLimit(Long id, LimitDto dto);

    ApiResponse setMinLimit(Long id, LimitDto dto);

    void sendEmail(ATM atm);
}
