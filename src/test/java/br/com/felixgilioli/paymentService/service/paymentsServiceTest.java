//package br.com.felixgilioli.paymentService.domain.service;
//
//import br.com.felixgilioli.paymentService.adapter.out.messaging.EmailEventPublisher;
//import br.com.felixgilioli.paymentService.adapter.out.repository.PaymentRepository;
//import br.com.felixgilioli.paymentService.domain.entity.Payment;
//import br.com.felixgilioli.paymentService.domain.entity.PutBody;
//import br.com.felixgilioli.paymentService.domain.event.SendEmailEvent;
//import br.com.felixgilioli.paymentService.domain.exception.PaymentAlreadyExistsException;
//import br.com.felixgilioli.paymentService.domain.exception.PaymentNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class PaymentServiceTest {
//
//    private PaymentRepository paymentRepository;
//    private EmailEventPublisher emailEventPublisher;
//    private AntifraudService antifraudService;
//    private PaymentService paymentService;
//
//    @BeforeEach
//    void setup() {
//        paymentRepository = mock(PaymentRepository.class);
//        emailEventPublisher = mock(EmailEventPublisher.class);
//        antifraudService = mock(AntifraudService.class);
//
//        paymentService = new PaymentService(paymentRepository, emailEventPublisher, antifraudService);
//    }
//
//    @Test
//    void testCreatePayment_success() {
//        Payment payment = new Payment(UUID.randomUUID(), "client@test.com", BigDecimal.valueOf(100));
//
//        when(paymentRepository.existsById(payment.getPaymentId())).thenReturn(false);
//        when(paymentRepository.save(payment)).thenReturn(payment);
//        when(antifraudService.checkAntifraudFlag()).thenReturn(false);
//
//        Payment saved = paymentService.create(payment);
//
//        assertEquals(payment, saved);
//        verify(antifraudService).validatePayment(payment.getAmount().doubleValue());
//
//        ArgumentCaptor<SendEmailEvent> captor = ArgumentCaptor.forClass(SendEmailEvent.class);
//        verify(emailEventPublisher).publish(captor.capture());
//        assertTrue(captor.getValue().getMessage().contains(payment.getPaymentId().toString()));
//    }
//
//    @Test
//    void testCreatePayment_alreadyExists() {
//        Payment payment = new Payment(UUID.randomUUID(), "client@test.com", BigDecimal.valueOf(100));
//        when(paymentRepository.existsById(payment.getPaymentId())).thenReturn(true);
//
//        assertThrows(PaymentAlreadyExistsException.class, () -> paymentService.create(payment));
//        verify(paymentRepository, never()).save(any());
//        verify(emailEventPublisher, never()).publish(any());
//    }
//
//    @Test
//    void testFindById_found() {
//        UUID id = UUID.randomUUID();
//        Payment payment = new Payment(id, "client@test.com", BigDecimal.valueOf(50));
//        when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));
//
//        Payment result = paymentService.findById(id);
//        assertEquals(payment, result);
//    }
//
//    @Test
//    void testFindById_notFound() {
//        UUID id = UUID.randomUUID();
//        when(paymentRepository.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(PaymentNotFoundException.class, () -> paymentService.findById(id));
//    }
//
//    @Test
//    void testFindAll() {
//        List<Payment> payments = Arrays.asList(
//                new Payment(UUID.randomUUID(), "a@test.com", BigDecimal.valueOf(10)),
//                new Payment(UUID.randomUUID(), "b@test.com", BigDecimal.valueOf(20))
//        );
//        when(paymentRepository.findAll()).thenReturn(payments);
//
//        List<Payment> result = paymentService.findAll();
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    void testUpdate_success() {
//        UUID id = UUID.randomUUID();
//        Payment existing = new Payment(id, "old@test.com", BigDecimal.valueOf(10));
//        PutBody updated = new PutBody("new@test.com", BigDecimal.valueOf(50));
//
//        when(paymentRepository.findById(id)).thenReturn(Optional.of(existing));
//        when(paymentRepository.save(existing)).thenReturn(existing);
//
//        Payment result = paymentService.update(id, updated);
//
//        assertEquals("new@test.com", result.getEmail());
//        assertEquals(BigDecimal.valueOf(50), result.getAmount());
//    }
//
//    @Test
//    void testUpdate_notFound() {
//        UUID id = UUID.randomUUID();
//        PutBody updated = new PutBody("new@test.com", BigDecimal.valueOf(50));
//        when(paymentRepository.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(PaymentNotFoundException.class, () -> paymentService.update(id, updated));
//    }
//
//    @Test
//    void testDelete_success() {
//        UUID id = UUID.randomUUID();
//        Payment existing = new Payment(id, "client@test.com", BigDecimal.valueOf(30));
//        when(paymentRepository.findById(id)).thenReturn(Optional.of(existing));
//
//        paymentService.delete(id);
//
//        verify(paymentRepository).delete(existing);
//    }
//
//    @Test
//    void testDelete_notFound() {
//        UUID id = UUID.randomUUID();
//        when(paymentRepository.findById(id)).thenReturn(Optional.empty());
//
//        assertThrows(PaymentNotFoundException.class, () -> paymentService.delete(id));
//        verify(paymentRepository, never()).delete(any());
//    }
//}
