package bookorder.book.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired BookController bookController;

    @Autowired MockMvc mockMvc;

    @Test
    void 모든_도서_조회() throws Exception {
        mockMvc.perform(get("/books/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items[0].id").isNumber())
                .andExpect(jsonPath("$.items[0].name").isString())
                .andExpect(jsonPath("$.items[0].category").isString())
                .andExpect(jsonPath("$.items[0].origin_price").isNumber())
                .andExpect(jsonPath("$.error").doesNotExist());
    }
}