package com.marcosdias.orderflowapi.domain.order;

import java.math.BigDecimal;

public class OrderItem {

    private Order order;

    private String sku;

    private String name;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal lineTotal;

    protected OrderItem() {
    }

    public OrderItem(String sku, String name, Integer quantity, BigDecimal unitPrice, BigDecimal lineTotal) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }


    public Order getOrder() {
        return order;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    void setOrder(Order order) {
        this.order = order;
    }
}


