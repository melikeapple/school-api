package com.schoolexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolexample.controller.SchoolController;
import com.schoolexample.model.School;
import com.schoolexample.repository.SchoolRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SchoolController.class)
@ContextConfiguration(classes = {SchoolController.class, SchoolRepository.class})
public class SchoolControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SchoolRepository repository;

    @Test
    public void findAllSchool() throws Exception {
        School school = new School();
        school.setId(1);
        school.setName("melike");
        List<School> schools = Arrays.asList(school);
        given(repository.findAll()).willReturn(schools);
        this.mockMvc.perform(get("/schools"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'melike'}]"));
    }

    @Test
    public void retrieveTestSchool() throws Exception {

        School school = new School();
        school.setId(2);
        school.setName("deneme");
        given(repository.findById(2)).willReturn(Optional.of(school));

        this.mockMvc.perform(get("/school/2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 2, 'name': 'deneme'}"));
    }

    @Test
    public void addNewSchoolTest() throws Exception {
        School school = new School();
        school.setId(14);
        school.setName("deneme2");
        this.mockMvc.perform(post("/school")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(school))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSchool() throws Exception {
        this.mockMvc.perform(delete("/school/1", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void updateSchool() throws Exception {
        School updateSchool = new School();
        updateSchool.setName("deneme3");
        updateSchool.setId(3);
        given(repository.findById(3)).willReturn(Optional.of(updateSchool));
        this.mockMvc.perform(put("/school/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateSchool))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
