package com.dominikazb.infrastructure.persistence;

import com.dominikazb.domain.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> getAccountByUniqueId(UUID uniqueId);

}
