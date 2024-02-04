package com.service.digital.dcsapi.exceptions;

public class EndDateLowerThanStartDateException extends RuntimeException {

    public EndDateLowerThanStartDateException() {
        super("End time is lower than start time.");
    }

}
