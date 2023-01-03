package fr.willy.cryptoback.accounts.infrastructure.repository.provider.coinbase.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountFomCBEntity {
    private String id;
    private Currency currency;
    private Balance balance;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Currency {
        private String code;
        private String name;
        private int exponent;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Balance {
        private BigDecimal amount;
    }
}

