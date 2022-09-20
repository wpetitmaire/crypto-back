package fr.willy.cryptoback.accounts.domain.valueobject;

public record IdentifiantCrypto(String value) {

    public IdentifiantCrypto(String providerCode, String currency) {
        this(providerCode.toUpperCase() + currency.toUpperCase());
    }
}
