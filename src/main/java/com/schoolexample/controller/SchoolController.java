package com.schoolexample.controller;

import com.schoolexample.exception.RecordNotFoundException;
import com.schoolexample.exception.Response;
import com.schoolexample.model.School;
import com.schoolexample.repository.SchoolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {
    public static final Logger logger = LoggerFactory.getLogger(SchoolController.class);

    @Autowired
    private SchoolRepository schoolRepository;

    @PostMapping("/school")
    public School createSchool(@RequestBody School school) {
        logger.debug("created" + school);
        return schoolRepository.save(school);
    }

    @GetMapping("/schools")
    public List<School> getAllSchool() {
        logger.debug("Getting all the schools: " + schoolRepository.findAll());
        return schoolRepository.findAll();
    }

    @GetMapping("/school/{id}")
    public Object retrieveSchool(@PathVariable("id") int id) {

        try {
            if (schoolRepository.findById(id).isPresent()) {
                return schoolRepository.findById(id);
            }
            return new Response(HttpStatus.valueOf(404), "School not found", 404);
        } catch (Exception e) {
            throw new RecordNotFoundException(e, this.getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName());
        }

    }


    @DeleteMapping("/school/{id}")
    public void deleteStudent(@PathVariable int id) {
        logger.debug("School deleted succes: " + id);
        schoolRepository.deleteById(id);
    }

    @PutMapping("/school/{id}")
    public Object updateSchool(@RequestBody School school, @PathVariable int id) {
        try {
            if (schoolRepository.findById(id).isPresent()) {
                school.setId(id);
                return schoolRepository.save(school);
            }
            return new Response(HttpStatus.NOT_FOUND, "Id not found", 404);
        } catch (Exception e) {
            throw new RecordNotFoundException(e, this.getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName());
        }

    }

}
