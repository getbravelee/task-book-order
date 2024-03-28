package bookorder.book.service;

import bookorder.book.domain.Book;
import bookorder.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * 도서 정보 추가
     */
    public Long addBook(Book book) {
        // 중복 책 x
        validateDuplicateBook(book);
        bookRepository.save(book);
        return book.getId();
    }

    /**
     * 전체 도서 조회
     */
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    /**
     * 도서 찾기
     */
    public Optional<Book> findOne(Long bookId) {
        return bookRepository.findById(bookId);
    }


    private void validateDuplicateBook(Book book) {
        bookRepository.findByName(book.getName())
            .ifPresent(b -> {
                throw new IllegalStateException("이미 존재하는 책입니다.");
            });
    }
}
