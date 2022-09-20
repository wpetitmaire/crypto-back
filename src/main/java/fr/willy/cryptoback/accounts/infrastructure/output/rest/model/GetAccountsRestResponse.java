package fr.willy.cryptoback.accounts.infrastructure.output.rest.model;

import fr.willy.cryptoback.accounts.domain.entity.Account;

import java.time.LocalDateTime;
import java.util.List;

public record GetAccountsRestResponse(
    LocalDateTime retrieveDate,
    List<Account> accounts
) {
}