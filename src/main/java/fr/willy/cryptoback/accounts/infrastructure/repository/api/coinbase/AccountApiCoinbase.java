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
import java.util.List;
import java.util.stream.Collectors;

import static fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.enums.CoinbaseRessource.ACCOUNTS;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountApiCoinbase implements AccountApi {

    private final PostgreAccountRepository postgreAccountRepository;
    private final CoinbaseConnexion coinbaseConnexion;

    @Getter
    private final String AccountName = "coinbase";

    private AccountEntity transformToDomainObject(AccountFomCBEntity accountFomCBEntity) {

        log.info("libelle : [{}] - code : [{}]", accountFomCBEntity.currency().name(), accountFomCBEntity.id());

        return new AccountEntity(
            accountFomCBEntity.currency().code(),
            accountFomCBEntity.currency().name(),
            "COINBASE"
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
        List<AccountEntity> collect = coinbaseConnexion.getPaginatedData(ACCOUNTS.getUrl(), AccountFomCBEntity.class)
            .stream()
            .map(this::transformToDomainObject)
            .collect(Collectors.toList());

        log.info("FIN");

        return collect;
    }

}
