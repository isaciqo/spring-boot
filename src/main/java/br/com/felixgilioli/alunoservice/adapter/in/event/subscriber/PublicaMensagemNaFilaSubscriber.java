package br.com.felixgilioli.alunoservice.adapter.in.event.subscriber;

import br.com.felixgilioli.alunoservice.domain.entity.Aluno;
import br.com.felixgilioli.alunoservice.domain.event.AlunoCadastradoEvent;
import br.com.felixgilioli.alunoservice.adapter.out.messaging.AlunoCadastrado;
import br.com.felixgilioli.alunoservice.adapter.out.messaging.PublicaMensagemAlunoCadastrado;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public record PublicaMensagemNaFilaSubscriber(PublicaMensagemAlunoCadastrado publicaMensagemAlunoCadastrado)
        implements ApplicationListener<AlunoCadastradoEvent> {

    @Override
    public void onApplicationEvent(AlunoCadastradoEvent event) {
        Aluno aluno = event.getAluno();
        var alunoCadastrado = new AlunoCadastrado(aluno.getId(), aluno.getEmail());
        publicaMensagemAlunoCadastrado.publica(alunoCadastrado);
    }
}
