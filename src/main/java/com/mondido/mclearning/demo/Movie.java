package com.mondido.mclearning.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String genre;
    private int yearReleased;
    private double rating; 

    public String getName(){
        return name;
    }

    public String getGenre(){
        return genre;
    }

    public int getYearReleased(){
        return yearReleased;
    }

    public double getRating(){
        return rating;
    }
}