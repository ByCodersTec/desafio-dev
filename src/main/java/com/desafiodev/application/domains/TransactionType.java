package com.desafiodev.application.domains;

import static com.desafiodev.application.domains.TransactionType.MovimentType.*;

import com.desafiodev.application.domains.exceptions.IllegalStateExceptionFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;
import lombok.NonNull;

public enum TransactionType {
  DEBITO("1", ENTRADA),
  BOLETO("2", SAIDA),
  FINANCIAMENTO("3", SAIDA),
  CREDITO("4", ENTRADA),
  RECEBIMENTO_EMPRESTIMO("5", ENTRADA),
  VENDAS("6", ENTRADA),
  RECEBIMENTO_TED("7", ENTRADA),
  RECEBIMENTO_DOC("8", ENTRADA),
  ALUGUEL("9", SAIDA);
  private static final Map<String, TransactionType> map = new HashMap<>();
  private final String cnabPosition;

  static {
    Arrays.stream(TransactionType.values())
        .forEach(transactionType -> map.put(transactionType.cnabPosition, transactionType));
  }

  private final MovimentType movimentType;

  TransactionType(@NonNull String cnabPosition, @NonNull MovimentType movimentType) {
    this.cnabPosition = cnabPosition;
    this.movimentType = movimentType;
  }

  public static Optional<TransactionType> getTransactionType(@NonNull String cnabPosition) {
    return Optional.ofNullable(map.get(cnabPosition));
  }

  public double getValue(double value) {
    return this.movimentType.getValue(value);
  }

  enum MovimentType {
    ENTRADA(x -> x),
    SAIDA(x -> x * -1);
    private final UnaryOperator<Double> function;

    MovimentType(UnaryOperator<Double> function) {
      this.function = function;
    }

    double getValue(double value) {
      if (value < 0)
        throw IllegalStateExceptionFactory.builder(getClass())
            .message("The transaction value, should be a positive value")
            .param("value", value)
            .build();
      return function.apply(value);
    }
  }
}
