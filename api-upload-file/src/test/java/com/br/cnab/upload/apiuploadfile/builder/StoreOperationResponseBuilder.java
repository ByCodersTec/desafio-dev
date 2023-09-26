package com.br.cnab.upload.apiuploadfile.builder;

import com.br.cnab.upload.apiuploadfile.model.StoreOperationResponse;

import java.util.List;

public class StoreOperationResponseBuilder {

    private StoreOperationResponse storeOperationResponse;

    public static StoreOperationResponseBuilder storeOperationResponseBuilder() {
        StoreOperationResponseBuilder builder = new StoreOperationResponseBuilder();

        builder.storeOperationResponse = StoreOperationResponse.builder()
                .storeName("BAR DO JOÃO")
                .totalBalance(152.0)
                .build();

        return builder;
    }

    public StoreOperationResponse build() {
        return storeOperationResponse;
    }

    public List<StoreOperationResponse> buildList() {
        return List.of(storeOperationResponse);
    }
}
