package uz.pdp.appatmsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appatmsystem.domain.Commission;
import uz.pdp.appatmsystem.enums.OperationType;
import uz.pdp.appatmsystem.model.ApiResponse;
import uz.pdp.appatmsystem.model.CommissionDto;
import uz.pdp.appatmsystem.repository.CommissionRepo;
import uz.pdp.appatmsystem.service.CommissionService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommissionServiceImpl implements CommissionService {

    private final CommissionRepo commissionRepo;

    @Override
    public ApiResponse setSameBankCommission(CommissionDto dto) {
        try {
            Optional<Commission> optionalCommission = commissionRepo.findByAdditionalAndKey(dto.isSameBank(), OperationType.valueOf(dto.getKey()));
            if (!optionalCommission.isPresent())
                return new ApiResponse("Commission not found", false);
            Commission commission = optionalCommission.get();
            commission.setValue(dto.getValue());
            commissionRepo.save(commission);
        } catch (Exception e) {
            return new ApiResponse("Operation type format is not correct (Use PUT or GET)", false);
        }
        return null;
    }
}
