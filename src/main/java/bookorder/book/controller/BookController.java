package bookorder.book.controller;

import bookorder.book.domain.Book;
import bookorder.book.domain.Purchase;
import bookorder.book.domain.Recipt;
import bookorder.book.service.BookService;
import bookorder.book.service.PurchaseService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;
    private final PurchaseService purchaseService;

    public BookController(BookService bookService, PurchaseService purchaseService) {
        this.bookService = bookService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> result = bookService.findBooks();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Recipt> purchase(@RequestBody Purchase purchase) {
        Recipt recipt = purchaseService.purchaseBook(
                purchase.getBookId(),
                purchase.getPayMethod(),
                purchase.getPayAmount()
        );
        return new ResponseEntity<>(recipt, HttpStatus.OK);
    }
}
