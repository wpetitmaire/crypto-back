package fr.willy.cryptoback.accounts.usecase;

import fr.willy.cryptoback.accounts.domain.entity.Account;
import fr.willy.cryptoback.accounts.domain.repository.AccountRepository;
import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class GetAccounts {

    final AccountRepository accountRepository;

    public GetAccountsRestResponse execute(boolean refresh) {

        if (refresh) {
            accountRepository.refreshAccounts();
        }

        List<Account> accounts = accountRepository.retrieveAccounts();

        log.info(accounts);

        return new GetAccountsRestResponse(
            LocalDateTime.now(),
            new ArrayList<>(accounts)
        );
    }

}
