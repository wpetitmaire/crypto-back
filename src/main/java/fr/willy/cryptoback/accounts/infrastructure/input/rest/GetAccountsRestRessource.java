package fr.willy.cryptoback.accounts.infrastructure.input.rest;

import fr.willy.cryptoback.accounts.infrastructure.output.rest.model.GetAccountsRestResponse;
import fr.willy.cryptoback.accounts.usecase.GetAccounts;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Log4j2
public class GetAccountsRestRessource {

    private final GetAccounts getAccounts;

    @GetMapping
    public ResponseEntity<GetAccountsRestResponse> getAccounts(@RequestParam(required = false) boolean refresh) {
        return ResponseEntity.ok(getAccounts.execute(refresh));
    }
}
