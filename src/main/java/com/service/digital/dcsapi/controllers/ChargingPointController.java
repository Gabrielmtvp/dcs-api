package com.service.digital.dcsapi.controllers;

import com.service.digital.dcsapi.models.ChargingPoint;
import com.service.digital.dcsapi.services.impls.ChargingPointServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/point")
@CrossOrigin(origins = "*")
public class ChargingPointController {

    @Autowired
    private ChargingPointServiceImpl chargingPointServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ChargingPoint chargingPoint) {
        try{
            var newChargePoint = this.chargingPointServiceImpl.createCpo(chargingPoint);
            return ResponseEntity.ok().body(newChargePoint);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
