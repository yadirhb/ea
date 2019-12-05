package edu.mum.cs544;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Owner.GetAll", query = "select o from Owner o join fetch o.pets")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "clientid")
    //@LazyCollection(value = LazyCollectionOption.EXTRA) // With this it was 29005 without it was 52832
    //@BatchSize(size = 50) // with 10 it was 30835, with 5 33725, with 50 it was 27636
    //@Fetch(value = FetchMode.SUBSELECT) // with this was 20240
    private List<Pet> pets;

    public Owner() {
    }

    public Owner(String name) {
        super();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }


}
