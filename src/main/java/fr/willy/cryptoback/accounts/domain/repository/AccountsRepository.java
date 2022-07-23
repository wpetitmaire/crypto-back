package fr.willy.cryptoback.accounts.domain.repository;

import fr.willy.cryptoback.accounts.domain.entity.Accounts;

import java.util.List;

public interface AccountsRepository {
    List<Accounts> retrieveAccounts();

    default void refreshAccounts() {
    }
}
