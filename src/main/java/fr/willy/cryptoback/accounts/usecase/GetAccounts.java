package fr.willy.cryptoback.accounts.usecase;

import fr.willy.cryptoback.accounts.domain.entity.Accounts;
import fr.willy.cryptoback.accounts.domain.repository.AccountsRepository;
import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAccounts {

    final AccountsRepository accountsRepository;

    public GetAccountsRestResponse execute() {

        List<Accounts> accounts = accountsRepository.retrieveAccounts();

        return new GetAccountsRestResponse(
            LocalDateTime.now(),
            new HashSet<>(accounts)
        );
    }

}
