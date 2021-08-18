package uz.pdp.appatmsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.CommissionDto;
import uz.pdp.appatmsystem.service.CommissionService;

import static uz.pdp.appatmsystem.controller.ApiController.API_PATH;

@RestController
@RequestMapping(API_PATH + "/commission")
@RequiredArgsConstructor
public class CommissionController {
    private final CommissionService commissionService;

    @PostMapping("/set/same/bank")
    @PreAuthorize("hasRole('DIRECTOR')")
    public ResponseEntity<?> setSameBankCommission(@RequestBody CommissionDto dto) {
        ApiResponse apiResponse = commissionService.setSameBankCommission(dto);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }

}
