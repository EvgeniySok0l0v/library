package com.ysokalau.library.web.request;

import lombok.Data;

/**
 * MagazineRequest
 */
@Data
public class MagazineRequest {

    private String name;
    private String[] authors;
    private String[] publishing;
    private String date;
    private String periodicity;
    private String subject;
}
