package com.schoolexample.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "school")
public class School {
    @Id
    int id;
    String name;
    @OneToMany(
            mappedBy = "school",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Teacher> teacher = new ArrayList<>();
    @OneToMany(
            mappedBy = "school",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Student> student = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public School() {
    }
}
