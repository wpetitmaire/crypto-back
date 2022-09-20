package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase;

import fr.willy.cryptoback.CryptobackApplication;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.AccountFomCBEntity;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.CurrencyEntity;
import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
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
        Mockito.when(coinbaseConnexion.getPaginatedData(ACCOUNTS.getUrl(), AccountFomCBEntity.class))
            .thenReturn(
                List.of(
                    new AccountFomCBEntity("ffcee710-98d7-4889-99b7-8dca658d46d0", new CurrencyEntity("BTC", "Bitcoin")),
                    new AccountFomCBEntity("4e72a120-c581-4624-b674-5d5aef765043", new CurrencyEntity("ETH", "Ethereum"))
                )
            );

        List<AccountEntity> actual = accountApiCoinbase.importAccounts();
        assertThat(actual)
            .hasSize(2);
    }

}