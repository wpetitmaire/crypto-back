package fr.willy.cryptoback.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.willy.cryptoback.CryptobackApplication;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.CoinbaseConnexion;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.CoinbasePrice;
import fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.AccountFomCBEntity;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import static fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.enums.CoinbaseRessource.ACCOUNTS;

@SpringBootTest(classes = CryptobackApplication.class)
@AutoConfigureMockMvc
public class RestRessourceTest {

    private final static Pattern UUID_PATTERN = Pattern.compile("^[{]?[\\da-fA-F]{8}-([\\da-fA-F]{4}-){3}[\\da-fA-F]{12}[}]?$");

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    protected ObjectWriter objectWriter;
    @MockBean
    CoinbaseConnexion coinbaseConnexion;
    @MockBean
    CoinbasePrice coinbasePrice;

    @BeforeEach
    public void setup() {
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    }

    protected String asString(Object object) throws JsonProcessingException {
        return objectWriter.writeValueAsString(object);
    }

    protected <T> T extractResponse(MvcResult mvcResult, Class<T> returnType) throws UnsupportedEncodingException, JsonProcessingException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), returnType);
    }

    protected <T> T extractResponse(MvcResult mvcResult, TypeReference<T> returnType) throws UnsupportedEncodingException, JsonProcessingException {
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), returnType);
    }

    protected String extractHeader(MvcResult mvcResult, String headerName) {
        return mvcResult.getResponse().getHeader(headerName);
    }

    protected void mockGetPaginationAccountsFromCB() {
        Mockito.when(coinbaseConnexion.getPaginatedData(ACCOUNTS.url(), AccountFomCBEntity.class))
            .thenReturn(List.of(
                new AccountFomCBEntity(
                    "7e4d7786-a660-42ea-b524-da49889b7c50",
                    new AccountFomCBEntity.Currency("BTC", "Bitcoin", 8),
                    new AccountFomCBEntity.Balance(BigDecimal.TEN)
                ),
                new AccountFomCBEntity(
                    "4f15f685-bad3-4514-8ef6-e9dd573ad3eb",
                    new AccountFomCBEntity.Currency("ETH", "Ethereum", 8),
                    new AccountFomCBEntity.Balance(BigDecimal.TEN)
                ),
                new AccountFomCBEntity(
                    "c760f9ac-a53d-4479-b37a-cd2ae7cc405c",
                    new AccountFomCBEntity.Currency("SHIB", "Shiba", 8),
                    new AccountFomCBEntity.Balance(BigDecimal.TEN)
                )
            ));
    }

    protected void mockPriceFromCB() {
        Mockito.when(coinbasePrice.getPrice("BTC"))
            .thenReturn(BigDecimal.ONE);

        Mockito.when(coinbasePrice.getPrice("ETH"))
            .thenReturn(BigDecimal.ONE);

        Mockito.when(coinbasePrice.getPrice("SHIB"))
            .thenReturn(BigDecimal.ONE);
    }
}
