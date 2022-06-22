package fr.willy.cryptoback.wallets.infrastructure.input.rest;

import fr.willy.cryptoback.common.RestRessourceTest;
import fr.willy.cryptoback.wallets.domain.entity.Wallet;
import fr.willy.cryptoback.wallets.infrastructure.output.rest.model.GetWalletsRestResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetWalletRestRessourceTest extends RestRessourceTest {

    @Test
    @Disabled
    void should_return_ok_with_the_wallet_list() throws Exception {

        GetWalletsRestResponse getWalletsRestResponse = extractResponse(getWalletsMvcResult(), GetWalletsRestResponse.class);

        assertThat(getWalletsRestResponse.retrieveDate()).isNotNull();
        assertThat(getWalletsRestResponse.wallets())
                .allMatch(wallet -> StringUtils.isNotBlank(wallet.getCode()))
                .allMatch(wallet -> StringUtils.isNotBlank(wallet.getLibel()));

        List<Long> walletIdList = getWalletsRestResponse.wallets().stream()
                .map(Wallet::getId)
                .collect(toList());

        assertThat(walletIdList).doesNotHaveDuplicates();
    }

    private MvcResult getWalletsMvcResult() throws Exception {
        return mockMvc.perform(get("/api/wallets")).andExpect(status().isOk())
                .andReturn();
    }

}