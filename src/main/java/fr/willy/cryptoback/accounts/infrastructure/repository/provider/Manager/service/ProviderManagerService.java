package fr.willy.cryptoback.accounts.infrastructure.repository.provider.Manager.service;

import fr.willy.cryptoback.accounts.domain.enums.Provider;
import fr.willy.cryptoback.accounts.infrastructure.repository.provider.ProviderApi;

public interface ProviderManagerService {
    ProviderApi getProvider(Provider provider);
}
