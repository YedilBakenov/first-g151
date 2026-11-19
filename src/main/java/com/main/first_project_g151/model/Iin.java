package com.main.first_project_g151.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "iins")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Iin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean active;

    private LocalDate dateCreate;

    private int number;
}
