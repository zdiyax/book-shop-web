package zd.model.product;

import lombok.Data;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:04 PM
 */
@Data
public class Book extends Product {
    private String isbn;
    private String author;
    private String domain;
    private String publisher;
}
