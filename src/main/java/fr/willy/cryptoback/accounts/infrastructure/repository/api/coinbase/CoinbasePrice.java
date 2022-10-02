package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase;

import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.PriceEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.enums.CoinbaseRessource.BUYS;

@Log4j2
@RequiredArgsConstructor
@Service
public class CoinbasePrice {

    private final CoinbaseConnexion coinbaseConnexion;

    public BigDecimal getPrice(String currency) {
        log.info("getPrice : currency=[{}]", currency);

        PriceEntity priceEntity = coinbaseConnexion.getRessourceFromUrl(String.format(BUYS.url(), currency + "-EUR"), PriceEntity.class);

        return priceEntity.data().amount();
    }
}
