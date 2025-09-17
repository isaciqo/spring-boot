package br.com.felixgilioli.paymentService.adapter.out.repository;

import br.com.felixgilioli.paymentService.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    boolean existsByEmail(String email);
}
