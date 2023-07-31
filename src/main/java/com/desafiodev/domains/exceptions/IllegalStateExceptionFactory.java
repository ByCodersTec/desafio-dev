package com.desafiodev.domains.exceptions;

public final class IllegalStateExceptionFactory {
  private final StringBuilder stringBuilder = new StringBuilder();

  private IllegalStateExceptionFactory(Class<?> tClass) {
    stringBuilder.append("class").append(tClass.getName());
  }

  public static IllegalStateExceptionFactory builder(Class<?> tClass) {
    return new IllegalStateExceptionFactory(tClass);
  }

  public <T> IllegalStateExceptionFactory param(String param, T value) {
    stringBuilder.append(param).append(value.toString());
    return this;
  }

  public IllegalStateExceptionFactory message(String massage) {
    stringBuilder.append(massage);
    return this;
  }

  public IllegalStateException build() {
    return new IllegalStateException(stringBuilder.toString());
  }
}
