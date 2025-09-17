package br.com.felixgilioli.paymentService.domain.service;

import br.com.felixgilioli.paymentService.adapter.out.redis.FlagRedisAdapter;
import br.com.felixgilioli.paymentService.adapter.out.http.AntifraudValidationClient;
import br.com.felixgilioli.paymentService.domain.exception.TransactionNotAuthorizedException;
import org.springframework.stereotype.Service;

@Service
public class AntifraudService {

    private final FlagRedisAdapter flagRedisAdapter;
    private final AntifraudValidationClient validationClient;


    public AntifraudService(FlagRedisAdapter flagRedisAdapter, AntifraudValidationClient validationClient) {
        this.flagRedisAdapter = flagRedisAdapter;
        this.validationClient = validationClient;
    }

    public boolean checkAntifraudFlag() {
        return flagRedisAdapter.getOrCreateAntifraudFlag();
    }

    public void validatePayment(double amount) {
        if (!checkAntifraudFlag()) return;

        try {
            validationClient.validatePayment(amount);
        } catch (RuntimeException | InterruptedException e) {
            throw new TransactionNotAuthorizedException("Payment not authorized due to antifraud rules");
        }
    }
}
