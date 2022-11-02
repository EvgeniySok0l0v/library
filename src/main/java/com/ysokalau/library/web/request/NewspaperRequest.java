package com.ysokalau.library.web.request;

import lombok.Data;

/**
 * NewspaperRequest
 */
@Data
public class NewspaperRequest {

    private String name;
    private String[] authors;
    private String[] publishing;
    private String date;
    private String periodicity;
    private String country;
}
