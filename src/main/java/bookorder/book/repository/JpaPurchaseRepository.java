package bookorder.book.repository;

import bookorder.book.domain.Purchase;
import jakarta.persistence.EntityManager;

public class JpaPurchaseRepository implements PurchaseReposiotory {

    private final EntityManager em;

    public JpaPurchaseRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Purchase save(Purchase purchase) {
        em.persist(purchase);
        return purchase;
    }
}
