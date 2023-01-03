package fr.willy.cryptoback.accounts.infrastructure.repository.provider.coinbase.entity.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
class CurrencyFromCBEntity {
    private String code;
    private String name;
}