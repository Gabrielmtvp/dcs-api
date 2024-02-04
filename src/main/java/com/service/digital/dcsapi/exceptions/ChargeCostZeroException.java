package com.service.digital.dcsapi.exceptions;

public class ChargeCostZeroException extends RuntimeException {

    public ChargeCostZeroException() {
        super("Charge must be greater than zero.");
    }

}
