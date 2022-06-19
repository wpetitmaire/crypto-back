package fr.willy.cryptoback.wallets.domain.repository;

import fr.willy.cryptoback.wallets.domain.entity.Wallet;

import java.util.List;

public interface WalletRepository {
    public List<Wallet> retireveWallets();
}
