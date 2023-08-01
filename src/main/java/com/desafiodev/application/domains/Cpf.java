package com.desafiodev.application.domains;

import com.desafiodev.application.domains.exceptions.IllegalStateExceptionFactory;
import lombok.NonNull;
import lombok.Value;

@Value
public class Cpf {
  private static final int CPF_LENGTH = 11;
  String number;

  private Cpf(@NonNull String number) {
    if (number.isEmpty())
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("CPF can't be empty")
          .param("number", number)
          .build();
    if (number.length() != CPF_LENGTH)
      throw IllegalStateExceptionFactory.builder(getClass())
          .message("CPF must has 11 numbers")
          .param("number", number)
          .build();
    this.number = number;
  }

  public static Cpf newInstance(@NonNull String number) {
    return new Cpf(number);
  }
}
