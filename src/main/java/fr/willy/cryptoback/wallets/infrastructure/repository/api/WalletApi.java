package fr.willy.cryptoback.wallets.infrastructure.repository.api;

import fr.willy.cryptoback.wallets.infrastructure.repository.entity.WalletEntity;

import java.util.List;

public interface WalletApi {
    public List<WalletEntity> importWallets();
    public String getWalletName();
}
