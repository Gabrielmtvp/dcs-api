package com.service.digital.dcsapi.repositories;

import com.service.digital.dcsapi.models.ChargingPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingPointRepository extends JpaRepository<ChargingPoint, Long> {

}
