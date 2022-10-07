package fr.willy.cryptoback.accounts.infrastructure.output.rest.model;

import fr.willy.cryptoback.accounts.domain.entity.Account;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GetAccountsRestResponseFactory {

    public static GetAccountsRestResponse construct(List<Account> domainAccounts) {
        return GetAccountsRestResponse.builder()
            .retrieveDate(LocalDateTime.now())
            .accounts(
                domainAccounts.stream()
                    .map(AccountResponseFactory::construct)
                    .collect(toList())
            )
            .build();
    }

}
