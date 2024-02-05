package com.service.digital.dcsapi.modules.cdo.useCases;

import com.service.digital.dcsapi.exceptions.ChargeCostZeroException;
import com.service.digital.dcsapi.exceptions.EndDateLowerThanStartDateException;
import com.service.digital.dcsapi.models.ChargingRecord;
import com.service.digital.dcsapi.models.ChargingPoint;
import com.service.digital.dcsapi.repositories.ChargingRecordRepository;
import com.service.digital.dcsapi.services.impls.ChargingRecordServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class CreateCrdUseCaseTest {
    @InjectMocks
    private ChargingRecordServiceImpl chargingRecordServiceImpl;

    @Mock
    ChargingRecordRepository chargingRecordRepository;

    @BeforeEach
    public void setUp() {
        ChargingPoint chargingPoint = new ChargingPoint(1L, "Digital Charging Solutions");
        LocalDateTime startDate = LocalDateTime.of(2024, 02, 03, 10, 10);
        LocalDateTime endDate = LocalDateTime.of(2024, 02, 03, 10, 20);
        new ChargingRecord(1L,"TST0000", startDate, endDate, 10, chargingPoint, 1L);
    }

    @Test()
    @DisplayName("Should not be able to create cdr with end date lower than start date")
    public void should_not_be_able_to_create_cdr_with_end_date_lower_than_start_date() {
        ChargingPoint chargingPoint = new ChargingPoint(1L, "Digital Charging Solutions");
        LocalDateTime startDate = LocalDateTime.of(2024, 02, 03, 10, 10);
        LocalDateTime endDate = LocalDateTime.of(2024, 02, 03, 10, 00);
        ChargingRecord chargingRecord = new ChargingRecord(1L,"TST0000", startDate, endDate, 10, chargingPoint, 1L);

        Throwable exception = Assertions.assertThrows(EndDateLowerThanStartDateException.class, () -> chargingRecordServiceImpl.createCdr(chargingRecord));
        Assertions.assertEquals("End time is lower than start time.", exception.getMessage());
    }

    @Test()
    @DisplayName("Should not be able to create cdr with end date lower than start date")
    public void should_not_be_able_to_create_cdr_with_cost_equal_zero() {
        ChargingPoint chargingPoint = new ChargingPoint(1L, "Digital Charging Solutions");
        LocalDateTime startDate = LocalDateTime.of(2024, 02, 03, 10, 10);
        LocalDateTime endDate = LocalDateTime.of(2024, 02, 03, 10, 20);
        ChargingRecord chargingRecord = new ChargingRecord(1L,"TST0000", startDate, endDate, 0, chargingPoint, 1L);

        Throwable exception = Assertions.assertThrows(ChargeCostZeroException.class, () -> chargingRecordServiceImpl.createCdr(chargingRecord));
        Assertions.assertEquals("Charge must be greater than zero.", exception.getMessage());
    }

//    @Test()
//    @DisplayName("Should not be able to create cdr with last charge end date greater than new start date")
//    public void should_not_be_able_to_create_cdr_with_last_charge_end_date_greater_new_start_date() {
//        Cpo cpo = new Cpo(1L, "Digital Charging Solutions");
//        LocalDateTime startDate = LocalDateTime.of(1200, 02, 03, 10, 10);
//        LocalDateTime endDate = LocalDateTime.of(1200, 02, 03, 10, 15);
//        Cdr cdr = new Cdr(1L,"TST0000", startDate, endDate, 10, cpo, 1L);
//
//        Throwable exception = Assertions.assertThrows(LastChargeEndDateGreaterThanNewStartDateException.class, () ->chargeServiceImpl.createCdr(cdr));
//        Assertions.assertEquals("The last charge End Date is greater than new Start Date.", exception.getMessage());
//    }

}
