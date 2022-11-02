package com.ysokalau.library.mapper;

import com.ysokalau.library.entity.Magazine;
import com.ysokalau.library.util.StringUtils;
import com.ysokalau.library.web.request.MagazineRequest;
import com.ysokalau.library.web.response.MagazineResponse;

import java.sql.Date;

/**
 * mapper for magazines
 */
public class MagazineMapper {

    /**
     * method  for map MagazineRequest to Magazine
     *
     * @param request - MagazineRequest
     * @return - Magazine
     */
    public static Magazine magazineRequestToMagazine(MagazineRequest request){
        if(checkRequest(request)) {
            Magazine magazine = new Magazine();
            magazine.setName(request.getName());
            magazine.setAuthors(StringUtils.arrayToString(request.getAuthors()));
            magazine.setPublishing(StringUtils.arrayToString(request.getPublishing()));
            magazine.setDate(Date.valueOf(request.getDate()));
            magazine.setPeriodicity(request.getPeriodicity());
            magazine.setSubject(request.getSubject());
            return magazine;
        } else {
            return null;
        }
    }

    /**
     * method for map Magazine to MagazineResponse
     *
     * @param magazine - Magazine
     * @return - MagazineResponse
     */
    public static MagazineResponse magazineToMagazineResponse(Magazine magazine){
        MagazineResponse response = new MagazineResponse();
        response.setId(magazine.getId());
        response.setName(magazine.getName());
        response.setAuthors(StringUtils.stringToArray(magazine.getAuthors()));
        response.setPublishing(StringUtils.stringToArray(magazine.getPublishing()));
        response.setDate(magazine.getDate().toString());
        response.setPeriodicity(magazine.getPeriodicity());
        response.setSubject(magazine.getSubject());
        return response;
    }

    /**
     * method for check request data
     *
     * @param request - MagazineRequest
     * @return - boolean
     */
    private static boolean checkRequest(MagazineRequest request){
        return StringUtils.checkString(request.getName())
                && StringUtils.checkString(request.getSubject())
                && StringUtils.checkString(request.getPeriodicity())
                && StringUtils.checkArray(request.getAuthors())
                && StringUtils.checkArray(request.getPublishing())
                && request.getDate().matches(StringUtils.DATE_PATTERN.pattern());
    }
}
