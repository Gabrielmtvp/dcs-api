package com.service.digital.dcsapi.dtos;

import com.service.digital.dcsapi.models.ChargingRecord;
import lombok.Data;

import java.util.List;

@Data
public class ChargingRecordsPage {
    private List<ChargingRecord> records;
    private long totalRecords;

    public ChargingRecordsPage(List<ChargingRecord> records, long totalRecords) {
        this.records = records;
        this.totalRecords = totalRecords;
    }
}
