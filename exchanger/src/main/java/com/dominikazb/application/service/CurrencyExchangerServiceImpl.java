package com.dominikazb.application.service;

import com.dominikazb.application.parser.UsdResponseParser;
import com.dominikazb.application.provider.NbpClient;
import com.dominikazb.domain.model.UsdResponseJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyExchangerServiceImpl implements CurrencyExchangerService {

    private final NbpClient nbpClient;
    private final ObjectMapper objectMapper;

    public CurrencyExchangerServiceImpl(NbpClient nbpClient, ObjectMapper objectMapper) {
        this.nbpClient = nbpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public BigDecimal getCurrentUsdExchangeValue() {
        try {
            UsdResponseJson result = objectMapper.readValue(nbpClient.getCurrentUsdBalance(), UsdResponseJson.class);
            return UsdResponseParser.getUsdCurrentExchangeRate(result);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
