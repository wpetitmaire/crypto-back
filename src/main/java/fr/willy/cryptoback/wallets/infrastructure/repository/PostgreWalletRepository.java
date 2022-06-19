package fr.willy.cryptoback.wallets.infrastructure.repository;

import fr.willy.cryptoback.wallets.domain.entity.Wallet;
import fr.willy.cryptoback.wallets.domain.repository.WalletRepository;
import fr.willy.cryptoback.wallets.infrastructure.repository.entity.WalletEntity;
import fr.willy.cryptoback.wallets.infrastructure.repository.jpa.JpaWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PostgreWalletRepository implements WalletRepository {

    private final JpaWalletRepository walletRepository;

    @Override
    public List<Wallet> retireveWallets() {

        return List.of(new Wallet(1L, "BIT", "Bitcoin"));

//        return walletRepository.findAll().stream()
//            .map(Wallet::new)
//            .collect(Collectors.toList());
    }
}