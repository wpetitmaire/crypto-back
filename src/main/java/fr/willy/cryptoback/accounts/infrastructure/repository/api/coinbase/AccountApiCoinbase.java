package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase;

import fr.willy.cryptoback.accounts.infrastructure.repository.PostgreAccountsRepository;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.AccountApi;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.AccountFomCBEntity;
import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.enums.CoinbaseRessource.ACCOUNTS;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountApiCoinbase implements AccountApi {

    private final PostgreAccountsRepository postgreAccountRepository;
    private final CoinbaseConnexion coinbaseConnexion;

    @Getter
    private final String AccountName = "coinbase";

    private static AccountEntity transformToDomainObject(AccountFomCBEntity accountFomCBEntity) {
        return new AccountEntity(accountFomCBEntity.id(), accountFomCBEntity.name());
    }

    @PostConstruct
    public void onStart() {
        postgreAccountRepository.addAccountApi(this);
    }

    @Override
    public List<AccountEntity> importAccounts() {
        return coinbaseConnexion.getPaginatedData(ACCOUNTS.name(), AccountFomCBEntity.class)
            .stream()
            .map(AccountApiCoinbase::transformToDomainObject)
            .collect(Collectors.toList());
    }

}
