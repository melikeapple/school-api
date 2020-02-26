package com.schoolexample;

import com.schoolexample.model.School;
import com.schoolexample.repository.SchoolRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class SchoolRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    public void
    Setup() {
        School school = new School();
        school.setName("deneme");
        schoolRepository.save(school);
        Assert.assertNotNull(school.getId());
    }
}
