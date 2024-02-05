package com.service.digital.dcsapi.services.impls;

import com.service.digital.dcsapi.models.ChargingPoint;
import com.service.digital.dcsapi.repositories.ChargingPointRepository;
import com.service.digital.dcsapi.services.ChargingPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChargingPointServiceImpl implements ChargingPointService {

    @Autowired
    private ChargingPointRepository chargingPointRepository;

    @Override
    public ChargingPoint createCpo(ChargingPoint chargingPoint) {
        return this.chargingPointRepository.save(chargingPoint);
    }
}
