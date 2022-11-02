package com.ysokalau.library.service;

import com.ysokalau.library.entity.Newspaper;

import java.util.Date;
import java.util.List;

/**
 * interface for newspaper service
 */
public interface NewspaperService {

    /**
     * method for create Newspaper
     *
     * @param newspaper - Newspaper
     * @return - Newspaper
     */
    Newspaper create(Newspaper newspaper);

    /**
     * method for update Newspaper
     *
     * @param newspaper - Newspaper
     * @param id - id of newspaper
     * @return - updated Newspaper
     */
    Newspaper update(Newspaper newspaper, Long id);

    /**
     * method for delete newspaper by id
     *
     * @param id - id of newspaper
     */
    void delete(Long id);

    /**
     * method for get newspaper by id
     *
     * @param id - id of newspaper
     * @return - Newspaper
     */
    Newspaper getById(Long id);

    /**
     * method for get all newspapers
     *
     * @return List of Newspaper
     */
    List<Newspaper> getAll();

    /**
     * method for get all newspapers by date
     *
     * @param date - date
     * @return - List of Newspaper
     */
    List<Newspaper> getAllByDate(Date date);

    /**
     * method for get all newspapers by authors
     *
     * @param authors - String[]
     * @return - List of Newspaper
     */
    List<Newspaper> getAllByAuthors(String[] authors);

    /**
     * method for get all newspapers by publishing
     *
     * @param publishing - String[]
     * @return - List of Newspaper
     */
    List<Newspaper> getAllByPublishing(String[] publishing);

    /**
     * method for get all newspapers by period
     *
     * @param startDate - start date
     * @param endDate - end date
     * @return - List of Newspaper
     */
    List<Newspaper> getAllByPeriod(Date startDate, Date endDate);

    /**
     * method for get all newspapers by country
     *
     * @param country - country
     * @return - List of Newspaper
     */
    List<Newspaper> getAllByCountry(String country);

}
