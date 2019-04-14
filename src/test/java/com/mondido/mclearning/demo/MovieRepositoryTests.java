package com.mondido.mclearning.demo;

import static org.junit.Assert.assertEquals;

import com.mondido.mclearning.demo.models.Movie;
import com.mondido.mclearning.demo.repositories.MovieRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTests {

    @Autowired
    MovieRepository movieRepo;

    private Movie testMovie;
    @Before
    public void setup() throws Exception {
        testMovie = new Movie("Test Movie", "Action", 1991, 5.2);
    }

    @Test
    public void shouldSaveMovie(){
        Movie movie = movieRepo.saveAndFlush(testMovie);

        Movie targetMovie = movieRepo.findById(movie.getId()).get();
        assertEquals(targetMovie.getId(), movie.getId());
    }


}