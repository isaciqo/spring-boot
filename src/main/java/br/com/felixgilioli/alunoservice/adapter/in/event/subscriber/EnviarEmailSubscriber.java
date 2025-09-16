package br.com.felixgilioli.alunoservice.adapter.in.event.subscriber;

import br.com.felixgilioli.alunoservice.domain.event.AlunoCadastradoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EnviarEmailSubscriber {

    @EventListener
    public void enviaEmail(AlunoCadastradoEvent event) {
        System.out.println("enviando email...");
        throw new RuntimeException();
    }
}
