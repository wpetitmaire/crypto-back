package fr.willy.cryptoback.accounts.infrastructure.repository;

import fr.willy.cryptoback.accounts.domain.entity.Account;
import fr.willy.cryptoback.accounts.domain.enums.Provider;
import fr.willy.cryptoback.accounts.domain.repository.AccountRepository;
import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
import fr.willy.cryptoback.accounts.infrastructure.repository.jpa.JpaAccountsRepository;
import fr.willy.cryptoback.accounts.infrastructure.repository.provider.Manager.service.ProviderManagerService;
import fr.willy.cryptoback.accounts.infrastructure.repository.provider.ProviderApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Component
public class PostgreAccountRepository implements AccountRepository {

    private final ProviderManagerService providerManagerService;
    private final JpaAccountsRepository accountsRepository;

    @Override
    public List<Account> retrieveAccountsFromProvider(Provider provider) {

        return accountsRepository.findAll().stream()
            .map(Account::new)
            .collect(Collectors.toList());
    }

    @Override
    public void refreshAccountsFromProvider(Provider provider) {
        log.info("-> refresh accounts");

        ProviderApi providerApi = providerManagerService.getProvider(provider);
        List<AccountEntity> accountEntityList = providerApi.importAccounts();

        log.info(accountEntityList);

        accountsRepository.deleteAll();
        accountsRepository.saveAll(accountEntityList);

//        accountApiList.values().forEach(accountApi -> AccountsRepository.saveAll(accountApi.importAccounts()));
    }

}
