package br.com.felixgilioli.paymentService.domain.exception;

public class TransactionNotAuthorizedException extends RuntimeException {
    public TransactionNotAuthorizedException(String message) {
        super(message);
    }
}
