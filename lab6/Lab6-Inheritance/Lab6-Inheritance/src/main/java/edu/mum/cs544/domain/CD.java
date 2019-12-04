package edu.mum.cs544.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Data
public class CD extends Product {

    private String artist;

    public CD(String artist, String name, String description) {
        super(name, description);
        this.artist = artist;
    }
}
