package br.com.felixgilioli.alunoservice.domain.exception;

public class AlunoNaoEncontradoException extends RuntimeException {

    public AlunoNaoEncontradoException(String message) {
        super(message);
    }
}
