package com.dominikazb.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface CurrencyExchangerService {

    BigDecimal getCurrentUsdExchangeValue() throws JsonProcessingException;
}
