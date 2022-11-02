package com.ysokalau.library.mapper;

import com.ysokalau.library.entity.Newspaper;
import com.ysokalau.library.util.StringUtils;
import com.ysokalau.library.web.request.NewspaperRequest;
import com.ysokalau.library.web.response.NewspaperResponse;

import java.sql.Date;

/**
 * mapper for newspapers
 */
public class NewspaperMapper {

    /**
     * method for map NewspaperRequest to Newspaper
     *
     * @param request - NewspaperRequest
     * @return - Newspaper
     */
    public static Newspaper newspaperRequestToNewspaper(NewspaperRequest request){
        if(checkRequest(request)) {
            Newspaper newspaper = new Newspaper();
            newspaper.setName(request.getName());
            newspaper.setAuthors(StringUtils.arrayToString(request.getAuthors()));
            newspaper.setPublishing(StringUtils.arrayToString(request.getPublishing()));
            newspaper.setDate(Date.valueOf(request.getDate()));
            newspaper.setPeriodicity(request.getPeriodicity());
            newspaper.setCountry(request.getCountry());
            return newspaper;
        } else {
            return null;
        }
    }

    /**
     * method for map Newspaper to NewspaperResponse
     *
     * @param newspaper - Newspaper
     * @return - NewspaperResponse
     */
    public static NewspaperResponse newspaperToNewspaperResponse(Newspaper newspaper){
        NewspaperResponse response = new NewspaperResponse();
        response.setId(newspaper.getId());
        response.setName(newspaper.getName());
        response.setAuthors(StringUtils.stringToArray(newspaper.getAuthors()));
        response.setPublishing(StringUtils.stringToArray(newspaper.getPublishing()));
        response.setDate(newspaper.getDate().toString());
        response.setPeriodicity(newspaper.getPeriodicity());
        response.setCountry(newspaper.getCountry());
        return response;
    }

    /**
     * method for check request data
     *
     * @param request - NewspaperRequest
     * @return - boolean
     */
    private static boolean checkRequest(NewspaperRequest request){
        return StringUtils.checkString(request.getName())
                && StringUtils.checkString(request.getCountry())
                && StringUtils.checkString(request.getPeriodicity())
                && StringUtils.checkArray(request.getAuthors())
                && StringUtils.checkArray(request.getPublishing())
                && request.getDate().matches(StringUtils.DATE_PATTERN.pattern());
    }
}
