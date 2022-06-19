package fr.willy.cryptoback.wallets.domain.entity;

import fr.willy.cryptoback.wallets.infrastructure.repository.entity.WalletEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class Wallet {

    @NotNull
    private Long id;
    @NotBlank
    private String code;
    @NotBlank
    private String libel;

    public Wallet(final WalletEntity walletEntity) {
        id = walletEntity.getId();
        code = walletEntity.getCode();
        libel = walletEntity.getLibel();
    }
}
