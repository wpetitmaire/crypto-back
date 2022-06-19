package fr.willy.cryptoback.wallets.usecase;

import fr.willy.cryptoback.wallets.domain.entity.Wallet;
import fr.willy.cryptoback.wallets.domain.repository.WalletRepository;
import fr.willy.cryptoback.wallets.infrastructure.output.rest.model.GetWalletsRestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetWallets {

    final WalletRepository walletRepository;

    public GetWalletsRestResponse execute() {

        List<Wallet> wallets = walletRepository.retireveWallets();

        return new GetWalletsRestResponse(
                LocalDateTime.now(),
                new HashSet<>(wallets)
        );
    }

}
