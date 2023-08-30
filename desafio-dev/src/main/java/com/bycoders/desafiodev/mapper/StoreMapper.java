package com.bycoders.desafiodev.mapper;

import com.bycoders.desafiodev.dto.StoreDTO;

public class StoreMapper {


    public static StoreDTO toResponse(String storeName, double totalValue) {

        return StoreDTO.builder()
                .storeName(storeName)
                .sumValues(totalValue)
                .build();
    }
}
