package fr.willy.cryptoback.accounts.infrastructure.output.rest.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class AccountResponse {
    private String id;
    private String code;
    private String libel;
    private BigDecimal price;
    private BalanceResponse balance;

    @Builder
    @Getter
    public static class BalanceResponse {
        private BigDecimal value;
        private int exponent;
    }
}
