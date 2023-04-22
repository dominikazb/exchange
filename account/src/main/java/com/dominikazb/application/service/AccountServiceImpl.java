package com.dominikazb.application.service;

import com.dominikazb.application.service.exceptions.AccountDoesNotExistException;
import com.dominikazb.application.service.exceptions.AccountNotSavedException;
import com.dominikazb.domain.model.Account;
import com.dominikazb.domain.model.AccountDetailsJson;
import com.dominikazb.domain.model.AccountInfo;
import com.dominikazb.infrastructure.persistence.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountInfo getAccountDetails(UUID uniqueId) throws AccountDoesNotExistException {
        if (accountRepository.getAccountByUniqueId(uniqueId).isPresent()) {
            Account account = accountRepository.getAccountByUniqueId(uniqueId).get();
            return new AccountInfo(
                    uniqueId,
                    account.getUserFirstName(),
                    account.getUserLastName(),
                    account.getBalancePln(),
                    account.getBalanceUsd(),
                    new Date()
            );
        }
        throw new AccountDoesNotExistException();
    }

    @Override
    public Account getAccount(UUID uniqueId) {
        return accountRepository.getAccountByUniqueId(uniqueId).orElse(null);
    }


    @Override
    public UUID saveAccount(AccountDetailsJson definition) throws AccountNotSavedException {

        if (accountDetailsCorrect(definition)) {

            UUID uniqueId = UUID.randomUUID();

            try {
                accountRepository.save(Account.builder()
                        .uniqueId(uniqueId)
                        .userFirstName(definition.firstName())
                        .userLastName(definition.lastName())
                        .balancePln(definition.initialBalance())
                        .balanceUsd(BigDecimal.valueOf(0))
                        .build());
            } catch (Exception e) {
                throw new AccountNotSavedException();
            }

            return uniqueId;
        }

        throw new AccountNotSavedException();
    }




    @Override
    public void exchangeBalanceToPln() {




    }

    @Override
    public void exchangeBalanceToUsd() {

    }


    private boolean accountDetailsCorrect(AccountDetailsJson accountDetailsJson) {
        return !Objects.isNull(accountDetailsJson) &&
                StringUtils.isNotBlank(accountDetailsJson.firstName()) &&
                StringUtils.isNotBlank(accountDetailsJson.lastName()) &&
                accountDetailsJson.initialBalance() != null;
    }
}
