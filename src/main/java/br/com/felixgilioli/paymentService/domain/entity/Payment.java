package br.com.felixgilioli.paymentService.domain.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @NotNull(message = "paymentId is required")
    @Column(name = "payment_id", updatable = false, nullable = false)
    private UUID paymentId;

    @Column(nullable = false)
    @NotNull(message = "Amount is required")
    @Positive
    private BigDecimal amount;

    @Column(name = "seller_id", nullable = false)
    @NotNull(message = "Seller ID is required")
    private UUID sellerId;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Brand is required")
    private String brand;

    @Column(nullable = false, length = 6)
    @NotBlank(message = "BIN is required")
    @Size(min = 6, max = 6, message = "BIN must be 6 characters")
    private String bin;

    @Column(nullable = false, length = 4)
    @NotBlank(message = "CVC is required")
    @Size(min = 4, max = 4, message = "CVC must be 4 characters")
    private String cvc;

    @Column(name = "order_id", nullable = false)
    @NotNull(message = "Order ID is required")
    private UUID orderId;

    @Column(nullable = false, length = 100)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    // Getters and Setters
    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public void setSellerId(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
