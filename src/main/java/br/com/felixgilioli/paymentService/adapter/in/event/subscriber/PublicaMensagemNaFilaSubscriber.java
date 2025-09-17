//package br.com.felixgilioli.paymentService.adapter.in.event.subscriber;
//
//import br.com.felixgilioli.paymentService.domain.entity.Aluno;
//import br.com.felixgilioli.paymentService.domain.event.AlunoCadastradoEvent;
//import br.com.felixgilioli.paymentService.adapter.out.messaging.AlunoCadastrado;
//import br.com.felixgilioli.paymentService.adapter.out.messaging.PublicaMensagemAlunoCadastrado;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public record PublicaMensagemNaFilaSubscriber(PublicaMensagemAlunoCadastrado publicaMensagemAlunoCadastrado)
//        implements ApplicationListener<AlunoCadastradoEvent> {
//
//    @Override
//    public void onApplicationEvent(AlunoCadastradoEvent event) {
//        Aluno aluno = event.getAluno();
//        var alunoCadastrado = new AlunoCadastrado(aluno.getId(), aluno.getEmail());
//        publicaMensagemAlunoCadastrado.publica(alunoCadastrado);
//    }
//}
