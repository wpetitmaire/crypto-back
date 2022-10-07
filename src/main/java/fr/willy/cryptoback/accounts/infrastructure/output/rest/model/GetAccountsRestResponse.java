package fr.willy.cryptoback.accounts.infrastructure.output.rest.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class GetAccountsRestResponse {
    private LocalDateTime retrieveDate;
    private List<AccountResponse> accounts;
}
