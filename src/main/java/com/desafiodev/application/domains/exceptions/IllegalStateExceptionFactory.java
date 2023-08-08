package com.desafiodev.application.domains.exceptions;

public final class IllegalStateExceptionFactory {
  private final StringBuilder stringBuilder = new StringBuilder();

  private IllegalStateExceptionFactory(Class<?> tClass) {
    stringBuilder.append("class").append(tClass.getName());
  }

  public static IllegalStateExceptionFactory builder(Class<?> tClass) {
    return new IllegalStateExceptionFactory(tClass);
  }

  public <T> IllegalStateExceptionFactory param(String param, T value) {
    stringBuilder.append(param).append(" ").append(value.toString()).append(" ");
    return this;
  }

  public IllegalStateExceptionFactory message(String massage) {
    stringBuilder.append(massage).append(" ");
    return this;
  }

  public IllegalStateException build() {
    return new IllegalStateException(stringBuilder.toString());
  }
}
