package zd.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.exception.ModelException;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Order extends Model {
    private final Logger log = LoggerFactory.getLogger(Order.class);
    private OrderStatus status;
    private List<Book> books;
    private Date dateOrdered;
    private BigDecimal orderTotal;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public void setOrderTotal(double orderTotal) throws ModelException {
        if (orderTotal <= 0) {
            log.debug("Non-positive orderTotal entered");
            throw new ModelException("Non-positive orderTotal entered");
        }
    }
}

