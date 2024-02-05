package com.service.digital.dcsapi.controllers;

import com.service.digital.dcsapi.models.ChargingRecord;
import com.service.digital.dcsapi.services.impls.ChargingRecordServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/charge")
public class ChargingRecordController {

    @Autowired
    private ChargingRecordServiceImpl chargingRecordServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ChargingRecord chargingRecord) {
        try{
            var newChargeData = this.chargingRecordServiceImpl.createCdr(chargingRecord);
            return ResponseEntity.ok().body(newChargeData);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/lastCdr")
    public ResponseEntity<Object> findLastCdr() {
        try {
            var lastCdr = this.chargingRecordServiceImpl.findLastCdrById();
            return ResponseEntity.ok().body(lastCdr);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("allCdr")
    public ResponseEntity<Object> allCdr() {
        try {
            var allCdrs = this.chargingRecordServiceImpl.findAllCdrs();
            return ResponseEntity.ok().body(allCdrs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("getAllChargingRecords")
    public ResponseEntity<?> getAllChargingRecords(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            @RequestParam(name = "sortProperty", defaultValue = "startDate") String sortProperty,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection,
            @RequestParam(name = "id", required = false) Long id) {
        try {
            var allCdrs = this.chargingRecordServiceImpl.getAllChargingRecords(page, size, sortProperty, sortDirection, id);
            return ResponseEntity.ok().body(allCdrs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
