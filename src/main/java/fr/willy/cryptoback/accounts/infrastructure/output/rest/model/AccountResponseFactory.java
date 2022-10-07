package fr.willy.cryptoback.accounts.infrastructure.output.rest.model;

import fr.willy.cryptoback.accounts.domain.entity.Account;

public class AccountResponseFactory {
    public static AccountResponse construct(Account domainAccount) {
        return AccountResponse.builder()
            .id(domainAccount.getId())
            .code(domainAccount.getCode())
            .libel(domainAccount.getLibel())
            .price(domainAccount.getPrice())
            .balance(
                AccountResponse.BalanceResponse.builder()
                    .value(domainAccount.getBalance())
                    .exponent(domainAccount.getExponent())
                    .build()
            )
            .build();
    }
}
