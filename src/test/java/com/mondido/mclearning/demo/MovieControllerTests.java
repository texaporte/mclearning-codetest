package com.mondido.mclearning.demo;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mondido.mclearning.demo.models.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextConfiguration(classes=DemoApplication.class)
public class MovieControllerTests {

    private MockMvc mockMvc;

    @Autowired
    EntityManager entityManager;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getMovies() throws Exception {
        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/movie/list");
        MockHttpServletResponse result = this.mockMvc.perform(reqBuilder).andReturn().getResponse();
        String response = result.getContentAsString();
        assertEquals(result.getStatus(), HttpStatus.OK.value());

        ObjectMapper objectMapper = new ObjectMapper();
        Movie[] movieMap = objectMapper.readValue(response, Movie[].class);
        assertEquals(movieMap.length, 5);
    }

    @Test
    public void getMovie() throws Exception{
        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/movie/1");
        MockHttpServletResponse result = this.mockMvc.perform(reqBuilder).andReturn().getResponse();
        String response = result.getContentAsString();
        assertEquals(result.getStatus(), HttpStatus.OK.value());

        ObjectMapper objectMapper = new ObjectMapper();
        Movie movie = objectMapper.readValue(response, Movie.class);
        assertEquals(movie.getName(), "Movie 1");
    }

    @Test
    public void updateMovie() throws Exception {
        Movie movieToUpdate = new Movie();
        movieToUpdate.setId((long)1);
        movieToUpdate.setName("Updated Name");
        movieToUpdate.setGenre("Documentary");
        movieToUpdate.setRating(3.4);
        movieToUpdate.setYearReleased(1999);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(movieToUpdate);
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/movie/1").contentType(MediaType.APPLICATION_JSON)
            .content(requestJson);

        MockHttpServletResponse response = this.mockMvc.perform(requestBuilder).andReturn().getResponse();

        String responseString = response.getContentAsString();
        Movie movie = objectMapper.readValue(responseString, Movie.class);

        assertEquals(movie.getId(), movieToUpdate.getId());
    }

    @Test
    public void createMovie() throws Exception {
        Movie moveToCreate = new Movie();
        moveToCreate.setName("New Movie");
        moveToCreate.setGenre("Documentary");
        moveToCreate.setRating(9.9);
        moveToCreate.setYearReleased(2016);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(moveToCreate);
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/movie/").contentType(MediaType.APPLICATION_JSON)
            .content(requestJson);

        MockHttpServletResponse response = this.mockMvc.perform(requestBuilder).andReturn().getResponse();

        String responseString = response.getContentAsString();
        Movie movie = objectMapper.readValue(responseString, Movie.class);

        assertEquals(movie.getName(), moveToCreate.getName());
    }

    @Test
    public void deleteMovie() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/movie/1");

        MockHttpServletResponse response = this.mockMvc.perform(requestBuilder).andReturn().getResponse();

        assertEquals("Movie 1 deleted", response.getContentAsString());
    }

}
