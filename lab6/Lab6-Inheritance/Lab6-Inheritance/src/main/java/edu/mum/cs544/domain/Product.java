package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    public Product(String name, String description) {
        this();

        this.name = name;
        this.description = description;
    }
}
