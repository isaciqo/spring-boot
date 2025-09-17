package br.com.felixgilioli.paymentService.domain.service;

import br.com.felixgilioli.paymentService.domain.event.SendEmailEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmail(SendEmailEvent event) {
        logger.info("📧 Simulando envio de e-mail -> To: {}, Subject: {}, Body: {}",
                event.getTo(), event.getSubject(), event.getBody());
    }
}

