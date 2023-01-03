package fr.willy.cryptoback.accounts.domain.repository;

import fr.willy.cryptoback.accounts.domain.entity.Account;
import fr.willy.cryptoback.accounts.domain.enums.Provider;

import java.util.List;

public interface AccountRepository {
    List<Account> retrieveAccountsFromProvider(Provider provider);

    void refreshAccountsFromProvider(Provider provider);

}
