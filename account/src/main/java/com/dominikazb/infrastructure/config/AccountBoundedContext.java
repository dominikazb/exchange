package com.dominikazb.infrastructure.config;

import com.dominikazb.application.service.AccountService;
import com.dominikazb.application.service.AccountServiceImpl;
import com.dominikazb.infrastructure.persistence.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountBoundedContext {

    @Bean
    AccountService accountService(AccountRepository accountRepository) {
        return new AccountServiceImpl(accountRepository);
    }
}
