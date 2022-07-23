package fr.willy.cryptoback.accounts.infrastructure.repository;

import fr.willy.cryptoback.accounts.domain.entity.Accounts;
import fr.willy.cryptoback.accounts.domain.repository.AccountsRepository;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.AccountApi;
import fr.willy.cryptoback.accounts.infrastructure.repository.jpa.JpaAccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PostgreAccountsRepository implements AccountsRepository {

    private final JpaAccountsRepository AccountsRepository;
    private final Map<String, AccountApi> accountApiList = new HashMap<>();

    public void addAccountApi(AccountApi accountApi) {
        accountApiList.put(accountApi.getAccountName(), accountApi);
    }

    @Override
    public List<Accounts> retrieveAccounts() {

        return AccountsRepository.findAll().stream()
            .map(Accounts::new)
            .collect(Collectors.toList());
    }

    @Override
    public void refreshAccounts() {
        accountApiList.values().forEach(accountApi -> AccountsRepository.saveAll(accountApi.importAccounts()));
    }
}
