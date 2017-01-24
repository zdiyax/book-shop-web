package zd.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<Book> productList;
    private BigDecimal sum;

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
            sum.add(book.getPrice());
        }
    }
}
