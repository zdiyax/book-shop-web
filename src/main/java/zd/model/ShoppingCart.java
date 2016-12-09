package zd.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:41 PM
 */
@Data
public class ShoppingCart {
    private List<Book> productList;
    private double sum;

    public Order order() {
        Order order = new Order();

        return order;
    }

    public ShoppingCart() {
        productList = new ArrayList<>();
    }

    public void add(Book book) {
        productList.add(book);
    }

    public void remove(Book book) {
        productList.remove(book);
    }

    public void updateSum() {
        for (Book book : productList) {
            sum += book.getPrice();
        }
    }
}
