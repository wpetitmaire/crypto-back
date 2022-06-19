package fr.willy.cryptoback.wallets.infrastructure.output.rest.model;

import fr.willy.cryptoback.wallets.domain.entity.Wallet;

import java.time.LocalDateTime;
import java.util.Set;

public record GetWalletsRestResponse(
        LocalDateTime retrieveDate,
        Set<Wallet> wallets
) {}