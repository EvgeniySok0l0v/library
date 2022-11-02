package com.ysokalau.library.web.response;

import lombok.Data;

/**
 * MagazineResponse
 */
@Data
public class MagazineResponse {

    private Long id;
    private String name;
    private String[] authors;
    private String[] publishing;
    private String date;
    private String periodicity;
    private String subject;
}
