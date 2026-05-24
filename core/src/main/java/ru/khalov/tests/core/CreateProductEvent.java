package ru.khalov.tests.core;

import java.math.BigDecimal;

public class CreateProductEvent {
    private String id;
    private String title;
    private BigDecimal price;
    private Integer quantity;


    public CreateProductEvent() {
    }

    public CreateProductEvent(String id, String title, BigDecimal price, Integer quantity) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
