package com.example.stockalarms.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    @JsonProperty("1. symbol")
    private String symbol;
    @JsonProperty("2. name")
    private String name;
    @JsonProperty("3. type")
    private String type;
    @JsonProperty("4. region")
    private String region;
    @JsonProperty("8. currency")
    private String currency;

}
