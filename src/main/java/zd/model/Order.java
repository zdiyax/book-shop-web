package zd.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.exception.ModelException;
import zd.model.user.ShippingAddress;

import java.sql.Date;
import java.util.List;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:04 PM
 */
@Data
public class Order {
    private final Logger log = LoggerFactory.getLogger(Order.class);

    private OrderStatus status;
    private List<Book> books;
    private Date dateOrdered;
    private ShippingAddress shippingAddress;
    private double orderTotal;

    public void setOrderTotal(double orderTotal) throws ModelException {
        if (orderTotal <= 0) {
            log.debug("Non-positive orderTotal entered");
            throw new ModelException("Non-positive orderTotal entered");
        }
    }

}

