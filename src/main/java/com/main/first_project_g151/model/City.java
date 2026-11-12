package com.main.first_project_g151.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {

    private Integer id;

    private String cityName;

    private int countPeople;

    private double rating;
}
