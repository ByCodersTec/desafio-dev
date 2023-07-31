package com.desafiodev.domains;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

import com.desafiodev.domains.exceptions.IllegalStateExceptionFactory;
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
  String type;
  String date;
  String value;
  String cpf;
  String creditCard;
  String hour;
  String owner;
  String storeName;

  private Cnab(@NonNull String line) {
    String[] array = line.split("");

    if (array.length != LINE_LENGTH)
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("The line must has 80 characters")
          .param("line", line)
          .build();

    this.type = array[TYPE_INDEX];
    this.date = collectFrom(array, DATE_INDEX);
    this.value = StringUtils.stripStart(collectFrom(array, VALUE_INDEX), "0");
    this.cpf = collectFrom(array, CPF_INDEX);
    this.creditCard = collectFrom(array, CREDIT_CARD_INDEX);
    this.hour = collectFrom(array, HOUR_INDEX);
    this.owner = collectFrom(array, OWNER_INDEX).trim();
    this.storeName = collectFrom(array, STORE_NAME_INDEX).trim();
  }

  private String collectFrom(String[] array, int[] index) {
    return stream(array, index[0], index[1]).collect(joining());
  }

  public static Cnab getInstance(@NonNull String line) {
    return new Cnab(line);
  }
}
