package fr.willy.cryptoback.wallets.infrastructure.input.rest;

import fr.willy.cryptoback.wallets.infrastructure.output.rest.model.GetWalletsRestResponse;
import fr.willy.cryptoback.wallets.usecase.GetWallets;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
@Log4j2
public class GetWalletRestRessource {

    private final GetWallets getWallets;

    @GetMapping
    public ResponseEntity<GetWalletsRestResponse> getWallets() {

       GetWalletsRestResponse response = getWallets.execute();
        return ResponseEntity.ok(response);
    }
}
