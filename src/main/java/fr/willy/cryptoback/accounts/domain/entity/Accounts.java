package fr.willy.cryptoback.accounts.domain.entity;

import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class Accounts {

    @NotNull
    private Long id;
    @NotBlank
    private String code;
    @NotBlank
    private String libel;

    public Accounts(final AccountEntity accountEntity) {
        id = accountEntity.getId();
        code = accountEntity.getCode();
        libel = accountEntity.getLibel();
    }
}
