package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountFomCBEntity {
    private String id;
    private String name;
}
