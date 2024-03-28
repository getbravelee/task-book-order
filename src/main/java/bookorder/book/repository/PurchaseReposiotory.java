package bookorder.book.repository;


import bookorder.book.domain.Purchase;

public interface PurchaseReposiotory {
    Purchase save(Purchase purchase);
}
