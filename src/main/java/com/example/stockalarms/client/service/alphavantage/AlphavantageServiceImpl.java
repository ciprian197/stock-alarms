package com.example.stockalarms.client.service.alphavantage;

import com.example.stockalarms.client.dto.QuoteDto;
import com.example.stockalarms.client.dto.StockDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
class AlphavantageServiceImpl implements AlphavantageService {

    @Override
    public List<StockDto> getStocks(final String keyword) {
        return Arrays.asList(StockDto.builder()
                .currency("USD")
                .name("The Boeing Company")
                .region("United States")
                .symbol("BA")
                .build());
    }

    @Override
    public QuoteDto getQuoteForSymbol(final String symbol) {
        return QuoteDto.builder()
                .symbol(symbol)
                .price(118.3500).build();
    }

}
