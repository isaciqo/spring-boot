package br.com.felixgilioli.paymentService.domain.service;

import br.com.felixgilioli.paymentService.domain.entity.Payment;
import br.com.felixgilioli.paymentService.domain.event.SendEmailEvent;
import br.com.felixgilioli.paymentService.adapter.out.messaging.EmailEventPublisher;
import br.com.felixgilioli.paymentService.adapter.out.repository.PaymentRepository;
import br.com.felixgilioli.paymentService.domain.exception.PaymentAlreadyExistsException;
import br.com.felixgilioli.paymentService.domain.exception.PaymentNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;
    private final EmailEventPublisher emailEventPublisher;
    private final AntifraudService antifraudService;

    public PaymentService(PaymentRepository paymentRepository,
                          EmailEventPublisher emailEventPublisher,
                          AntifraudService antifraudService) {
        this.paymentRepository = paymentRepository;
        this.emailEventPublisher = emailEventPublisher;
        this.antifraudService = antifraudService;
    }

    public Payment create(Payment payment) {
        boolean paymentExists = paymentRepository.existsById(payment.getPaymentId());

        if (paymentExists) {
            throw new PaymentAlreadyExistsException("Payment already exists with id: " + payment.getPaymentId());
        }

        // Check antifraud flag before persisting
        boolean antifraudFlag = antifraudService.checkAntifraudFlag();
        log.info("Antifraud flag: {}", antifraudFlag);

        Payment savedPayment = paymentRepository.save(payment);

        // Publish event to RabbitMQ
        SendEmailEvent emailEvent = new SendEmailEvent(
                "finance@company.com", // in real case, the client email or finance dept
                "Payment Confirmation",
                "Your payment with ID " + savedPayment.getPaymentId() + " has been successfully processed."
        );
        emailEventPublisher.publish(emailEvent);

        return savedPayment;
    }

    public Payment findById(UUID id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment update(UUID id, Payment updatedPayment) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));

        existing.setSellerId(updatedPayment.getSellerId());
        existing.setAmount(updatedPayment.getAmount());

        return paymentRepository.save(existing);
    }

    public void delete(UUID id) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));

        paymentRepository.delete(existing);
    }
}
