package com.dominikazb.infrastructure.config;

import com.dominikazb.application.provider.NbpClient;
import com.dominikazb.application.service.CurrencyExchangerService;
import com.dominikazb.application.service.CurrencyExchangerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangerBoundedContext {

    @Bean
    CurrencyExchangerService currencyExchangerService(NbpClient nbpClient, ObjectMapper objectMapper) {
        return new CurrencyExchangerServiceImpl(nbpClient, objectMapper);
    }
}
