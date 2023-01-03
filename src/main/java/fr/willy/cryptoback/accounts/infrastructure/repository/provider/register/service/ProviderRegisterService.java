package fr.willy.cryptoback.accounts.infrastructure.repository.provider.register.service;

import fr.willy.cryptoback.accounts.domain.enums.Provider;
import fr.willy.cryptoback.accounts.infrastructure.repository.provider.ProviderApi;

public interface ProviderRegisterService {
    void register(Provider provider, ProviderApi providerApi);
}
