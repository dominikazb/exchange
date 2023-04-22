package com.dominikazb.application.service;

import com.dominikazb.application.service.exceptions.AccountDoesNotExistException;
import com.dominikazb.application.service.exceptions.AccountNotSavedException;
import com.dominikazb.domain.model.Account;
import com.dominikazb.domain.model.AccountDetailsJson;
import com.dominikazb.domain.model.AccountInfo;

import java.util.UUID;

public interface AccountService {

    AccountInfo getAccountDetails(UUID uniqueId) throws AccountDoesNotExistException;
    Account getAccount(UUID uniqueId);
    UUID saveAccount(AccountDetailsJson definition) throws AccountNotSavedException;
    void exchangeBalanceToPln();
    void exchangeBalanceToUsd();
}
