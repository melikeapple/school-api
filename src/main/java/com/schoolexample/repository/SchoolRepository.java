package com.schoolexample.repository;

import com.schoolexample.model.School;
import com.schoolexample.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Integer> {

}
