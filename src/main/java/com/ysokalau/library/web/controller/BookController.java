package com.ysokalau.library.web.controller;

import com.ysokalau.library.entity.Book;
import com.ysokalau.library.mapper.BookMapper;
import com.ysokalau.library.service.BookService;
import com.ysokalau.library.web.request.BookRequest;
import com.ysokalau.library.web.response.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.ysokalau.library.util.StringUtils.DATE_PATTERN;

/**
 * BookController
 */
@Slf4j
@RequestMapping("/api/book")
@RestController
public class BookController {

    private final BookService bookService;

    /**
     * constructor BookController
     *
     * @param bookService - BookService
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * method for get all books
     *
     * @return - List of Book
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<BookResponse>> getAll(){
        log.info("List of all books:");
        return getListResponseEntity(bookService.getAll());
    }

    /**
     * method for get book by id
     *
     * @param id - id of book
     * @return - Book
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id){
        Book bookFromDb = bookService.getById(id);
        if(bookFromDb != null) {
            BookResponse response = BookMapper
                    .bookToBookResponse(bookFromDb);
            log.info("Book with id:" + id + "\n" + bookFromDb);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.warn("Book with id:" + id + " not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * method for create Book
     *
     * @param request - BookRequest
     * @return - ResponseEntity of BookResponse
     */
    @PostMapping("/create")
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest request){
        Book book = BookMapper.bookRequestToBook(request);
        if(book != null) {
            BookResponse response = BookMapper
                    .bookToBookResponse(bookService.create(book));
            log.info("Book:" + book + "\nwas created!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.warn("Something wrong with BookRequest:" + request);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for update book
     *
     * @param request - BookRequest
     * @param id - id of book
     * @return - ResponseEntity of String
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody BookRequest request,
                                         @PathVariable Long id){
        Book book = BookMapper.bookRequestToBook(request);
        if(book != null) {
            if (bookService.update(book, id) != null) {
                log.info("Book with id:" + book.getId() + " was updated!");
                return new ResponseEntity<>("Book with id:" + book.getId() + " was updated!", HttpStatus.OK);
            } else {
                log.warn("Book with id:" + id + " not found!");
                return new ResponseEntity<>("Book with id:" + book.getId() + " not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn("Something wrong with BookRequest:" + request);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for delete book by id
     *
     * @param id - id of book
     * @return - ResponseEntity of String
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(bookService.getById(id) != null) {
            bookService.delete(id);
            log.info("Book with id:" + id + " was deleted!");
            return new ResponseEntity<>("Book with id:" + id + " was deleted!", HttpStatus.OK);
        } else {
            log.warn("Book with id:" + id + " not found!");
            return new ResponseEntity<>("Book with id:" + id + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * method for get all  books by date
     *
     * @param date - date
     * @return - ResponseEntity of List of BookResponse
     */
    @PostMapping("/getAllByDate")
    public ResponseEntity<List<BookResponse>> getAllByDate(@RequestParam(name = "date") String date){
        if(date.matches(DATE_PATTERN.pattern())){
            List<Book> books = bookService.getAllByDate(Date.valueOf(date));
            log.info("List of books by date:" + date);
            return getListResponseEntity(books);
        } else {
            log.warn("Incorrect date format!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all books by period
     *
     * @param startDate - start date
     * @param endDate - start date
     * @return - ResponseEntity of List of BookResponse
     */
    @PostMapping("/getAllByPeriod")
    public ResponseEntity<List<BookResponse>> getAllPeriod(@RequestParam(name = "start") String startDate,
                                                           @RequestParam(name = "end") String endDate){
        if(startDate.matches(DATE_PATTERN.pattern()) && endDate.matches(DATE_PATTERN.pattern())){
            List<Book> books = bookService.getAllByPeriod(Date.valueOf(startDate), Date.valueOf(endDate));
            log.info("List of books by period:from " + startDate + " to " + endDate);
            return getListResponseEntity(books);
        } else {
            log.warn("Incorrect date format!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all books by authors
     *
     * @param authors - String[]
     * @return - ResponseEntity of List of BookResponse
     */
    @PostMapping("/getAllByAuthors")
    public ResponseEntity<List<BookResponse>> getAllByAuthors(@RequestBody String[] authors){
        if(authors != null && authors.length > 0){
            List<Book> books = bookService.getAllByAuthors(authors);
            log.info("List of books by authors:");
            return getListResponseEntity(books);
        } else {
            log.warn("Something wrong with request body!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all books by publishing
     *
     * @param publishing - String[]
     * @return - ResponseEntity of List of BookResponse
     */
    @PostMapping("/getAllByPublishing")
    public ResponseEntity<List<BookResponse>> getAllByPublishing(@RequestBody String[] publishing){
        if(publishing != null && publishing.length > 0){
            List<Book> books = bookService.getAllByPublishing(publishing);
            log.info("List of books by publishing:");
            return getListResponseEntity(books);
        } else {
            log.warn("Something wrong with request body!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all books by genre
     *
     * @param genre - genre
     * @return - ResponseEntity of List of BookResponse
     */
    @PostMapping("/getAllByGenre")
    public ResponseEntity<List<BookResponse>> getAllByGenre(@RequestParam(name = "genre") String genre){
        if(genre != null){
            List<Book> books = bookService.getAllByGenre(genre);
            log.info("List of books by genre:" + genre);
            return getListResponseEntity(books);
        } else {
            log.warn("Incorrect request param!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get ResponseEntity,
     * map all objects and print object to console
     *
     * @param books - List of Book
     * @return - ResponseEntity of List of BookResponse
     */
    private ResponseEntity<List<BookResponse>> getListResponseEntity(List<Book> books) {
        List<BookResponse> response = new ArrayList<>();
        books.forEach(o -> {
            response.add(BookMapper.bookToBookResponse(o));
            //print books in console(books from db)
            log.info(o.toString());
        });
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
