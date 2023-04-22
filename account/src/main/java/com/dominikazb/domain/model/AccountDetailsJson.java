package com.dominikazb.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record AccountDetailsJson( String firstName, String lastName, BigDecimal initialBalance) {
}
