package br.com.desafiodev.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DataUtils {


    public static LocalDate parseLocalDate(String data) {
        String dataTransacao = data.substring(0, 4) + "-" + data.substring(4, 6) + "-" + data.substring(6, 8);
        return LocalDate.parse(dataTransacao);
    }

    public static LocalTime parseLocalTime(String time) {
        String hora = time.substring(0,2)+":"+ time.substring(2,4) + ":" + time.substring(4, 6);
        return LocalTime.parse(hora);
    }

}
