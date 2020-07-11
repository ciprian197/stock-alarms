package com.example.stockalarms.controller;

import com.example.stockalarms.dto.StockDto;
import com.example.stockalarms.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping
    public List<StockDto> getStocks(@RequestParam String keyword) {
        return stockService.getStocks(keyword);
    }

}
