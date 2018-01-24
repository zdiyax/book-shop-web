package kz.epam.zd.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Book> bookList;
    private BigDecimal sum;

    public ShoppingCart() {
        bookList = new ArrayList<>();
    }

    public Order order() {
        Order order = new Order();
        return order;
    }

    public void add(Book book) {
        bookList.add(book);
    }

    public void remove(Book book) {
        bookList.remove(book);
    }

    public void updateSum() {
        for (Book book : bookList) {
            sum.add(book.getPrice());
        }
    }
}
