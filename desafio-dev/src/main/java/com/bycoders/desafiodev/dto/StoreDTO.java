package com.bycoders.desafiodev.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StoreDTO {

    private String storeName;
    private Double sumValues;

}
