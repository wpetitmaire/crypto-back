package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase;

import fr.willy.cryptoback.CryptobackApplication;
import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
import fr.willy.cryptoback.common.RestRessourceTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CryptobackApplication.class)
class AccountApiCoinbaseTest extends RestRessourceTest {

    @Autowired
    private AccountApiCoinbase accountApiCoinbase;

    @Test
    public void should_transform_coinbase_objects_to_domain_objects() {

        mockGetPaginationAccountsFromCB();

        List<AccountEntity> actual = accountApiCoinbase.importAccounts();
        assertThat(actual)
            .hasSize(3);
    }

}