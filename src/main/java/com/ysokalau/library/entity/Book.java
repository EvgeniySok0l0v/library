package com.ysokalau.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Book class
 */
@Data
@Entity
public class Book extends PrintedProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String genre;

    public Book(){
        super();
    }

    public Book(Long id, String name, String authors, String publishing, Date date, String genre){
        super(name, authors, publishing, date);
        this.id = id;
        this.genre = genre;
    }

    @Override
    public String toString(){
        return "\nId:" + id +
                "\nname:" + super.getName() +
                "\nauthors:" + super.getAuthors() +
                "\npublishing:" + super.getPublishing() +
                "\ndate:" + super.getDate().toString() +
                "\ngenre:" + genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        if (!super.equals(o)) return false;
        return genre.equals(book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), genre);
    }
}
