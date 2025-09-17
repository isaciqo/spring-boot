package br.com.felixgilioli.paymentService.adapter.out.http;

import br.com.felixgilioli.paymentService.domain.entity.Payment;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class AntifraudValidationClient {

    // Simula chamada a endpoint externo com timeout de 2 segundos
    public void validatePayment(Payment payment) throws InterruptedException {
        Thread.sleep(2000); // simula timeout
        if (BigDecimal.valueOf(422).compareTo(payment.getAmount()) == 0) {
            throw new RuntimeException("Transaction not authorized");
        }
    }
}
