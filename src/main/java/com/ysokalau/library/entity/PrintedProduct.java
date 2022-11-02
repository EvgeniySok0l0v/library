package com.ysokalau.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * SuperClass for printed products
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class PrintedProduct {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String authors;
    @Column(nullable = false)
    private String publishing;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    private Date date;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrintedProduct that)) return false;
        return name.equals(that.name)
                && authors.equals(that.authors)
                && publishing.equals(that.publishing)
                && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authors, publishing, date);
    }
}
