package fr.willy.cryptoback.accounts.infrastructure.repository;

import fr.willy.cryptoback.accounts.domain.entity.Account;
import fr.willy.cryptoback.accounts.domain.repository.AccountRepository;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.AccountApi;
import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
import fr.willy.cryptoback.accounts.infrastructure.repository.jpa.JpaAccountsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Component
public class PostgreAccountRepository implements AccountRepository {

    private final JpaAccountsRepository accountsRepository;
    private final Map<String, AccountApi> accountApiList = new HashMap<>();

    public void addAccountApi(AccountApi accountApi) {
        accountApiList.put(accountApi.getProviderName(), accountApi);
    }

    @Override
    public List<Account> retrieveAccounts() {

        return accountsRepository.findAll().stream()
            .map(Account::new)
            .collect(Collectors.toList());
    }

    @Override
    public void refreshAccounts() {
        log.info("-> refresh accounts");

        AccountApi api = accountApiList.get("coinbase");
        List<AccountEntity> accountEntityList = api.importAccounts();

        log.info(accountEntityList);

        accountsRepository.deleteAll();
        accountsRepository.saveAll(accountEntityList);

//        accountApiList.values().forEach(accountApi -> AccountsRepository.saveAll(accountApi.importAccounts()));
    }

}
