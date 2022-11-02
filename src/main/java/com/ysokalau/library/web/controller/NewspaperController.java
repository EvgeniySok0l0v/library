package com.ysokalau.library.web.controller;

import com.ysokalau.library.entity.Newspaper;
import com.ysokalau.library.mapper.NewspaperMapper;
import com.ysokalau.library.service.NewspaperService;
import com.ysokalau.library.web.request.NewspaperRequest;
import com.ysokalau.library.web.response.NewspaperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.ysokalau.library.util.StringUtils.DATE_PATTERN;

/**
 * NewspaperController
 */
@Slf4j
@RequestMapping("/api/newspaper")
@RestController
public class NewspaperController {

    private final NewspaperService newspaperService;

    /**
     * constructor NewspaperController
     *
     * @param newspaperService - NewspaperService
     */
    public NewspaperController(NewspaperService newspaperService) {
        this.newspaperService = newspaperService;
    }

    /**
     * method for get all newspapers
     *
     * @return - List of Newspaper
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<NewspaperResponse>> getAll(){
        log.info("List of all newspapers:");
        return getListResponseEntity(newspaperService.getAll());
    }

    /**
     * method for get newspaper by id
     *
     * @param id - id of newspaper
     * @return - Newspaper
     */
    @GetMapping("/getById/{id}")
    public ResponseEntity<NewspaperResponse> getById(@PathVariable Long id){
        Newspaper newspaperFromDb = newspaperService.getById(id);
        if(newspaperFromDb != null) {
            NewspaperResponse response = NewspaperMapper
                    .newspaperToNewspaperResponse(newspaperFromDb);
            log.info("Newspaper with id:" + id + "\n" + newspaperFromDb);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.warn("Newspaper with id:" + id + " not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * method for create Newspaper
     *
     * @param request - NewspaperRequest
     * @return - ResponseEntity of NewspaperResponse
     */
    @PostMapping("/create")
    public ResponseEntity<NewspaperResponse> create(@RequestBody NewspaperRequest request){
        Newspaper newspaper = NewspaperMapper.newspaperRequestToNewspaper(request);
        if(newspaper != null) {
            NewspaperResponse response = NewspaperMapper
                    .newspaperToNewspaperResponse(newspaperService.create(newspaper));
            log.info("Newspaper:" + newspaper + "\nwas created!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.warn("Something wrong with NewspaperRequest:" + request);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for update newspaper
     *
     * @param request - NewspaperRequest
     * @param id - id of newspaper
     * @return - ResponseEntity of String
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody NewspaperRequest request,
                                         @PathVariable Long id){
        Newspaper newspaper = NewspaperMapper.newspaperRequestToNewspaper(request);
        if(newspaper != null) {
            if (newspaperService.update(newspaper, id) != null) {
                log.info("Newspaper with id:" + newspaper.getId() + " was updated!");
                return new ResponseEntity<>("Newspaper with id:" + newspaper.getId() + " was updated!", HttpStatus.OK);
            } else {
                log.warn("Newspaper with id:" + id + " not found!");
                return new ResponseEntity<>("Newspaper with id:"
                        + newspaper.getId() + " not found!", HttpStatus.NOT_FOUND);
            }
        } else {
            log.warn("Something wrong with NewspaperRequest:" + request);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for delete newspaper by id
     *
     * @param id - id of newspaper
     * @return - ResponseEntity of String
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(newspaperService.getById(id) != null) {
            newspaperService.delete(id);
            log.info("Newspaper with id:" + id + " was deleted!");
            return new ResponseEntity<>("Newspaper with id:" + id + " was deleted!", HttpStatus.OK);
        } else {
            log.warn("Newspaper with id:" + id + " not found!");
            return new ResponseEntity<>("Newspaper with id:" + id + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * method for get all  newspapers by date
     *
     * @param date - date
     * @return - ResponseEntity of List of NewspaperResponse
     */
    @PostMapping("/getAllByDate")
    public ResponseEntity<List<NewspaperResponse>> getAllByDate(@RequestParam(name = "date") String date){
        if(date.matches(DATE_PATTERN.pattern())){
            List<Newspaper> newspapers = newspaperService.getAllByDate(Date.valueOf(date));
            log.info("List of newspapers by date:" + date);
            return getListResponseEntity(newspapers);
        } else {
            log.warn("Incorrect date format!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all newspapers by period
     *
     * @param startDate - start date
     * @param endDate - start date
     * @return - ResponseEntity of List of NewspaperResponse
     */
    @PostMapping("/getAllByPeriod")
    public ResponseEntity<List<NewspaperResponse>> getAllPeriod(@RequestParam(name = "start") String startDate,
                                                               @RequestParam(name = "end") String endDate){
        if(startDate.matches(DATE_PATTERN.pattern()) && endDate.matches(DATE_PATTERN.pattern())){
            List<Newspaper> newspapers = newspaperService
                    .getAllByPeriod(Date.valueOf(startDate), Date.valueOf(endDate));
            log.info("List of newspapers by period:from " + startDate + " to " + endDate);
            return getListResponseEntity(newspapers);
        } else {
            log.warn("Incorrect date format!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all newspapers by authors
     *
     * @param authors - String[]
     * @return - ResponseEntity of List of NewspaperResponse
     */
    @PostMapping("/getAllByAuthors")
    public ResponseEntity<List<NewspaperResponse>> getAllByAuthors(@RequestBody String[] authors){
        if(authors != null && authors.length > 0){
            List<Newspaper> newspapers = newspaperService.getAllByAuthors(authors);
            log.info("List of newspapers by authors:");
            return getListResponseEntity(newspapers);
        } else {
            log.warn("Something wrong with request body!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all newspapers by publishing
     *
     * @param publishing - String[]
     * @return - ResponseEntity of List of NewspaperResponse
     */
    @PostMapping("/getAllByPublishing")
    public ResponseEntity<List<NewspaperResponse>> getAllByPublishing(@RequestBody String[] publishing){
        if(publishing != null && publishing.length > 0){
            List<Newspaper> newspapers = newspaperService.getAllByPublishing(publishing);
            log.info("List of newspapers by publishing:");
            return getListResponseEntity(newspapers);
        } else {
            log.warn("Something wrong with request body!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get all newspapers by country
     *
     * @param country - country
     * @return - ResponseEntity of List of NewspaperResponse
     */
    @PostMapping("/getAllByCountry")
    public ResponseEntity<List<NewspaperResponse>> getAllBySubject(@RequestParam(name = "country") String country){
        if(country != null){
            List<Newspaper> newspapers = newspaperService.getAllByCountry(country);
            log.info("List of newspapers by country:" + country);
            return getListResponseEntity(newspapers);
        } else {
            log.warn("Incorrect request param!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * method for get ResponseEntity,
     * map all objects and print object to console
     *
     * @param newspapers - List of Newspaper
     * @return - ResponseEntity of List of NewspaperResponse
     */
    private ResponseEntity<List<NewspaperResponse>> getListResponseEntity(List<Newspaper> newspapers) {
        List<NewspaperResponse> response = new ArrayList<>();
        newspapers.forEach(o -> {
            response.add(NewspaperMapper.newspaperToNewspaperResponse(o));
            //print newspapers in console(newspapers from db)
            log.info(o.toString());
        });
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
