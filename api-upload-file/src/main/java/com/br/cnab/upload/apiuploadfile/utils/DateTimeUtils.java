package com.br.cnab.upload.apiuploadfile.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {

    public static String formatTimeInUTC3(String dateTimeString) {

        try {
            SimpleDateFormat inputSdf = new SimpleDateFormat("HHmmss");
            Date date = inputSdf.parse(dateTimeString);

            SimpleDateFormat outputSdf = new SimpleDateFormat("HH:mm:ss");
            TimeZone timeZone = TimeZone.getTimeZone("UTC-3");
            outputSdf.setTimeZone(timeZone);

            return outputSdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
