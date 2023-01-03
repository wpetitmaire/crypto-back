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
public class PriceEntity {
    private Data data;

    @Getter
    public static class Data {
        private BigDecimal amount;
    }
}
