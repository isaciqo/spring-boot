package br.com.felixgilioli.paymentservice.adapter.in.rest;

import br.com.felixgilioli.paymentService.domain.entity.Payment;
import br.com.felixgilioli.paymentService.domain.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public record PaymentsController(PaymentService paymentService) {

    @PostMapping
    @Operation(description = "Create a new payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid payment data")
    })
    public Payment create(@RequestBody @Valid Payment payment) {
        return paymentService.create(payment);
    }

    @GetMapping("/{id}")
    @Operation(description = "Find a payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the payment"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public Payment findById(@PathVariable UUID id) {
        return paymentService.findById(id);
    }

    @GetMapping
    @Operation(description = "List all payments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all payments")
    })
    public List<Payment> findAll() {
        return paymentService.findAll();
    }

    @PutMapping("/{id}")
    @Operation(description = "Update an existing payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment successfully updated"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public Payment update(@PathVariable UUID id, @RequestBody Payment payment) {
        return paymentService.update(id, payment);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete a payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public void delete(@PathVariable UUID id) {
        paymentService.delete(id);
    }
}
