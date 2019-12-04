package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class DVD extends Product {

    private String genre;

    public DVD(String genre, String name, String description) {
        super(name, description);
        this.genre = genre;
    }
}
