package fr.willy.cryptoback.accounts.domain.entity;

import fr.willy.cryptoback.accounts.domain.valueobject.IdentifiantCrypto;
import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    String id;
    String code;
    String libel;
    BigDecimal balance;
    BigDecimal price;
    int exponent;

    public Account(AccountEntity accountEntity) {
        id = new IdentifiantCrypto(accountEntity.provider(), accountEntity.currency()).value();
        code = accountEntity.currency();
        libel = accountEntity.name();
        balance = accountEntity.balance();
        price = accountEntity.price();
        exponent = accountEntity.exponent();
    }
}
