package uz.pdp.appatmsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.pdp.appatmsystem.domain.ATMHistory;
import uz.pdp.appatmsystem.model.resp.ATMHistoryRespDto;


import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
@Component
public interface MapstructMapper {

    /**
     * ATM History
     */
    @Mapping(source = "atm.id", target = "atm")
    ATMHistoryRespDto toATMHistoryDto(ATMHistory histories);

    @Mapping(source = "atm.id", target = "atm")
    List<ATMHistoryRespDto> toATMHistoryDto(List<ATMHistory> histories);
}
