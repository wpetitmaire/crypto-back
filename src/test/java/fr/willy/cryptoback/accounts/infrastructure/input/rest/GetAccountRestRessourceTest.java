package fr.willy.cryptoback.accounts.infrastructure.input.rest;

import fr.willy.cryptoback.accounts.domain.entity.Account;
import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponse;
import fr.willy.cryptoback.common.RestRessourceTest;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class GetAccountRestRessourceTest extends RestRessourceTest {

    @Test
    @Disabled
    void should_return_ok_with_the_account_list() throws Exception {

        mockGetPaginationAccountsFromCB();

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