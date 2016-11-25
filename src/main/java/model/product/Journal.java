package model.product;

import lombok.Data;

import java.util.Date;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:04 PM
 */

@Data
public class Journal extends Product {
    private Date datePublished;
    private String edition;
}
