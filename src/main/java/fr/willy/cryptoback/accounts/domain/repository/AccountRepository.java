package fr.willy.cryptoback.accounts.domain.repository;

import fr.willy.cryptoback.accounts.domain.entity.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> retrieveAccounts();
    void refreshAccounts();

}
