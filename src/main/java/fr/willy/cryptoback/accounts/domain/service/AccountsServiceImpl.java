package fr.willy.cryptoback.accounts.domain.service;

import fr.willy.cryptoback.accounts.domain.entity.Account;
import fr.willy.cryptoback.accounts.domain.enums.Provider;
import fr.willy.cryptoback.accounts.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountRepository accountRepository;

    @Override
    public void refreshAllAccountsFromProvider(Provider provider) {
        accountRepository.refreshAccountsFromProvider(provider);
    }

    @Override
    public List<Account> retrieveAccountsFromProvider(Provider provider) {
        return accountRepository.retrieveAccountsFromProvider(provider);
    }
}
