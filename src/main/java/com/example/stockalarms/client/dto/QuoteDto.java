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
public class QuoteDto {

    @JsonProperty("01. symbol")
    private String symbol;
    @JsonProperty("05. price")
    private double price;

}
