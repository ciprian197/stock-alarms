package com.example.stockalarms.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
public class AlphaVantageClientConfig {

    @Bean
    public WebClient alphaVantageClient() {
        return WebClient
                .builder()
                .baseUrl("https://www.alphavantage.co")
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().wiretap(true)
                ))
                .build();
    }

}
