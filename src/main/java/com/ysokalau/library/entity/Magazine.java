package com.ysokalau.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Magazine class
 */
@Data
@Entity
public class Magazine extends PrintedProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String periodicity;
    @Column(nullable = false)
    private String subject;

    public Magazine(){
        super();
    }

    public Magazine(Long id,
                    String name,
                    String authors,
                    String publishing,
                    Date date,
                    String periodicity,
                    String subject){
        super(name, authors, publishing, date);
        this.id = id;
        this.periodicity = periodicity;
        this.subject = subject;
    }

    @Override
    public String toString(){
        return "\nId:" + id +
                "\nname:" + super.getName() +
                "\nauthors:" + super.getAuthors() +
                "\npublishing:" + super.getPublishing() +
                "\ndate:" + super.getDate().toString() +
                "\nperiodicity:" + periodicity +
                "\nsubject:" + subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazine magazine)) return false;
        if (!super.equals(o)) return false;
        return periodicity.equals(magazine.periodicity) && subject.equals(magazine.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), periodicity, subject);
    }
}
