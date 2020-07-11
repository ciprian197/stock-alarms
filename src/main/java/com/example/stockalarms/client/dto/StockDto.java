package com.example.stockalarms.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    private String name;
    private String symbol;
    private String type;
    private String currency;
    private String region;

}
