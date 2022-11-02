package com.ysokalau.library.web.response;

import lombok.Data;

/**
 * BookResponse
 */
@Data
public class BookResponse {

    private Long id;
    private String name;
    private String[] authors;
    private String[] publishing;
    private String date;
    private String genre;
}
