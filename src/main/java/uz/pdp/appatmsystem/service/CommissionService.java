package uz.pdp.appatmsystem.service;

import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.CommissionDto;

public interface CommissionService {

    ApiResponse setSameBankCommission(CommissionDto dto);
}
