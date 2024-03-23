package bookorder.book.repository;

import bookorder.book.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    // 도서 저장
    Book save(Book book);

    // 도서 아이디로 도서 조회
    Optional<Book> findById(Long id);

    // 도서 이름으로 도서 조회
    Optional<Book> findByName(String name);

    // 모든 도서 조회
    List<Book> findAll();

}
