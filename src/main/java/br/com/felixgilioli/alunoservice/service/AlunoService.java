package br.com.felixgilioli.alunoservice.service;

import br.com.felixgilioli.alunoservice.domain.entity.Aluno;
import br.com.felixgilioli.alunoservice.domain.event.AlunoCadastradoEvent;
import br.com.felixgilioli.alunoservice.domain.exception.AlunoJaCadastradoException;
import br.com.felixgilioli.alunoservice.domain.exception.AlunoNaoEncontradoException;
import br.com.felixgilioli.alunoservice.adapter.out.repository.AlunoRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record AlunoService(
        AlunoRepository alunoRepository,
        ApplicationEventPublisher applicationEventPublisher
) {

    public Aluno salvar(Aluno aluno) {
        boolean alunoJaExiste = alunoRepository.existsByEmail(aluno.getEmail());

        if (alunoJaExiste) {
            throw new AlunoJaCadastradoException("Já existe um aluno com esse email: " + aluno.getEmail());
        }

        aluno = alunoRepository.save(aluno);

        applicationEventPublisher.publishEvent(new AlunoCadastradoEvent(this, aluno));

        return aluno;
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado com id: " + id));
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado com id: " + id));

        // Atualiza os campos desejados
        alunoExistente.setNomeCompleto(alunoAtualizado.getNomeCompleto());
        alunoExistente.setEmail(alunoAtualizado.getEmail());
        // Adicione mais campos se necessário

        return alunoRepository.save(alunoExistente);
    }

    public void deletar(Long id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado com id: " + id));

        alunoRepository.delete(alunoExistente);
    }
}
