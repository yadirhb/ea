package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;

    private String lastname;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Customer(String firstname, String lastname) {
        this();

        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void addOrder(Order order) {
        if (order != null) {
            order.setCustomer(this);
            orders.add(order);
        }
    }

    public void removeOrder(Order order) {
        if (order != null) {
            order.setCustomer(null);
            orders.remove(order);
        }
    }

}
