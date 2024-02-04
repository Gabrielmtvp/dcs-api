package com.service.digital.dcsapi.utils;

import java.time.LocalDateTime;

public abstract class DateTimeUtils {

    public static boolean isEndDateLowerThanStartDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate.isBefore(startDate)) {
            return true;
        }
        return false;
    }

    public static boolean isLastChargeEndDateGreaterThanNewStartDate(LocalDateTime newStartDate, LocalDateTime lastEndDate) {
        if (lastEndDate.isAfter(newStartDate) || lastEndDate.isEqual(newStartDate)) {
            return true;
        }
        return false;
    }
}