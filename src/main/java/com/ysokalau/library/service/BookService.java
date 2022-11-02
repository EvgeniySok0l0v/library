package com.ysokalau.library.service;

import com.ysokalau.library.entity.Book;

import java.util.Date;
import java.util.List;

/**
 * interface for book service
 */
public interface BookService {

    /**
     * method for create Book
     *
     * @param book - Book
     * @return - Book
     */
    Book create(Book book);

    /**
     * method for update Book
     *
     * @param book - Book
     * @param id - id of book
     * @return - updated Book
     */
    Book update(Book book, Long id);

    /**
     * method for delete Book by id
     *
     * @param id - id of book
     */
    void delete(Long id);

    /**
     * method for get Book by id
     *
     * @param id - id of book
     * @return - Book
     */
    Book getById(Long id);

    /**
     * method for get all books
     *
     * @return - List of Book
     */
    List<Book> getAll();

    /**
     * method for get all books by date
     *
     * @param date - date
     * @return - List of Book
     */
    List<Book> getAllByDate(Date date);

    /**
     * method for get all books by authors
     *
     * @param authors - String[]
     * @return - List of Book
     */
    List<Book> getAllByAuthors(String[] authors);

    /**
     * method for get all books by publishing
     *
     * @param publishing - String[]
     * @return - List of Book
     */
    List<Book> getAllByPublishing(String[] publishing);

    /**
     * method for get all books by period
     *
     * @param startDate - start date
     * @param endDate - end date
     * @return - List of Book
     */
    List<Book> getAllByPeriod(Date startDate, Date endDate);

    /**
     * method for get all books by genre
     *
     * @param genre - genre
     * @return - List of Book
     */
    List<Book> getAllByGenre(String genre);
}
