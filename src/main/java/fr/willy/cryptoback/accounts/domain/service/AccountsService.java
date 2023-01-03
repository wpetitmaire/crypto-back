package fr.willy.cryptoback.accounts.domain.service;

import fr.willy.cryptoback.accounts.domain.entity.Account;
import fr.willy.cryptoback.accounts.domain.enums.Provider;

import java.util.List;

public interface AccountsService {
    void refreshAllAccountsFromProvider(Provider provider);

    List<Account> retrieveAccountsFromProvider(Provider provider);
}
