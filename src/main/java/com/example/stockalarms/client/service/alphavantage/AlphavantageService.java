package com.example.stockalarms.client.service.alphavantage;

import com.example.stockalarms.client.dto.QuoteDto;
import com.example.stockalarms.client.dto.StockDto;

import java.util.List;

public interface AlphavantageService {

    List<StockDto> getStocks(String keyword);

    QuoteDto getQuoteForSymbol(String symbol);

}
