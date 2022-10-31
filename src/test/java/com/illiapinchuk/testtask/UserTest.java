package com.illiapinchuk.testtask;

import com.illiapinchuk.testtask.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserTest{

    private static final String TEST_GET_USER_STARTED = "Test started - getUserById()";
    private static final String TEST_GET_USER_FINISHED = "Test finished - getUserById()";
    private static final String TEST_GET_USER_URL = "/api/users/1";

    private static final String TEST_GET_USER_EXCEPTION_STARTED = "Test started - getUserByIdAndExpectUserNotFoundException()";
    private static final String TEST_GET_USER_EXCEPTION_FINISHED = "Test finished - getUserByIdAndExpectUserNotFoundException()";
    private static final String TEST_GET_USER_EXCEPTION_URL = "/api/users/4";

    @Autowired
    private MockMvc mvc;

    @Test
    public void getUserById() throws Exception {
        log.info(TEST_GET_USER_STARTED);
        mvc.perform(MockMvcRequestBuilders.get(TEST_GET_USER_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("student1 first name"))
                .andExpect(jsonPath("$.lastName").value("student1 last name"))
                .andExpect(jsonPath("$.age").value(19));
        log.info(TEST_GET_USER_FINISHED);
    }

    @Test
    public void getUserByIdAndExpectUserNotFoundException() throws Exception {
        log.info(TEST_GET_USER_EXCEPTION_STARTED);

        mvc.perform(MockMvcRequestBuilders.get(TEST_GET_USER_EXCEPTION_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException))
                .andExpect(result -> assertEquals("Could not find user with id 4", result.getResolvedException().getMessage()));
        log.info(TEST_GET_USER_EXCEPTION_FINISHED);
    }
}
