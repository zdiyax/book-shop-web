package model;

import lombok.Data;
import model.order.Order;
import model.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:41 PM
 */
@Data
public class ShoppingCart {
    private List<Product> productList;
    private double sum;

    public Order order() {
        Order order = new Order();

        return order;
    }

    public ShoppingCart() {
        productList = new ArrayList<>();
    }

    public void add(Product product) {
        productList.add(product);
    }

    public void remove(Product product) {
        productList.remove(product);
    }

    public void updateSum() {
        for (Product product : productList) {
            sum += product.getPrice();
        }
    }
}
