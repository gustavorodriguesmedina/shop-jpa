package com.gustavo.Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    private Integer quantity;

    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, Product product, Order order) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
        this.unitPrice = product.getValue();
    }

    public BigDecimal getValue(){
        return unitPrice.multiply(new BigDecimal(quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
