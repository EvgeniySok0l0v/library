package com.ysokalau.library.service.impl;

import com.ysokalau.library.entity.Book;
import com.ysokalau.library.repo.BookRepo;
import com.ysokalau.library.service.BookService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * service implementation for books
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepo repo;

    /**
     * constructor BookServiceImpl
     *
     * @param repo - BookRepo
     */
    public BookServiceImpl(BookRepo repo) {
        this.repo = repo;
    }

    @Override
    public Book create(Book book) {
        return repo.save(book);
    }

    @Override
    public Book update(Book book, Long id) {
        if(repo.findById(id).isPresent()){
            book.setId(id);
            return repo.save(book);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Cacheable("books")
    public Book getById(Long id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        repo.findAll().forEach(books::add);
        return books;
    }

    @Override
    public List<Book> getAllByDate(Date date) {
        return repo.getAllByDate(date);
    }

    @Override
    public List<Book> getAllByAuthors(String[] authors) {
        Set<Book> books = new HashSet<>();
        Arrays.stream(authors)
                .filter(s -> s != null && !"".equals(s))
                .forEach(author -> books
                        .addAll(repo.getAllByAuthors("%" + author + "%")));
        return books.stream().toList();
    }

    @Override
    public List<Book> getAllByPublishing(String[] publishing) {
        Set<Book> books = new HashSet<>();
        Arrays.stream(publishing)
                .filter(s -> s != null && !"".equals(s))
                .forEach(pub -> books
                        .addAll(repo.getAllByPublishing("%" + pub + "%")));
        return books.stream().toList();
    }

    @Override
    public List<Book> getAllByPeriod(Date startDate, Date endDate) {
        return repo.getAllByPeriod(startDate, endDate);
    }

    @Override
    public List<Book> getAllByGenre(String genre) {
        return repo.getAllByGenre("%" + genre + "%");
    }
}
