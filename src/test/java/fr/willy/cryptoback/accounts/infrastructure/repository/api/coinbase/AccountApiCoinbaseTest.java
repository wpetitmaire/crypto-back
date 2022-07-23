package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase;

import fr.willy.cryptoback.CryptobackApplication;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.AccountFomCBEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.enums.CoinbaseRessource.ACCOUNTS;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CryptobackApplication.class)
class AccountApiCoinbaseTest {

    @Autowired
    private AccountApiCoinbase accountApiCoinbase;
    @Autowired
    private CoinbaseConnexion coinbaseConnexion;

    @Test
    public void should_transform_coinbase_objects_to_domain_objects() {
        Mockito.when(coinbaseConnexion.getPaginatedData(ACCOUNTS.name(), AccountFomCBEntity.class))
            .thenReturn(
                List.of(
                    new AccountFomCBEntity("BIT", "Bitcoin"),
                    new AccountFomCBEntity("ETH", "Ethereum")
                )
            );

        assertThat(accountApiCoinbase.importAccounts())
            .hasSize(2);
    }

}