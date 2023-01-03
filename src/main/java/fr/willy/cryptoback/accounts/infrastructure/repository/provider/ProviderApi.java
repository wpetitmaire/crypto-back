package fr.willy.cryptoback.accounts.infrastructure.repository.provider;

import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;

import java.util.List;

public interface ProviderApi {
    List<AccountEntity> importAccounts();
}
