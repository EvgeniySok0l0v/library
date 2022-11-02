package com.ysokalau.library.repo;

import com.ysokalau.library.entity.Book;
import com.ysokalau.library.entity.Magazine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * repository for magazines
 */
@Repository
public interface MagazineRepo extends CrudRepository<Magazine, Long> {

    List<Magazine> getAllByDate(Date date);

    @Query(nativeQuery = true, value = "select * from magazine where magazine.authors like :authors")
    List<Magazine> getAllByAuthors(String authors);

    @Query(nativeQuery = true, value = "select * from magazine where magazine.publishing like :publishing")
    List<Magazine> getAllByPublishing(String publishing);

    @Query(nativeQuery = true,
            value = "select * from magazine where magazine.date >= :startDate and magazine.date <= :endDate")
    List<Magazine> getAllByPeriod(Date startDate, Date endDate);

    @Query(nativeQuery = true, value = "select * from magazine where magazine.subject like :subject")
    List<Magazine> getAllBySubject(String subject);

}
