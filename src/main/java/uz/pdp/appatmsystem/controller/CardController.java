package uz.pdp.appatmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.CardDto;
import uz.pdp.appatmsystem.model.FillDto;
import uz.pdp.appatmsystem.model.WithDrawDto;
import uz.pdp.appatmsystem.service.CardService;

import javax.validation.Valid;

import static uz.pdp.appatmsystem.controller.ApiController.API_PATH;

@RestController
@RequestMapping(API_PATH + "/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> register(@Valid @RequestBody CardDto dto) {
        ApiResponse apiResponse = cardService.register(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 201 : 400).body(apiResponse);
    }

    @PostMapping("/withdraw/money")
    public ResponseEntity<?> withdrawMoney(@Valid @RequestBody WithDrawDto dto) {
        ApiResponse apiResponse = cardService.withdrawMoney(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

    @PostMapping("/deposit/money")
    public ResponseEntity<?> depositMoney(@Valid @RequestBody FillDto dto) {
        ApiResponse apiResponse = cardService.depositMoney(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 401).body(apiResponse);
    }

    @PutMapping("/activate/{number}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> activate(@PathVariable String number) {
        ApiResponse apiResponse = cardService.activate(number);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }
}
