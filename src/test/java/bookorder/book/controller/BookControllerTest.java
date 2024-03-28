package bookorder.book.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import bookorder.book.domain.Book;
import bookorder.book.domain.PayDetail;
import bookorder.book.domain.Purchase;
import bookorder.book.domain.Recipt;
import bookorder.book.service.BookService;
import bookorder.book.service.PurchaseService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookControllerTest {

    @Autowired BookController bookController;
    @Autowired BookService bookService;
    @Autowired PurchaseService purchaseService;

    @Autowired MockMvc mockMvc;

    @Test
    void 도서구매() throws Exception {

        PurchaseService purchaseServiceMock = mock(PurchaseService.class);

        Book book = new Book();
        book.setId(1L);
        book.setName("Spring");
        book.setCategory("dev");
        book.setOriginPrice(20000);

        Recipt recipt = new Recipt();
        recipt.setPayMethod("CASH");
        recipt.setPayAmount(30000);
        recipt.setBook(book);
        recipt.setChangeAmount(recipt.getPayAmount() - book.getOriginPrice());
        when(purchaseServiceMock.purchaseBook(anyLong(), anyString(), anyInt())).thenReturn(recipt);

        BookController bookController = new BookController(null, purchaseServiceMock);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        mockMvc.perform(post("/api/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"bookId\": 1, \"payMethod\": \"CASH\", \"payAmount\": 30000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payMethod").value("CASH"))
                .andExpect(jsonPath("$.payAmount").value(30000))
                .andExpect(jsonPath("$.book").exists())
                .andExpect(jsonPath("$.changeAmount").exists())
                .andDo(print());
    }
}