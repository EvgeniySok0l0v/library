package com.ysokalau.library.repo;

import com.ysokalau.library.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * repository for books
 */
@Repository
public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> getAllByDate(Date date);

    @Query(nativeQuery = true, value = "select * from book where book.authors like :authors")
    List<Book> getAllByAuthors(String authors);

    @Query(nativeQuery = true, value = "select * from book where book.publishing like :publishing")
    List<Book> getAllByPublishing(String publishing);

    @Query(nativeQuery = true, value = "select * from book where book.date >= :startDate and book.date <= :endDate")
    List<Book> getAllByPeriod(Date startDate, Date endDate);

    @Query(nativeQuery = true, value = "select * from book where book.genre like :genre")
    List<Book> getAllByGenre(String genre);
}
