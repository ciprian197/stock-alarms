package com.example.stockalarms.client.service.alphavantage;

import com.example.stockalarms.client.dto.GlobalQuoteResponse;
import com.example.stockalarms.client.dto.QuoteDto;
import com.example.stockalarms.client.dto.SearchEndpointResponse;
import com.example.stockalarms.client.dto.StockDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Exceptions;

import java.util.List;

@Slf4j
@Service
class AlphaVantageServiceImpl implements AlphaVantageService {

    private static final String KEYWORDS_PARAM_KEY = "keywords";
    private static final String SYMBOL_PARAM_KEY = "symbol";
    private static final String FUNCTION_PARAM_KEY = "function";
    private static final String API_KEY_PARAM_KEY = "apikey";

    private final WebClient alphaVantageClient;
    private final String apiKey;

    public AlphaVantageServiceImpl(final WebClient alphaVantageClient,
                                   @Value("${stock-alarms.alpha-vantage.api-key}") final String apiKey) {
        this.alphaVantageClient = alphaVantageClient;
        this.apiKey = apiKey;
    }

    @Override
    public List<StockDto> getStocks(final String keyword) {
        return alphaVantageClient.get()
                .uri(uriBuilder -> uriBuilder.path("/query")
                        .queryParam(FUNCTION_PARAM_KEY, "SYMBOL_SEARCH")
                        .queryParam(KEYWORDS_PARAM_KEY, keyword)
                        .queryParam(API_KEY_PARAM_KEY, apiKey).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SearchEndpointResponse.class)
                .map(SearchEndpointResponse::getResults)
                .block();
    }


    @Override
    public QuoteDto getQuoteForSymbol(final String symbol) {
        return alphaVantageClient.get()
                .uri(uriBuilder -> uriBuilder.path("/query")
                        .queryParam(FUNCTION_PARAM_KEY, "GLOBAL_QUOTE")
                        .queryParam(SYMBOL_PARAM_KEY, symbol)
                        .queryParam(API_KEY_PARAM_KEY, apiKey).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GlobalQuoteResponse.class)
                .map(this::getQuote)
                .block();
    }

    private QuoteDto getQuote(final GlobalQuoteResponse globalQuoteResponse) {
        final QuoteDto quote = globalQuoteResponse.getQuote();
        if (quote.getSymbol() == null) {
            throw Exceptions.propagate(new RuntimeException("Stock not found"));
        }
        return quote;
    }

}
