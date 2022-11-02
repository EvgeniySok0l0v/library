package com.ysokalau.library.service.impl;

import com.ysokalau.library.entity.Newspaper;
import com.ysokalau.library.repo.NewspaperRepo;
import com.ysokalau.library.service.NewspaperService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * service implementation for newspapers
 */
@Service
public class NewspaperServiceImpl implements NewspaperService {

    private final NewspaperRepo repo;

    /**
     * constructor NewspaperServiceImpl
     *
     * @param repo - NewspaperRepo
     */
    public NewspaperServiceImpl(NewspaperRepo repo) {
        this.repo = repo;
    }

    @Override
    public Newspaper create(Newspaper newspaper) {
        return repo.save(newspaper);
    }

    @Override
    public Newspaper update(Newspaper newspaper, Long id) {
        if(repo.findById(id).isPresent()){
            newspaper.setId(id);
            return repo.save(newspaper);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Cacheable("newspapers")
    public Newspaper getById(Long id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    @Override
    public List<Newspaper> getAll() {
        List<Newspaper> newspapers = new ArrayList<>();
        repo.findAll().forEach(newspapers::add);
        return newspapers;
    }

    @Override
    public List<Newspaper> getAllByDate(Date date) {
        return repo.getAllByDate(date);
    }

    @Override
    public List<Newspaper> getAllByAuthors(String[] authors) {
        Set<Newspaper> newspapers = new HashSet<>();
        Arrays.stream(authors)
                .filter(s -> s != null && !"".equals(s))
                .forEach(author -> newspapers
                        .addAll(repo.getAllByAuthors("%" + author + "%")));
        return newspapers.stream().toList();
    }

    @Override
    public List<Newspaper> getAllByPublishing(String[] publishing) {
        Set<Newspaper> newspapers = new HashSet<>();
        Arrays.stream(publishing)
                .filter(s -> s != null && !"".equals(s))
                .forEach(pub -> newspapers
                        .addAll(repo.getAllByPublishing("%" + pub + "%")));
        return newspapers.stream().toList();
    }

    @Override
    public List<Newspaper> getAllByPeriod(Date startDate, Date endDate) {
        return repo.getAllByPeriod(startDate, endDate);
    }

    @Override
    public List<Newspaper> getAllByCountry(String country) {
        return repo.getAllByCountry("%" + country + "%");
    }
}
