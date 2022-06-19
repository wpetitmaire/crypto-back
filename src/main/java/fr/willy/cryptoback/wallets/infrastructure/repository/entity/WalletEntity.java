package fr.willy.cryptoback.wallets.infrastructure.repository.entity;

import fr.willy.cryptoback.wallets.domain.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "wallet")
public class WalletEntity {

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

    public WalletEntity(String code, String libel) {
        this.code = code;
        this.libel = libel;
    }

    public static WalletEntity from(Wallet wallet) {
        return new WalletEntity(wallet.getCode(), wallet.getLibel());
    }
}
