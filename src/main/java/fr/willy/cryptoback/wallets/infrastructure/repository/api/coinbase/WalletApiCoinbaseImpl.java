package fr.willy.cryptoback.wallets.infrastructure.repository.api.coinbase;

import fr.willy.cryptoback.wallets.infrastructure.repository.PostgreWalletRepository;
import fr.willy.cryptoback.wallets.infrastructure.repository.api.WalletApi;
import fr.willy.cryptoback.wallets.infrastructure.repository.entity.WalletEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class WalletApiCoinbaseImpl implements WalletApi {

    private final PostgreWalletRepository postgreWalletRepository;
    private final CoinbaseConnexion coinbaseConnexion;

    @Getter
    private final String walletName = "coinbase";

    @PostConstruct
    public void onStart() {
        postgreWalletRepository.addWalletApi(this);
    }

    @Override
    public List<WalletEntity> importWallets() {
        return null;
    }



}
