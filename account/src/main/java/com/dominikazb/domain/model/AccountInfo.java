package com.dominikazb.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record AccountInfo(UUID accountId, String userFirstName, String userLastName, BigDecimal plnBalance, BigDecimal usdBalance, Date date) {
}
