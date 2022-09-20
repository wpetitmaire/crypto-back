package fr.willy.cryptoback.accounts.infrastructure.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@ToString
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    private String currency;
    private String name;
    private String provider;

    public AccountEntity(String currency, String name, String provider) {
        this.currency = currency;
        this.name = name;
        this.provider = provider;
    }
}