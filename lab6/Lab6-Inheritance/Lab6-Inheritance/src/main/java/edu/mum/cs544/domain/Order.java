package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue
    private Long orderid;

    private LocalDate date;

    @ManyToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_line")
    private List<OrderLine> lines = new ArrayList<>();

    public Order(LocalDate date) {
        this();

        this.date = date;
    }

    public void addLine(OrderLine line) {
        if (line != null) {
            lines.add(line);
        }
    }

    public void removeLine(OrderLine line) {
        if (line != null) {
            lines.remove(line);
        }
    }
}
