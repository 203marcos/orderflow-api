package com.marcosdias.orderflowapi.payment;

import com.marcosdias.orderflowapi.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Column(length = 120)
    private String providerReference;

    @Column(nullable = false)
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

    public Long getId() {
        return id;
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

