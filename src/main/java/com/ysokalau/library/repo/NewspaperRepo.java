package com.ysokalau.library.repo;

import com.ysokalau.library.entity.Newspaper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * repository for newspapers
 */
@Repository
public interface NewspaperRepo extends CrudRepository<Newspaper, Long> {

    List<Newspaper> getAllByDate(Date date);

    @Query(nativeQuery = true, value = "select * from newspaper where newspaper.authors like :authors")
    List<Newspaper> getAllByAuthors(String authors);

    @Query(nativeQuery = true, value = "select * from newspaper where newspaper.publishing like :publishing")
    List<Newspaper> getAllByPublishing(String publishing);

    @Query(nativeQuery = true,
            value = "select * from newspaper where newspaper.date >= :startDate and newspaper.date <= :endDate")
    List<Newspaper> getAllByPeriod(Date startDate, Date endDate);
    @Query(nativeQuery = true, value = "select * from newspaper where newspaper.country like :country")
    List<Newspaper> getAllByCountry(String country);
}
