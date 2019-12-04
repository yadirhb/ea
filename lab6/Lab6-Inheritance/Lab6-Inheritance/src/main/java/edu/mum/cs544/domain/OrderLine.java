package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue
    private Long id;

    private Integer quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product product;

    public OrderLine(Integer quantity, Product product) {
        this();

        this.quantity = quantity;
        this.product = product;
    }
}
