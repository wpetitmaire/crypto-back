package fr.willy.cryptoback.accounts.infrastructure.input.rest;

import fr.willy.cryptoback.accounts.domain.entity.Accounts;
import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponse;
import fr.willy.cryptoback.common.RestRessourceTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetAccountsRestRessourceTest extends RestRessourceTest {

    @Test
    @Disabled
    void should_return_ok_with_the_account_list() throws Exception {

        GetAccountsRestResponse getAccountsRestResponse = extractResponse(getAccountsMvcResult(), GetAccountsRestResponse.class);

        assertThat(getAccountsRestResponse.retrieveDate()).isNotNull();
        assertThat(getAccountsRestResponse.accounts())
            .allMatch(account -> StringUtils.isNotBlank(account.getCode()))
            .allMatch(account -> StringUtils.isNotBlank(account.getLibel()));

        List<Long> walletIdList = getAccountsRestResponse.accounts().stream()
            .map(Accounts::getId)
            .collect(toList());

        assertThat(walletIdList).doesNotHaveDuplicates();
    }

    private MvcResult getAccountsMvcResult() throws Exception {
        return mockMvc.perform(get("/api/accounts")).andExpect(status().isOk())
            .andReturn();
    }

}