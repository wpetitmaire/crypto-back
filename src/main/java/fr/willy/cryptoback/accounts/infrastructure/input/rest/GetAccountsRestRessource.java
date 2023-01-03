package fr.willy.cryptoback.accounts.infrastructure.input.rest;

import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponse;
import fr.willy.cryptoback.accounts.usecase.GetAccountsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static fr.willy.cryptoback.accounts.domain.enums.Provider.COINBASE;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class GetAccountsRestRessource {

    private final GetAccountsUseCase getAccountsUseCase;

    @GetMapping(path = "/coinbase")
    public ResponseEntity<GetAccountsRestResponse> getAccounts(@RequestParam(required = false) boolean refresh) {
        return ResponseEntity.ok(getAccountsUseCase.retrieveAccounts(refresh, COINBASE));
    }
}
