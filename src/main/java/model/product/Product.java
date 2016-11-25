package model.product;

import lombok.Data;
import model.ModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:03 PM
 */
/*
TODO: Connect Lombok, @Entity?
 */
@Data
public abstract class Product {
    private final Logger log = LoggerFactory.getLogger(Product.class);

    private ProductLanguage language;
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
