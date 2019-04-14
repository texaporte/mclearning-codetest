package com.mondido.mclearning.demo;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mondido.mclearning.demo.controllers.HomeController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

	@Test
	public void currentTimeIsCorrect() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/timeofday");

        MvcResult result = this.mockMvc.perform(reqBuilder).andReturn();

        String expected = sdf.format(new Date());
        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);
    }
    
    @Test
    public void homeRequest() throws Exception{
        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/");
        MvcResult result = this.mockMvc.perform(reqBuilder).andReturn();

        String expected = "Hello World!";
        String actual = result.getResponse().getContentAsString();

        assertEquals(expected, actual);
    }

}
