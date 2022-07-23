package fr.willy.cryptoback.accounts.infrastructure.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
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
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String code;
    private String libel;

    public AccountEntity(String code, String libel) {
        this.code = code;
        this.libel = libel;
    }
}
