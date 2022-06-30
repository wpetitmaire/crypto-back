package fr.willy.cryptoback.wallets.infrastructure.repository.api.coinbase;

import fr.willy.cryptoback.wallets.infrastructure.repository.api.coinbase.entity.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.junit.jupiter.MockServerSettings;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith({MockitoExtension.class, MockServerExtension.class})
@MockServerSettings(ports = {8888})
class CoinbaseConnexionTest {

    private final ClientAndServer client;
    @InjectMocks
    private CoinbaseConnexion coinbaseConnexion;

    CoinbaseConnexionTest(ClientAndServer client) {
        this.client = client;
    }

    @BeforeEach
    public void beforeEachTest() {
        client.reset();

        ReflectionTestUtils.setField(coinbaseConnexion, "baseUrl", "http://localhost:8888/");
        ReflectionTestUtils.setField(coinbaseConnexion, "secretKey", "11111111");
        ReflectionTestUtils.setField(coinbaseConnexion, "apiKey", "2222222");
    }

    //        ReflectionTestUtils.setField(coinbaseConnexion.baseUrl, "BASE_URL", "http://localhost:8888/");
    @Test
    void should_return_a_set_of_wallets() throws IOException {

        Path exampleResponseFilePath = Paths.get("src/test/resources/files/jsonResults/coinbase-accounts-get-api.json");
        byte[] responseBodyData = Files.readAllBytes(exampleResponseFilePath);

        client.when(
            request()
                .withMethod("GET")
                .withPath("accounts")
        ).respond(
            response()
                .withStatusCode(200)
                .withBody(responseBodyData)
        );

        Set<AccountEntity> accounts = coinbaseConnexion.getPaginatedData("accounts");

        assertThat(accounts).allMatch(accountEntity -> accountEntity.id() != null);
        assertThat(accounts).allMatch(accountEntity -> accountEntity.name() != null);
    }

}