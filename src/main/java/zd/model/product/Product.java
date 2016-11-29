package zd.model.product;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.model.Model;
import zd.model.ModelException;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:03 PM
 */
/*
TODO: Connect Lombok, @Entity?
 */
@Data
public abstract class Product extends Model{
    private final Logger log = LoggerFactory.getLogger(Product.class);

    private String language;
    private String title;
    private String description;
    private double price;

    public void setPrice(double price) throws ModelException {
        if (price <= 0) {
            log.debug("Non-positive price entered");
            throw new ModelException("Non-positive price entered");
        }
        this.price = price;
    }

}
