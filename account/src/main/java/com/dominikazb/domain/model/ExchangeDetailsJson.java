package com.dominikazb.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record ExchangeDetailsJson(Currency currency, BigDecimal currencyValue, UUID accountUniqueId) {
}
