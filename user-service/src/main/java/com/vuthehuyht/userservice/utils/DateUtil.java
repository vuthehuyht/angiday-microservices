package com.vuthehuyht.userservice.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static Date toDate(String dateString) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        } catch (ParseException e) {
            log.error("Parse exception: {}", e.getMessage());
            return null;
        }
    }
}
