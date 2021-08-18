package uz.pdp.appatmsystem.service;

import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.CardDto;
import uz.pdp.appatmsystem.model.FillDto;
import uz.pdp.appatmsystem.model.WithDrawDto;

public interface CardService {
    ApiResponse withdrawMoney(WithDrawDto dto);

    ApiResponse depositMoney(FillDto dto);

    ApiResponse register(CardDto dto);

    ApiResponse activate(String number);
}
