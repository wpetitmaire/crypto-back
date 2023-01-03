package fr.willy.cryptoback.accounts.infrastructure.repository.enums;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
public enum Providers {
    COINBASE("COINBASE");

    @Getter
    private final String label;

    Providers(String label) {
        this.label = label;
    }
}
