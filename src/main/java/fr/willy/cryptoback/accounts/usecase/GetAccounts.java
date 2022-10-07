package fr.willy.cryptoback.accounts.usecase;

import fr.willy.cryptoback.accounts.domain.repository.AccountRepository;
import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponse;
import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class GetAccounts {

    final AccountRepository accountRepository;

    public GetAccountsRestResponse execute(boolean refresh) {

        if (refresh) {
            accountRepository.refreshAccounts();
        }

        return GetAccountsRestResponseFactory.construct(accountRepository.retrieveAccounts());
    }

}
