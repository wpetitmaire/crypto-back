package fr.willy.cryptoback.accounts.usecase;

import fr.willy.cryptoback.accounts.domain.enums.Provider;
import fr.willy.cryptoback.accounts.domain.service.AccountsService;
import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponse;
import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class GetAccountsUseCase {

    final AccountsService accountsService;

    public GetAccountsRestResponse retrieveAccounts(boolean refresh, Provider provider) {

        if (refresh) {
            accountsService.refreshAllAccountsFromProvider(provider);
        }

        return GetAccountsRestResponseFactory.fromDomain(accountsService.retrieveAccountsFromProvider(provider));
    }

}
