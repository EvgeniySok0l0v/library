package com.ysokalau.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Newspaper class
 */
@Data
@Entity
public class Newspaper extends PrintedProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String periodicity;
    @Column(nullable = false)
    private String country;

    public Newspaper(){
        super();
    }

    public Newspaper(Long id,
                    String name,
                    String authors,
                    String publishing,
                    Date date,
                    String periodicity,
                    String country){
        super(name, authors, publishing, date);
        this.id = id;
        this.periodicity = periodicity;
        this.country = country;
    }

    @Override
    public String toString(){
        return "\nId:" + id +
                "\nname:" + super.getName() +
                "\nauthors:" + super.getAuthors() +
                "\npublishing:" + super.getPublishing() +
                "\ndate:" + super.getDate().toString() +
                "\nperiodicity:" + periodicity +
                "\ncountry:" + country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Newspaper newspaper)) return false;
        if (!super.equals(o)) return false;
        return periodicity.equals(newspaper.periodicity) && country.equals(newspaper.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), periodicity, country);
    }
}
