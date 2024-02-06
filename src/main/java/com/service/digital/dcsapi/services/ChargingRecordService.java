package com.service.digital.dcsapi.services;

import com.service.digital.dcsapi.dtos.ChargingRecordsPage;
import com.service.digital.dcsapi.models.ChargingRecord;

import java.util.List;

public interface ChargingRecordService {
    ChargingRecord createCdr(ChargingRecord chargingRecord);

    ChargingRecord findLastCdrById();

    ChargingRecordsPage getAllChargingRecords(int page, int size, String sortProperty, String sortDirection, Long id);
}
