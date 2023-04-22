package com.dominikazb.application.provider;

import com.dominikazb.domain.model.UsdResponseJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "nbpExchangeValuesClient", url="http://api.nbp.pl/api/exchangerates/rates")
public interface NbpClient {

    @GetMapping("/A/USD/")
    String getCurrentUsdBalance();
}

