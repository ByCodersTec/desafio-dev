package com.github.filipednb.financialtransactions.api.transaction;

import com.github.filipednb.financialtransactions.api.enums.TransactionType;
import com.github.filipednb.financialtransactions.file.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "amount", source = "valor")
    @Mapping(target = "numCard", source = "cartao")
    @Mapping(target = "owner.name", source = "donoLoja")
    @Mapping(target = "store.name", source = "nomeLoja")
    @Mapping(target = "document.numDocument", source = "cpf")
    @Mapping(target = "transactionType", source = "dto", qualifiedByName = "dtoToTransactionType")
    @Mapping(target = "dateOccurrence", source = "dto", qualifiedByName = "dtoToDateOccurrence")
    TransactionEntity toEntity(TransactionDTO dto);

    @Named("dtoToDateOccurrence")
    default LocalDateTime dtoToDateOccurrence(TransactionDTO dto) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        return LocalDateTime.of(LocalDate.parse(dto.getData(),dateFormatter),
                LocalTime.parse(dto.getHora(), timeFormatter));
    }

    @Named("dtoToTransactionType")
    default TransactionType dtoToTransactionType(TransactionDTO dto) {
        return TransactionType.getByCode(dto.getTipo());
    }
}
