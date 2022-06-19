package fr.willy.cryptoback.wallets.infrastructure.repository.jpa;

import fr.willy.cryptoback.wallets.infrastructure.repository.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaWalletRepository extends JpaRepository<WalletEntity, UUID> {
}
