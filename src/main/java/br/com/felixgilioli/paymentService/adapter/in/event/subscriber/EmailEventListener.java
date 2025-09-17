package br.com.felixgilioli.paymentService.adapter.in.event.subscriber;

import br.com.felixgilioli.paymentService.adapter.out.messaging.RabbitMQConfig;
import br.com.felixgilioli.paymentService.domain.event.SendEmailEvent;
import br.com.felixgilioli.paymentService.domain.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailEventListener {

    private final EmailService emailService;

    public EmailEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void onMessage(SendEmailEvent event)throws InterruptedException {
        emailService.sendEmail(event);
    }
}
