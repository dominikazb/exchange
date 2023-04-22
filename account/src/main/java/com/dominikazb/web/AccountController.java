package com.dominikazb.web;

import com.dominikazb.application.exceptions.CurrencyExchangeApiException;
import com.dominikazb.application.service.AccountService;
import com.dominikazb.application.service.CurrencyExchangerService;
import com.dominikazb.application.service.exceptions.AccountDoesNotExistException;
import com.dominikazb.application.service.exceptions.AccountNotSavedException;
import com.dominikazb.application.service.exceptions.IncorrectPayloadException;
import com.dominikazb.domain.model.Account;
import com.dominikazb.domain.model.AccountDetailsJson;
import com.dominikazb.domain.model.AccountInfo;
import com.dominikazb.domain.model.ExchangeDetailsJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final CurrencyExchangerService currencyExchangerService;


    @GetMapping("/{uniqueId}")
    public AccountInfo hello(@PathVariable("uniqueId") String uniqueId) throws AccountDoesNotExistException, IllegalArgumentException {
        return accountService.getAccountDetails(UUID.fromString(uniqueId));
    }


    @PostMapping("/create")
    public ResponseEntity<UUID> createAccount(@RequestBody AccountDetailsJson definition) throws AccountNotSavedException {
        UUID uniqueId = accountService.saveAccount(definition);
        return new ResponseEntity<>(uniqueId, HttpStatus.OK);
    }


    @PostMapping("/exchange-pln-to-usd")
    public void exchangePolishZlotyToUsd(@RequestBody ExchangeDetailsJson definition) throws JsonProcessingException, CurrencyExchangeApiException, IncorrectPayloadException {

        BigDecimal result = currencyExchangerService.getCurrentUsdExchangeValue();

        if (Objects.isNull(result)) {
            throw new CurrencyExchangeApiException();
        }

        if (payloadIsIncorrect(definition)) {
            throw new IncorrectPayloadException();
        }

        Account account = accountService.getAccount(definition.accountUniqueId());

        // TODO: funkcjonalność zmiany waluty i zapisania do db
        // TODO: być może sprawdzenie czy jest wystarczająca kwota na koncie, żeby zrobić przewalutowanie





    }

    @PostMapping("/exchange-usd-to-pln/{amount}")
    public void exchangeUsdToPolishZloty(@PathVariable("amount") BigDecimal amount) {

        // TODO: funkcjonalność zmiany waluty i zapisania do db
        // TODO: być może sprawdzenie czy jest wystarczająca kwota na koncie, żeby zrobić przewalutowanie

    }




    private boolean payloadIsIncorrect(ExchangeDetailsJson definition) {
        return definition.currency() == null || definition.currencyValue() == null || definition.accountUniqueId() == null;
    }
}
