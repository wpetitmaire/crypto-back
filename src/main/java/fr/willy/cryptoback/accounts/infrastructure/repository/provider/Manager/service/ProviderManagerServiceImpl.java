package fr.willy.cryptoback.accounts.infrastructure.repository.provider.Manager.service;

import fr.willy.cryptoback.accounts.domain.enums.Provider;
import fr.willy.cryptoback.accounts.infrastructure.repository.provider.ProviderApi;
import fr.willy.cryptoback.accounts.infrastructure.repository.provider.register.service.ProviderRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProviderManagerServiceImpl implements ProviderManagerService, ProviderRegisterService {

    private final Map<Provider, ProviderApi> providerApiMap = new HashMap<>();

    @Override
    public ProviderApi getProvider(Provider provider) {
        return providerApiMap.get(provider);
    }

    @Override
    public void register(Provider provider, ProviderApi providerApi) {
        providerApiMap.put(provider, providerApi);
    }
}
