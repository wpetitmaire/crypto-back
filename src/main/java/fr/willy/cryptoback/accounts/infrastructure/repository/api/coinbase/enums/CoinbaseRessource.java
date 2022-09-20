package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.enums;

import lombok.Getter;

public enum CoinbaseRessource {

    ACCOUNTS("/v2/accounts");

    @Getter
    private String url;

    CoinbaseRessource(String ressourceUrl) {
        url = ressourceUrl;
    }
}
