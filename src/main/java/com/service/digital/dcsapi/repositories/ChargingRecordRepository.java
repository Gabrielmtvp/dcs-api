package com.service.digital.dcsapi.repositories;

import com.service.digital.dcsapi.models.ChargingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChargingRecordRepository extends JpaRepository<ChargingRecord, Long> {
    <Optional> ChargingRecord findByVehicle(String vehicle);

}
