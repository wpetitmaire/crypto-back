package fr.willy.cryptoback.accounts.infrastructure.repository.api.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConnexionError extends RuntimeException {

    public ConnexionError(String message, Throwable e) {
        super(message, e);
       log.error("ConnexionError : {}", message);
    }
}
