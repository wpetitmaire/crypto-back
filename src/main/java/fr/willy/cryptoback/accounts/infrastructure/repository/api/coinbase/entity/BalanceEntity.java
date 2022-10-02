package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class BalanceEntity {
    private BigDecimal amount;
}
