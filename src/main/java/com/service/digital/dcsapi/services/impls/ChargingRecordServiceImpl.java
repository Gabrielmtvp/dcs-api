package com.service.digital.dcsapi.services.impls;

import com.service.digital.dcsapi.exceptions.ChargeCostZeroException;
import com.service.digital.dcsapi.exceptions.EndDateLowerThanStartDateException;
import com.service.digital.dcsapi.exceptions.LastChargeEndDateGreaterThanNewStartDateException;
import com.service.digital.dcsapi.models.ChargingRecord;
import com.service.digital.dcsapi.repositories.ChargingRecordRepository;
import com.service.digital.dcsapi.services.ChargingRecordService;
import com.service.digital.dcsapi.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChargingRecordServiceImpl implements ChargingRecordService {

    @Autowired
    private ChargingRecordRepository chargingRecordRepository;

    @Override
    public ChargingRecord createCdr(ChargingRecord chargingRecord) {
        boolean isTotalCostZero = chargingRecord.getTotalCost() == 0;
        if(isTotalCostZero) {
            throw new ChargeCostZeroException();
        }

        boolean isEndDateLowerThanStartDate = DateTimeUtils.isEndDateLowerThanStartDate(chargingRecord.getStartDate(), chargingRecord.getEndDate());
        if(isEndDateLowerThanStartDate){
            throw new EndDateLowerThanStartDateException();
        }

        ChargingRecord lastCdr = this.chargingRecordRepository.findTopByOrderByIdDesc();
        if(lastCdr != null) {
            boolean isLastChargeEndDateGreaterThanNewStartDate = DateTimeUtils.isLastChargeEndDateGreaterThanNewStartDate(chargingRecord.getStartDate(), lastCdr.getEndDate());
            if(isLastChargeEndDateGreaterThanNewStartDate) {
                throw new LastChargeEndDateGreaterThanNewStartDateException();
            }
        }

        return this.chargingRecordRepository.save(chargingRecord);
    }

    public ChargingRecord findLastCdrById() {
        return this.chargingRecordRepository.findTopByOrderByIdDesc();
    }

    public List<ChargingRecord> findAllCdrs() {
        return this.chargingRecordRepository.findAll();
    }

    public List<ChargingRecord> getAllChargingRecords(int page, int size, String sortProperty, String sortDirection, Long id) {
        Sort.Direction sortDirect = sortDirection.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, sortDirect, sortProperty);

        return chargingRecordRepository.findAll(pageable).getContent();
    }
}
