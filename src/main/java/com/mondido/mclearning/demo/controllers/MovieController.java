package com.mondido.mclearning.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.mondido.mclearning.demo.models.Movie;
import com.mondido.mclearning.demo.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepo;

    @GetMapping("/list")
    public List<Movie> getAllMovies(){
        return movieRepo.findAll();    
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable long id){
        Optional<Movie> movie = movieRepo.findById(id);

        if(!movie.isPresent()){
            return new Movie();
        }

        return movie.get();
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie newMovie = movieRepo.saveAndFlush(movie);

		Optional<Movie> mov = movieRepo.findById(newMovie.getId());
        return ResponseEntity.of(mov);
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieRepo.deleteById(id);
        return "Movie "+id+" deleted";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable Long id){
        Optional<Movie> theMovie = movieRepo.findById(id);

		if (!theMovie.isPresent()){
            return ResponseEntity.notFound().build();
        } else {
            movie.setId(id);
            movieRepo.save(movie);
            Optional<Movie> mov = movieRepo.findById(id);
            return ResponseEntity.of(mov);
        }
    }
}