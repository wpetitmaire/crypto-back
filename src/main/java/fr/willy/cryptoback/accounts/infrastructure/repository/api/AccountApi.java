package fr.willy.cryptoback.accounts.infrastructure.repository.api;

import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;

import java.math.BigDecimal;
import java.util.List;

public interface AccountApi {
    List<AccountEntity> importAccounts();

    String getProviderName();

    BigDecimal getCurrencyPrice(String currency);
}
