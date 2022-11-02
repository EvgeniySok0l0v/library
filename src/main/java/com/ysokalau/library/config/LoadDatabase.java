package com.ysokalau.library.config;

import com.ysokalau.library.entity.Book;
import com.ysokalau.library.entity.Magazine;
import com.ysokalau.library.entity.Newspaper;
import com.ysokalau.library.repo.BookRepo;
import com.ysokalau.library.repo.MagazineRepo;
import com.ysokalau.library.repo.NewspaperRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;

/**
 * Class for load data to the DB
 */
@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(BookRepo bookRepo, MagazineRepo magazineRepo, NewspaperRepo newspaperRepo) {

        return args -> {
            log.info("Books:\n");
            log.info("Preloading:" + bookRepo.save(new Book(
                    1L,
                    "Harry Potter and the Philosopher's Stone",
                    "J.K.Rowling",
                    "Bloomsbury",
                    Date.valueOf("1997-06-26"),
                    "fantasy")));
            log.info("Preloading:" + bookRepo.save(new Book(
                    2L,
                    "Harry Potter and the Chamber of Secrets",
                    "J.K.Rowling",
                    "Bloomsbury",
                    Date.valueOf("1998-07-02"),
                    "fantasy")));
            log.info("Preloading:" + bookRepo.save(new Book(
                    3L,
                    "Harry Potter and the Prisoner of Azkaban",
                    "J.K.Rowling",
                    "Bloomsbury",
                    Date.valueOf("1999-07-08"),
                    "fantasy,novel")));
            log.info("Preloading:" + bookRepo.save(new Book(
                    4L,
                    "Harry Potter and the Goblet of Fire",
                    "J.K.Rowling",
                    "Bloomsbury" +
                            "Scholastic,",
                    Date.valueOf("2000-07-08"),
                    "fantasy,drama,novel")));
            log.info("Preloading:" + bookRepo.save(new Book(
                    5L,
                    "Harry Potter and the Order of the Phoenix",
                    "J.K.Rowling",
                    "Bloomsbury," +
                            "Scholastic," +
                            "Raincoast," +
                            "Росмэн-Издат",
                    Date.valueOf("2003-06-21"),
                    "fantasy,drama")));
            log.info("Preloading:" + bookRepo.save(new Book(
                    6L,
                    "Harry Potter and the Half-Blood Prince",
                    "J.K.Rowling",
                    "Bloomsbury," +
                            "Scholastic," +
                            "Raincoast," +
                            "Росмэн-Издат",
                    Date.valueOf("2005-07-16"),
                    "fantasy")));
            log.info("Preloading:" + bookRepo.save(new Book(
                    7L,
                    "Harry Potter and the Deathly Hallows",
                    "J.K.Rowling",
                    "Bloomsbury",
                    Date.valueOf("2007-07-21"),
                    "fantasy,drama,novel")));

            log.info("Magazines:\n");
            log.info("Preloading:" + magazineRepo.save(new Magazine(
                    1L,
                    "Playboy",
                    "Hugh Marston Hefner",
                    "Playboy Enterprises,Hubert Burda,Media Holding",
                    Date.valueOf("2022-07-01"),
                    "1 month",
                    "Erotic")));
            log.info("Preloading:" + magazineRepo.save(new Magazine(
                    2L,
                    "Playboy",
                    "Hugh Marston Hefner",
                    "Playboy Enterprises,Hubert Burda,Media Holding",
                    Date.valueOf("2022-08-01"),
                    "1 month",
                    "Erotic")));
            log.info("Preloading:" + magazineRepo.save(new Magazine(
                    3L,
                    "Playboy",
                    "Hugh Marston Hefner",
                    "Playboy Enterprises,Hubert Burda,Media Holding",
                    Date.valueOf("2022-09-01"),
                    "1 month",
                    "Erotic")));

            log.info("Newspapers:\n");
            log.info("Preloading:" + newspaperRepo.save(new Newspaper(
                    1L,
                    "Правда Гомель",
                    "Не знаю",
                    "Любое1",
                    Date.valueOf("2022-09-01"),
                    "1 week",
                    "Belarus")));
            log.info("Preloading:" + newspaperRepo.save(new Newspaper(
                    2L,
                    "Правда Гомель",
                    "Не знаю",
                    "Любое2",
                    Date.valueOf("2022-09-08"),
                    "1 week",
                    "Belarus")));
            log.info("Preloading:" + newspaperRepo.save(new Newspaper(
                    3L,
                    "Правда-Неправда",
                    "Не знаю, Без понятия",
                    "Любое2, Абсолютно любое",
                    Date.valueOf("1999-09-18"),
                    "2 week",
                    "Belarus")));
        };
    }
}
