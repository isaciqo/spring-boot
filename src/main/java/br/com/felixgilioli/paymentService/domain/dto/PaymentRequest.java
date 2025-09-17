package br.com.felixgilioli.paymentservice.domain.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

public class PaymentRequest {

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Seller ID is required")
    private UUID sellerId;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "BIN is required")
    @Size(min = 6, max = 6, message = "BIN must be 6 characters")
    private String bin;

    @NotBlank(message = "CVC is required")
    @Size(min = 4, max = 4, message = "CVC must be 4 characters")
    private String cvc;

    @NotNull(message = "Order ID is required")
    private UUID orderId;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    // Getters and Setters
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public UUID getSellerId() { return sellerId; }
    public void setSellerId(UUID sellerId) { this.sellerId = sellerId; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getBin() { return bin; }
    public void setBin(String bin) { this.bin = bin; }

    public String getCvc() { return cvc; }
    public void setCvc(String cvc) { this.cvc = cvc; }

    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID orderId) { this.orderId = orderId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
