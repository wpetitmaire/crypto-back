package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
class CoinbaseConnexionTestConfiguration {

    @Bean
    @Primary
    public CoinbaseConnexion coinbaseConnexion() {
        return Mockito.mock(CoinbaseConnexion.class);
    }

}