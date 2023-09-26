package com.br.cnab.upload.apiuploadfile.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;


public class DateUtils {

    public static String formatDateToYYYYMMDD(String dateString) {
        try {
            SimpleDateFormat inputSdf = new SimpleDateFormat("yyyyMMdd");
            Date date = inputSdf.parse(dateString);

            SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd");
            return outputSdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
