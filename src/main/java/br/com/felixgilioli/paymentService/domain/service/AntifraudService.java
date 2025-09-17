package br.com.felixgilioli.paymentService.domain.service;

import br.com.felixgilioli.paymentService.domain.entity.Payment;
import br.com.felixgilioli.paymentService.adapter.out.redis.FlagRedisAdapter;
import br.com.felixgilioli.paymentService.adapter.out.http.AntifraudValidationClient;
import br.com.felixgilioli.paymentService.domain.exception.TransactionNotAuthorizedException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AntifraudServiceImpl{

    private final FlagRedisAdapter flagRedisAdapter;
    private final AntifraudValidationClient validationClient;
    private static final Logger log = LoggerFactory.getLogger(AntifraudServiceImpl.class);

    public AntifraudServiceImpl(FlagRedisAdapter flagRedisAdapter, AntifraudValidationClient validationClient) {
        this.flagRedisAdapter = flagRedisAdapter;
        this.validationClient = validationClient;
    }

    @Override
    public boolean checkAntifraudFlag() {
        boolean flag = flagRedisAdapter.getOrCreateAntifraudFlag();
        log.info("Antifraud flag: {}", flag);
        return flag;
    }

    @Override
    public void validatePayment(Payment payment) {
        if (!checkAntifraudFlag()) return;

        try {
            validationClient.validatePayment(payment);
        } catch (RuntimeException | InterruptedException e) {
            throw new TransactionNotAuthorizedException("Payment not authorized due to antifraud rules");
        }
    }
}
