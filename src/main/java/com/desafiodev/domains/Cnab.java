package com.desafiodev.domains;

import static com.desafiodev.domains.TransactionType.getTransactionType;
import static com.desafiodev.domains.exceptions.IllegalStateExceptionFactory.builder;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang.StringUtils.isNumeric;

import com.desafiodev.domains.exceptions.IllegalStateExceptionFactory;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.NonNull;
import lombok.Value;
import org.apache.commons.lang.StringUtils;

@Value
public class Cnab {

  private static final int LINE_LENGTH = 80;
  private static final int TYPE_INDEX = 0;
  private static final int[] DATE_INDEX = new int[] {1, 9};
  private static final int[] VALUE_INDEX = new int[] {9, 19};
  private static final int[] CPF_INDEX = new int[] {19, 30};
  private static final int[] CREDIT_CARD_INDEX = new int[] {30, 42};
  private static final int[] HOUR_INDEX = new int[] {42, 48};
  private static final int[] OWNER_INDEX = new int[] {48, 62};
  private static final int[] STORE_NAME_INDEX = new int[] {62, 80};
  TransactionType type;
  Instant instant;
  double value;
  String cpf;
  String creditCard;
  String owner;
  String storeName;

  private Cnab(@NonNull String line) {
    String[] array = line.split("");

    if (array.length != LINE_LENGTH)
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("The line must has 80 characters")
          .param("line", line)
          .build();

    this.type = parseTransactionType(array[TYPE_INDEX]);
    this.instant = parseDateHour(collectFrom(array, DATE_INDEX), collectFrom(array, HOUR_INDEX));
    this.value = parseValue(collectFrom(array, VALUE_INDEX));
    this.cpf = collectFrom(array, CPF_INDEX);
    this.creditCard = collectFrom(array, CREDIT_CARD_INDEX);
    this.owner = collectFrom(array, OWNER_INDEX).trim();
    this.storeName = collectFrom(array, STORE_NAME_INDEX).trim();
  }

  private String collectFrom(String[] array, int[] index) {
    return stream(array, index[0], index[1]).collect(joining());
  }

  private TransactionType parseTransactionType(String type) {
    return getTransactionType(type)
        .orElseThrow(
            () ->
                builder(getClass())
                    .message("Transaction type not found")
                    .param("typeInt", type)
                    .build());
  }

  private Instant parseDateHour(@NonNull String date, @NonNull String hour) {
    DateTimeFormatter formatter = ofPattern("yyyyMMddHHmmss");
    try {
      LocalDateTime localDateTime = LocalDateTime.parse(date.concat(hour), formatter);
      return Instant.parse(localDateTime.toString().concat(".00Z"))
          .atZone(ZoneId.of("America/Sao_Paulo"))
          .toInstant();
    } catch (DateTimeException e) {
      throw builder(getClass())
          .message("Date invalid")
          .param("exception", e)
          .param("date", date)
          .param("hour", hour)
          .build();
    }
  }

  private double parseValue(@NonNull String value) {
    String number = StringUtils.stripStart(value, "0");
    if (number.isEmpty()) return 0;
    if (!isNumeric(number))
      throw builder(getClass()).message("Value invalid").param("value", value).build();

    double result = Double.parseDouble(number);

    if (result != 0) return result / 100;

    return result;
  }

  public static Cnab getInstance(@NonNull String line) {
    return new Cnab(line);
  }
}
