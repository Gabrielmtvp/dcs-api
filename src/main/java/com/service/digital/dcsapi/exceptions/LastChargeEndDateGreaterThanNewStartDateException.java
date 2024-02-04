package com.service.digital.dcsapi.exceptions;

public class LastChargeEndDateGreaterThanNewStartDateException extends RuntimeException {

    public LastChargeEndDateGreaterThanNewStartDateException() {
        super("The last charge End Date is greater than new Start Date.");
    }

}
