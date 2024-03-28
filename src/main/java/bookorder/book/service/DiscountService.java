package bookorder.book.service;

public class DiscountService {
    // friday discount
    // category discount


    public int fridayDiscount(int price) {
        return price - 1000;
    }

    public int categoryDiscount(){
        return 0;
    }

}
