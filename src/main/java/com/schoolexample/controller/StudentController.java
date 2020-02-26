package com.schoolexample.controller;

import com.schoolexample.exception.RecordNotFoundException;
import com.schoolexample.exception.Response;
import com.schoolexample.model.Student;
import com.schoolexample.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student) {
        logger.debug("Created new students: " + student);
        return studentRepository.save(student);
    }

    @GetMapping("/students")
    public List<Student> retrieveAllStudents() {
        logger.debug("Getting all students: " + studentRepository.findAll());
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public Response retrieveStudent(@PathVariable int id) {
        try {
            studentRepository.findById(id);
            if (studentRepository.findById(id).isPresent()) {
                return new Response(HttpStatus.OK, "Ok", 200);
            }
            return new Response(HttpStatus.NOT_FOUND, "Response not found", 404);
        } catch (Exception e) {
            throw new RecordNotFoundException(e, this.getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName());
        }


    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable int id) {
        logger.debug("Student deleted success: " + id);
        studentRepository.deleteById(id);
    }

    @PutMapping("/student/{id}")
    public Response updateStudent(@RequestBody Student student, @PathVariable int id) {
        try {

            if (studentRepository.findById(id).isPresent()) {
                student.setId(id);
                return new Response(HttpStatus.OK, "", 200);
            }
            return new Response(HttpStatus.NOT_FOUND, "Response not found", 404);
        } catch (Exception e) {
            throw new RecordNotFoundException(e, this.getClass(), new Object() {
            }.getClass().getEnclosingMethod().getName());
        }
    }
}
