package br.com.felixgilioli.alunoservice.adapter.out.repository;

import br.com.felixgilioli.alunoservice.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByEmail(String email);
}
