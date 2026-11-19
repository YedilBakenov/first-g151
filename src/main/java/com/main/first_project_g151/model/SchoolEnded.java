package com.main.first_project_g151.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schools")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SchoolEnded {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int numberSchool;

    private String addressSchool;
}
