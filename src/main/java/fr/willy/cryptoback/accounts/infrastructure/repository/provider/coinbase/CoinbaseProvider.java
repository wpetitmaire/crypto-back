package fr.willy.cryptoback.accounts.infrastructure.repository.provider.coinbase;

import fr.willy.cryptoback.accounts.domain.enums.Provider;
import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
import fr.willy.cryptoback.accounts.infrastructure.repository.provider.ProviderApi;
import fr.willy.cryptoback.accounts.infrastructure.repository.provider.coinbase.entity.AccountFomCBEntity;
import fr.willy.cryptoback.accounts.infrastructure.repository.provider.register.service.ProviderRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static fr.willy.cryptoback.accounts.infrastructure.repository.provider.coinbase.enums.CoinbaseRessource.ACCOUNTS;
import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
@Log4j2
public class CoinbaseProvider implements ProviderApi {

    private final ProviderRegisterService providerRegisterService;
    private final CoinbaseConnexion coinbaseConnexion;
    private final CoinbasePrice coinbasePrice;

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
        providerRegisterService.register(Provider.COINBASE, this);
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
