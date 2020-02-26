package com.schoolexample.controller;

import com.schoolexample.exception.RecordNotFoundException;
import com.schoolexample.exception.Response;
import com.schoolexample.model.Teacher;
import com.schoolexample.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    TeacherRepository teacherRepository;

    @PostMapping("/teacher")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        logger.info("Creating one teacher");
        logger.debug("Created one teacher: " + teacherRepository.save(teacher));
        return teacherRepository.save(teacher);
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllSchool() {
        logger.debug("Getting all the schools: " + teacherRepository.findAll());
        return teacherRepository.findAll();
    }

    @GetMapping("/teacher/{id}")
    public Object retrieveTeacher(@PathVariable int id) {
        try {
            if (teacherRepository.findById(id).isPresent()) {
                return teacherRepository.findById(id);
            }
            return new Response(HttpStatus.NOT_FOUND.value(), "Response not found", 404);
        } catch (Exception e) {
            throw new RecordNotFoundException(e, this.getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName());
        }


    }

    @DeleteMapping("/teacher/{id}")
    public void deleteStudent(@PathVariable int id) {
        logger.info("Deleting one teacher");
        logger.debug("Deleted succes");
        teacherRepository.deleteById(id);
    }

    @PutMapping("/teacher/{id}")
    public Object updateTeacher(@RequestBody Teacher teacher, @PathVariable int id, String name) {
        try {

            if (teacherRepository.findById(id).isPresent()) {
                teacher.setId(id);
                return new Response(HttpStatus.OK.value(), "", 200);
            }
            return new Response(HttpStatus.NOT_FOUND.value(), "Response not found", 404);
        } catch (Exception e) {
            throw new RecordNotFoundException(e, this.getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName());
        }

    }
}
