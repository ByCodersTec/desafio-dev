package com.br.cnab.upload.apiuploadfile.model;


import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class StoreOperationResponse {
    private  String storeName;
    private double totalBalance;

    public StoreOperationResponse(String storeName, double totalBalance) {
        this.storeName = storeName;
        this.totalBalance = totalBalance;
    }

    public String getFormattedTotalBalance() {
        return String.format("R$ %.2f", this.totalBalance);
    }
}

