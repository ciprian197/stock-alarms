package com.example.stockalarms.service.stock;

import com.example.stockalarms.client.service.alphavantage.AlphavantageService;
import com.example.stockalarms.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class StockServiceImpl implements StockService {

    private final AlphavantageService alphavantageService;

    @Override
    public List<StockDto> getStocks(final String keyword) {
        return alphavantageService.getStocks(keyword).stream()
                .map(this::toInternalDto)
                .collect(Collectors.toList());
    }

    @Override
    public double getCurrentPriceForStock(final String stockId) {
        return alphavantageService.getQuoteForSymbol(stockId).getPrice();
    }

    private StockDto toInternalDto(final com.example.stockalarms.client.dto.StockDto stockDto) {
        return StockDto.builder()
                .symbol(stockDto.getSymbol())
                .name(stockDto.getName())
                .currency(stockDto.getCurrency())
                .region(stockDto.getRegion())
                .type(stockDto.getType()).build();
    }

}
