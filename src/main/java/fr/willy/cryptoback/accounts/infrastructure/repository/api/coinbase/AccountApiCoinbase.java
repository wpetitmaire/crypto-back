package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase;

import fr.willy.cryptoback.accounts.infrastructure.repository.PostgreAccountRepository;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.AccountApi;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.AccountFomCBEntity;
import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.enums.CoinbaseRessource.ACCOUNTS;
import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountApiCoinbase implements AccountApi {

    private final PostgreAccountRepository postgreAccountRepository;
    private final CoinbaseConnexion coinbaseConnexion;
    private final CoinbasePrice coinbasePrice;

    @Getter
    private final String providerName = "coinbase";

    private AccountEntity transformToDomainObject(AccountFomCBEntity accountFomCBEntity) {

        log.info("libelle : [{}] - code : [{}]", accountFomCBEntity.currency().name(), accountFomCBEntity.id());

        log.info(accountFomCBEntity.balance().amount().compareTo(ZERO));

        return new AccountEntity(
            accountFomCBEntity.currency().code(),
            accountFomCBEntity.currency().name(),
            "COINBASE",
            accountFomCBEntity.balance().amount(),
            coinbasePrice.getPrice(accountFomCBEntity.currency().code()),
            accountFomCBEntity.currency().exponent()
        );
    }

    @PostConstruct
    public void onStart() {
        log.info("-> onstart");
        postgreAccountRepository.addAccountApi(this);
    }

    @Override
    public List<AccountEntity> importAccounts() {
        log.info("-> importAccounts");

        return coinbaseConnexion.getPaginatedData(ACCOUNTS.url(), AccountFomCBEntity.class)
            .stream()
            .filter(accountFomCBEntity -> {

                log.info(accountFomCBEntity.balance());

                return accountFomCBEntity.balance().amount().compareTo(ZERO) != 0;
            })
            .map(this::transformToDomainObject)
            .collect(Collectors.toList());
    }


    public BigDecimal getCurrencyPrice(String currency) {
        return coinbasePrice.getPrice(currency);
    }
}
