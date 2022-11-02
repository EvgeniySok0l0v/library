package com.ysokalau.library.service.impl;

import com.ysokalau.library.entity.Magazine;
import com.ysokalau.library.repo.MagazineRepo;
import com.ysokalau.library.service.MagazineService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * service implementation for magazines
 */
@Service
public class MagazineServiceImpl implements MagazineService {

    private final MagazineRepo repo;

    /**
     * constructor MagazineServiceImpl
     *
     * @param repo - MagazineRepo
     */
    public MagazineServiceImpl(MagazineRepo repo) {
        this.repo = repo;
    }

    @Override
    public Magazine create(Magazine magazine) {
        return repo.save(magazine);
    }

    @Override
    public Magazine update(Magazine magazine, Long id) {
        if(repo.findById(id).isPresent()){
            magazine.setId(id);
            return repo.save(magazine);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Cacheable("magazines")
    public Magazine getById(Long id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    @Override
    public List<Magazine> getAll() {
        List<Magazine> magazines = new ArrayList<>();
        repo.findAll().forEach(magazines::add);
        return magazines;
    }

    @Override
    public List<Magazine> getAllByDate(Date date) {
        return repo.getAllByDate(date);
    }

    @Override
    public List<Magazine> getAllByAuthors(String[] authors) {
        Set<Magazine> magazines = new HashSet<>();
        Arrays.stream(authors)
                .filter(s -> s != null && !"".equals(s))
                .forEach(author -> magazines
                        .addAll(repo.getAllByAuthors("%" + author + "%")));
        return magazines.stream().toList();
    }

    @Override
    public List<Magazine> getAllByPublishing(String[] publishing) {
        Set<Magazine> magazines = new HashSet<>();
        Arrays.stream(publishing)
                .filter(s -> s != null && !"".equals(s))
                .forEach(pub -> magazines
                        .addAll(repo.getAllByPublishing("%" + pub + "%")));
        return magazines.stream().toList();
    }

    @Override
    public List<Magazine> getAllByPeriod(Date startDate, Date endDate) {
        return repo.getAllByPeriod(startDate, endDate);
    }

    @Override
    public List<Magazine> getAllBySubject(String subject) {
        return repo.getAllBySubject("%" + subject + "%");
    }
}
