package com.ysokalau.library.web.request;

import lombok.Data;

/**
 * BookRequest
 */
@Data
public class BookRequest {

    private String name;
    private String[] authors;
    private String[] publishing;
    private String date;
    private String genre;
}
