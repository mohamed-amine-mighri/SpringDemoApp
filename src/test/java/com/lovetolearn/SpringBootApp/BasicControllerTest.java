package com.lovetolearn.SpringBootApp;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lovetolearn.SpringBootApp.controller.BasicController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BasicController.class)
public class BasicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetData() throws Exception {
        mockMvc.perform(get("/get"))
                .andExpect(status().isOk())
                .andExpect(content().string(is("Welcome To My Test App")));
    }
}