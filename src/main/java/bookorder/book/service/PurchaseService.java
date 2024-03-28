package bookorder.book.service;

import bookorder.book.domain.*;
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

    // TODO:
    //  결합도 낮으면 좋은 것
    //  응집도 높으면 좋은 것
    // ResponseDto
    public Recipt purchaseBook(Long bookId, String payMethod, Money payAmount) {
        // 책 정보 가져오기
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책 ID입니다: " + bookId));

        // 결제 금액 확인
        if (!book.isBuyable(payAmount)) {
            throw new IllegalArgumentException("구매할 수 없는 금액입니다.");
        }
//        notEnoughMoney(payAmount, book);

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

    // TODO: 묻지말고 시켜라 -> 책임을 할당하고 그 객체에게 시켜라
    //  안다는 것 -> 디펜던시  == 의존 == 결합 -> 변경에 영향받는다
    //  함수는 테스트 할 수 없다.
    private void notEnoughMoney(int payAmount, Book book) {
        if (payAmount < book.getOriginPrice()) {
            throw new IllegalArgumentException("돈이 부족합니다.");
        }
    }
}
