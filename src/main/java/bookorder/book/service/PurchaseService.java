package bookorder.book.service;

import bookorder.book.domain.Book;
import bookorder.book.domain.PayDetail;
import bookorder.book.domain.Purchase;
import bookorder.book.domain.Recipt;
import bookorder.book.repository.BookRepository;
import bookorder.book.repository.PurchaseReposiotory;

public class PurchaseService {

    private final PurchaseReposiotory purchaseRepository;
    private final BookRepository bookRepository;

    public PurchaseService(PurchaseReposiotory purchaseReposiotory, BookRepository bookRepository) {
        this.purchaseRepository = purchaseReposiotory;
        this.bookRepository = bookRepository;
    }

    /**
     * 도서 구매
     */
    public Recipt purchaseBook(Long bookId, String payMethod, int payAmount) {
        // 책 정보 가져오기
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책 ID입니다: " + bookId));

        // 결제 금액 확인
        notEnoughMoney(payAmount, book);

        // 구매 정보 저장
        Purchase purchase = new Purchase();
        purchase.setBookId(bookId);
        purchase.setPayMethod(payMethod);
        purchase.setPayAmount(payAmount);
        purchaseRepository.save(purchase);

        int changeAmount = payAmount - book.getOriginPrice();

        // 결제 완료 후 영수증 생성
        Recipt recipt = new Recipt();
        recipt.setPayMethod(payMethod);
        recipt.setPayAmount(payAmount);
        recipt.setBook(book);
        recipt.setChangeAmount(changeAmount);

        return recipt;
    }

    private static void notEnoughMoney(int payAmount, Book book) {
        if (payAmount < book.getOriginPrice()) {
            throw new IllegalArgumentException("돈이 부족합니다.");
        }
    }
}
