package model.order;

import model.product.Product;

import java.util.Date;
import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:04 PM
 */

public class Order {
    private int orderId;
    private Status status;
    private List<Product> books;
    private Date dateOrdered;
    private double orderTotal;
}

