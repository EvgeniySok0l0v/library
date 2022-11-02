package com.ysokalau.library.web.controller;

import com.ysokalau.library.entity.Magazine;
import com.ysokalau.library.mapper.MagazineMapper;
import com.ysokalau.library.service.MagazineService;
import com.ysokalau.library.web.request.MagazineRequest;
import com.ysokalau.library.web.response.MagazineResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.ysokalau.library.util.StringUtils.DATE_PATTERN;

/**
 * MagazineController
 */
@Slf4j
@RequestMapping("/api/magazine")
@RestController
public class MagazineController {

    private final MagazineService magazineService;

    /**
     * constructor MagazineController
     *
     * @param magazineService - MagazineService
     */
    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    /**
     * method for get all magazines
     *
     * @return - List of Magazine
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<MagazineResponse>> getAll(){
        log.info("List of all magazines:");
        return getListResponseEntity(magazineService.getAll());
    }

    /**
     * method for get magazine by id
     *
     * @param id - id of magazine
     * @return - Magazine
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity<MagazineResponse> getById(@PathVariable Long id){
        Magazine magazineFromDb = magazineService.getById(id);
        if(magazineFromDb != null) {
            MagazineResponse response = MagazineMapper
                    .magazineToMagazineResponse(magazineFromDb);
            log.info("Magazine with id:" + id + "\n" + magazineFromDb);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.warn("Magazine with id:" + id + " not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * method for create Magazine
     *
     * @param request - MagazineRequest
     * @return - ResponseEntity of MagazineResponse
     */
    @PostMapping("/create")
    public ResponseEntity<MagazineResponse> create(@RequestBody MagazineRequest request){
        Magazine magazine = MagazineMapper.magazineRequestToMagazine(request);
        if(magazine != null) {
            MagazineResponse response = MagazineMapper
                    .magazineToMagazineResponse(magazineService.create(magazine));
            log.info("Magazine:" + magazine + "\nwas created!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.warn("Something wrong with MagazineRequest:" + request);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for update magazine
     *
     * @param request - MagazineRequest
     * @param id - id of magazine
     * @return - ResponseEntity of String
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody MagazineRequest request,
                                         @PathVariable Long id){
        Magazine magazine = MagazineMapper.magazineRequestToMagazine(request);
        if(magazine != null) {
            if (magazineService.update(magazine, id) != null) {
                log.info("Magazine with id:" + magazine.getId() + " was updated!");
                return new ResponseEntity<>("Magazine with id:" + magazine.getId() + " was updated!", HttpStatus.OK);
            } else {
                log.warn("Magazine with id:" + id + " not found!");
                return new ResponseEntity<>("Magazine with id:"
                        + magazine.getId() + " not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn("Something wrong with MagazineRequest:" + request);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for delete magazine by id
     *
     * @param id - id of magazine
     * @return - ResponseEntity of String
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(magazineService.getById(id) != null) {
            magazineService.delete(id);
            log.info("Magazine with id:" + id + " was deleted!");
            return new ResponseEntity<>("Magazine with id:" + id + " was deleted!", HttpStatus.OK);
        } else {
            log.warn("Magazine with id:" + id + " not found!");
            return new ResponseEntity<>("Magazine with id:" + id + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * method for get all  magazines by date
     *
     * @param date - date
     * @return - ResponseEntity of List of MagazineResponse
     */
    @PostMapping("/getAllByDate")
    public ResponseEntity<List<MagazineResponse>> getAllByDate(@RequestParam(name = "date") String date){
        if(date.matches(DATE_PATTERN.pattern())){
            List<Magazine> books = magazineService.getAllByDate(Date.valueOf(date));
            log.info("List of magazines by date:" + date);
            return getListResponseEntity(books);
        } else {
            log.warn("Incorrect date format!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all magazines by period
     *
     * @param startDate - start date
     * @param endDate - start date
     * @return - ResponseEntity of List of MagazineResponse
     */
    @PostMapping("/getAllByPeriod")
    public ResponseEntity<List<MagazineResponse>> getAllPeriod(@RequestParam(name = "start") String startDate,
                                                           @RequestParam(name = "end") String endDate){
        if(startDate.matches(DATE_PATTERN.pattern()) && endDate.matches(DATE_PATTERN.pattern())){
            List<Magazine> magazines = magazineService.getAllByPeriod(Date.valueOf(startDate), Date.valueOf(endDate));
            log.info("List of magazines by period:from " + startDate + " to " + endDate);
            return getListResponseEntity(magazines);
        } else {
            log.warn("Incorrect date format!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all magazines by authors
     *
     * @param authors - String[]
     * @return - ResponseEntity of List of MagazineResponse
     */
    @PostMapping("/getAllByAuthors")
    public ResponseEntity<List<MagazineResponse>> getAllByAuthors(@RequestBody String[] authors){
        if(authors != null && authors.length > 0){
            List<Magazine> magazines = magazineService.getAllByAuthors(authors);
            log.info("List of magazines by authors:");
            return getListResponseEntity(magazines);
        } else {
            log.warn("Something wrong with request body!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all magazines by publishing
     *
     * @param publishing - String[]
     * @return - ResponseEntity of List of MagazineResponse
     */
    @PostMapping("/getAllByPublishing")
    public ResponseEntity<List<MagazineResponse>> getAllByPublishing(@RequestBody String[] publishing){
        if(publishing != null && publishing.length > 0){
            List<Magazine> magazines = magazineService.getAllByPublishing(publishing);
            log.info("List of magazines by publishing:");
            return getListResponseEntity(magazines);
        } else {
            log.warn("Something wrong with request body!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * method for get all magazines by subject
     *
     * @param subject - subject
     * @return - ResponseEntity of List of MagazineResponse
     */
    @PostMapping("/getAllBySubject")
    public ResponseEntity<List<MagazineResponse>> getAllBySubject(@RequestParam(name = "subject") String subject){
        if(subject != null){
            List<Magazine> magazines = magazineService.getAllBySubject(subject);
            log.info("List of magazines by subject:" + subject);
            return getListResponseEntity(magazines);
        } else {
            log.warn("Incorrect request param!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get ResponseEntity,
     * map all objects and print object to console
     *
     * @param magazines - List of Magazine
     * @return - ResponseEntity of List of MagazineResponse
     */
    private ResponseEntity<List<MagazineResponse>> getListResponseEntity(List<Magazine> magazines) {
        List<MagazineResponse> response = new ArrayList<>();
        magazines.forEach(o -> {
            response.add(MagazineMapper.magazineToMagazineResponse(o));
            //print magazines in console(magazines from db)
            log.info(o.toString());
        });
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
