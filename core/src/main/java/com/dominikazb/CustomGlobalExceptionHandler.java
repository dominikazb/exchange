package com.dominikazb;

import com.dominikazb.application.service.exceptions.AccountDoesNotExistException;
import com.dominikazb.application.service.exceptions.AccountNotSavedException;
import com.dominikazb.application.exceptions.CurrencyExchangeApiException;
import com.dominikazb.application.service.exceptions.IncorrectPayloadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(AccountNotSavedException.class)
    public ResponseEntity<?> accountNotSavedException(AccountNotSavedException e) {
        return new ResponseEntity<>("account-was-not-created", HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(AccountDoesNotExistException.class)
    public ResponseEntity<?> accountDoesNotExistException(AccountDoesNotExistException e) {
        return new ResponseEntity<>("account-does-not-exist", HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> uuidStringTooLarge(IllegalArgumentException e) {
        return new ResponseEntity<>("invalid-number-format", HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(CurrencyExchangeApiException.class)
    public ResponseEntity<?> currencyExchangeApiException(CurrencyExchangeApiException e) {
        return new ResponseEntity<>("usd-currency-actual-exchange-value-not-fetched", HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(IncorrectPayloadException.class)
    public ResponseEntity<?> incorrectPayloadException(IncorrectPayloadException e) {
        return new ResponseEntity<>("incorrect-payload-exception", HttpStatus.I_AM_A_TEAPOT);
    }
}
