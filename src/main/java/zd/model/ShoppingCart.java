package zd.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<Book> bookList;
    private BigDecimal sum;

    public Order order() {
        Order order = new Order();
        return order;
    }

    public ShoppingCart() {
        bookList = new ArrayList<>();
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
