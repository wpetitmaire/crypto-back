package fr.willy.cryptoback.accounts.infrastructure.repository.jpa;

import fr.willy.cryptoback.accounts.infrastructure.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaAccountsRepository extends JpaRepository<AccountEntity, UUID> {

}
