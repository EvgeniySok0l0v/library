package com.ysokalau.library.mapper;

import com.ysokalau.library.entity.Book;
import com.ysokalau.library.util.StringUtils;
import com.ysokalau.library.web.request.BookRequest;
import com.ysokalau.library.web.response.BookResponse;

import java.sql.Date;

/**
 * mapper for books
 */
public class BookMapper {

    /**
     * method for map BookRequest to Book
     *
     * @param request - BookRequest
     * @return - Book
     */
    public static Book bookRequestToBook(BookRequest request){
        if(checkRequest(request)) {
            Book book = new Book();
            book.setName(request.getName());
            book.setAuthors(StringUtils.arrayToString(request.getAuthors()));
            book.setPublishing(StringUtils.arrayToString(request.getPublishing()));
            book.setDate(Date.valueOf(request.getDate()));
            book.setGenre(request.getGenre());
            return book;
        } else {
            return null;
        }
    }

    /**
     * method for map Book to BookResponse
     *
     * @param book - Book
     * @return - BookResponse
     */
    public static BookResponse bookToBookResponse(Book book){
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setAuthors(StringUtils.stringToArray(book.getAuthors()));
        response.setPublishing(StringUtils.stringToArray(book.getPublishing()));
        response.setDate(book.getDate().toString());
        response.setGenre(book.getGenre());
        return response;
    }

    /**
     * method for check request data
     *
     * @param request - BookRequest
     * @return - boolean
     */
    private static boolean checkRequest(BookRequest request){
        return StringUtils.checkString(request.getName())
                && StringUtils.checkString(request.getGenre())
                && StringUtils.checkArray(request.getAuthors())
                && StringUtils.checkArray(request.getPublishing())
                && request.getDate().matches(StringUtils.DATE_PATTERN.pattern());
    }
}
