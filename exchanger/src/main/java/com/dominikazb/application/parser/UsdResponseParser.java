package com.dominikazb.application.parser;

import com.dominikazb.domain.model.RateJson;
import com.dominikazb.domain.model.UsdResponseJson;

import java.math.BigDecimal;
import java.util.Objects;

public class UsdResponseParser {

    public static BigDecimal getUsdCurrentExchangeRate(UsdResponseJson response) {

        BigDecimal result = null;

        if (response.rates().size() > 0) {

            RateJson rate = response.rates().get(0);

            if (Objects.nonNull(rate.mid())) {
                result = rate.mid();
            }
        }

        return result;
    }
}
