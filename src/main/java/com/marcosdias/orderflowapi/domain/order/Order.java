package com.marcosdias.orderflowapi.domain.order;

import com.marcosdias.orderflowapi.domain.address.Address;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long id;

    private Long userId;

    private Address address;

    private List<OrderItem> items = new ArrayList<>();

    private OrderStatus status;

    private BigDecimal totalAmount;

    private BigDecimal shippingCost;

    private Instant createdAt;

    private Instant updatedAt;

    protected Order() {
    }

    public Order(Long userId, Address address, OrderStatus status, BigDecimal totalAmount, BigDecimal shippingCost, Instant createdAt, Instant updatedAt) {
        this.userId = userId;
        this.address = address;
        this.status = status;
        this.totalAmount = totalAmount;
        this.shippingCost = shippingCost;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Address getAddress() {
        return address;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
}


