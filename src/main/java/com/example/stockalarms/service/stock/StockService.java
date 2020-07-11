package com.example.stockalarms.service.stock;

import com.example.stockalarms.dto.StockDto;

import java.util.List;

public interface StockService {
    List<StockDto> getStocks(String keyword);

    double getCurrentPriceForStock(String stockId);
}
