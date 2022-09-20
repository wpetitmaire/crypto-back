package fr.willy.cryptoback.accounts.infrastructure.input.rest;

import fr.willy.cryptoback.accounts.domain.entity.Account;
import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponse;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.CoinbaseConnexion;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.AccountFomCBEntity;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.CurrencyEntity;
import fr.willy.cryptoback.common.RestRessourceTest;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.enums.CoinbaseRessource.ACCOUNTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@RunWith(SpringRunner.class)
class GetAccountRestRessourceTest extends RestRessourceTest {

    @MockBean
    CoinbaseConnexion coinbaseConnexion;

    @Test
    @Disabled
    void should_return_ok_with_the_account_list() throws Exception {

        Mockito.when(coinbaseConnexion.getPaginatedData(ACCOUNTS.getUrl(), AccountFomCBEntity.class))
            .thenReturn(List.of(
                new AccountFomCBEntity("7e4d7786-a660-42ea-b524-da49889b7c50", new CurrencyEntity("BTC", "Bitcoin")),
                new AccountFomCBEntity("4f15f685-bad3-4514-8ef6-e9dd573ad3eb", new CurrencyEntity("ETH", "Ethereum")),
                new AccountFomCBEntity("c760f9ac-a53d-4479-b37a-cd2ae7cc405c", new CurrencyEntity("SHIB", "Shiba"))
            ));

        GetAccountsRestResponse getAccountsRestResponse = extractResponse(getAccountsMvcResult(), GetAccountsRestResponse.class);

        assertThat(getAccountsRestResponse.retrieveDate()).isNotNull();
        assertThat(getAccountsRestResponse.accounts())
            .allMatch(account -> BooleanUtils.isTrue(account.getId() != null))
            .allMatch(account -> BooleanUtils.isTrue(account.getCode() != null))
            .allMatch(account -> BooleanUtils.isTrue(account.getLibel() != null));

        List<String> accountIds = getAccountsRestResponse.accounts().stream()
            .map(Account::getId)
            .toList();

        assertThat(accountIds).doesNotHaveDuplicates();
    }

    private MvcResult getAccountsMvcResult() throws Exception {
        return mockMvc
            .perform(get("/api/accounts?refresh=true"))
            .andExpect(status().isOk())
            .andReturn();
    }

}