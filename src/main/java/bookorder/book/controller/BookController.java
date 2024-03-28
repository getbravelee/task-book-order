package bookorder.book.controller;

import bookorder.book.RequestDto;
import bookorder.book.domain.Book;
import bookorder.book.domain.Purchase;
import bookorder.book.domain.Recipt;
import bookorder.book.service.BookService;
import bookorder.book.service.PurchaseService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final PurchaseService purchaseService;

    // TODO: DI 주입 dependency injection
    //생성자 주입, 필드주입 ... 어떤 주입이 있는지 알아볼 것.

    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> result = bookService.findBooks();
        return ResponseEntity.ok(result);
    }

    // TODO: 3layer architecture는 왜나왔을까요?
    //  책임 분리 -> 변경
    //  안되면 강결합 -> 의존성
    @PostMapping("/purchase")
    public ResponseEntity<Recipt> purchase(@RequestBody Purchase purchase) {
        // TODO: 검증
        //  검증을 어떻게 하는지 공부.

        Recipt recipt = purchaseService.purchaseBook(
                purchase.getBookId(),
                purchase.getPayMethod(),
                purchase.getPayAmount()
        );


        return new ResponseEntity<>(recipt, HttpStatus.OK);
    }

    @PostMapping("/mobile/purchase2")
    public ResponseEntity<Recipt> purchase2(@RequestBody Purchase purchase) {

        RequestDto requestDto = new RequestDto();

        Recipt recipt = purchaseService.purchaseBook(
                purchase.getBookId(),
                purchase.getPayMethod(),
                purchase.getPayAmount()
        );
        return new ResponseEntity<>(recipt, HttpStatus.OK);
    }
}
