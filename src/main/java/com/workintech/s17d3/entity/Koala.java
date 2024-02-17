package com.workintech.s17d3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Koala {
    private Integer id;
    private String name;
    private Double sleepHour;
    private Double weight;
    private String gender;
}