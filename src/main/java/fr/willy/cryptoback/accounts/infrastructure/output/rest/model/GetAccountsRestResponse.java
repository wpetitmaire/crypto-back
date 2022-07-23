package fr.willy.cryptoback.accounts.infrastructure.output.rest.model;

import fr.willy.cryptoback.accounts.domain.entity.Accounts;

import java.time.LocalDateTime;
import java.util.Set;

public record GetAccountsRestResponse(
    LocalDateTime retrieveDate,
    Set<Accounts> accounts
) {
}