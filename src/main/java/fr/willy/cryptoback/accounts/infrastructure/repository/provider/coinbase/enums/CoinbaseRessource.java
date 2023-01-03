package fr.willy.cryptoback.accounts.infrastructure.repository.provider.coinbase.enums;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
public enum CoinbaseRessource {

    ACCOUNTS("/v2/accounts"),
    BUYS("/v2/prices/%s/buy");

    @Getter
    private String url;

    CoinbaseRessource(String ressourceUrl) {
        url = ressourceUrl;
    }
}
