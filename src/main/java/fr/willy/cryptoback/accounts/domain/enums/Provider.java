package fr.willy.cryptoback.accounts.domain.enums;

public enum Provider {
    COINBASE("coinbase");

    private final String name;

    Provider(String name) {
        this.name = name;
    }
}
