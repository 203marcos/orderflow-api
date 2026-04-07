package com.marcosdias.orderflowapi.domain.payment;

import com.marcosdias.orderflowapi.domain.order.Order;
import java.math.BigDecimal;
import java.time.Instant;

public class Payment {

    private Order order;

    private BigDecimal amount;

    private PaymentMethod method;

    private PaymentStatus status;

    private String providerReference;

    private Instant createdAt;

    protected Payment() {
    }

    public Payment(Order order, BigDecimal amount, PaymentMethod method, PaymentStatus status, String providerReference, Instant createdAt) {
        this.order = order;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.providerReference = providerReference;
        this.createdAt = createdAt;
    }


    public Order getOrder() {
        return order;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getProviderReference() {
        return providerReference;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}


