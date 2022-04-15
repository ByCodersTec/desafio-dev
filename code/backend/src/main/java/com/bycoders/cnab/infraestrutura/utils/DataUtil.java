package com.bycoders.cnab.infraestrutura.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataUtil {

	public static LocalDate converterStringParaLocalDate(final String data) {
		return LocalDate.parse(data);
	}

	public static String converterLocalDateForStringWithFormatter(final LocalDate data, final String formato){
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
		return data.format(formatter);
	}
	
	public static LocalDate converterStringParaLocalDateFormatado(final String data) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(data, formatter);
	}

	public static LocalDate converterStringLocalDate(final String data, final String formato){
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
		return LocalDate.parse(data, formatter);
	}


	public static LocalDateTime converterStringLocalDateTime(final String data, final String formato){
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato).withLocale(new Locale("pt", "br"));
		return LocalDateTime.parse(data, formatter);
	}

	public static LocalDateTime converterStringParaLocalDateTime(final String data) {
		return LocalDateTime.parse(data);
	}

	public static LocalDateTime converterStringParaLocalDateTimeFormatado(final String data) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return LocalDateTime.parse(data, formatter);
	}

	
	public static LocalDateTime converterStringParaLocalDateTimeWithFormato(final String data, String formato) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
		return LocalDateTime.parse(data, formatter);
	}

	public static LocalTime converterStringParaLocalTimeFormatado(final String hora) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return LocalTime.parse(hora, formatter);
	}




}
