package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Book extends Product {

    private String title;

    public Book(String title, String description) {
        super("Book: " + title, description);

        this.title = title;
    }
}
