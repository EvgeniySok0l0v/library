package com.ysokalau.library.service;

import com.ysokalau.library.entity.Magazine;

import java.util.Date;
import java.util.List;

/**
 * interface for magazine service
 */
public interface MagazineService {

    /**
     * method for create Magazine
     *
     * @param magazine - Magazine
     * @return - Magazine
     */
    Magazine create(Magazine magazine);

    /**
     * method for update Magazine
     *
     * @param magazine - Magazine
     * @param id - id of magazine
     * @return - updated Magazine
     */
    Magazine update(Magazine magazine, Long id);

    /**
     * method for delete magazine by id
     *
     * @param id - id of magazine
     */
    void delete(Long id);

    /**
     * method for get Magazine by id
     *
     * @param id - id of magazine
     * @return - Magazine
     */
    Magazine getById(Long id);

    /**
     * method for get all magazines
     *
     * @return List of Magazine
     */
    List<Magazine> getAll();

    /**
     * method for get all magazines by date
     *
     * @param date - date
     * @return List of Magazine
     */
    List<Magazine> getAllByDate(Date date);

    /**
     * method for get all magazines by authors
     *
     * @param authors - String[]
     * @return List of Magazine
     */
    List<Magazine> getAllByAuthors(String[] authors);

    /**
     * method for get all magazines by publishing
     *
     * @param publishing - String[]
     * @return List of Magazine
     */
    List<Magazine> getAllByPublishing(String[] publishing);

    /**
     * method for get all magazines by period
     *
     * @param startDate - start date
     * @param endDate - end date
     * @return - List of Magazine
     */
    List<Magazine> getAllByPeriod(Date startDate, Date endDate);

    /**
     * method for get all magazines by subject
     *
     * @param subject - subject
     * @return - List of Magazine
     */
    List<Magazine> getAllBySubject(String subject);
}
