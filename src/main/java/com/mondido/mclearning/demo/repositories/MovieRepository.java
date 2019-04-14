package com.mondido.mclearning.demo.repositories;

import com.mondido.mclearning.demo.models.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{
    
}