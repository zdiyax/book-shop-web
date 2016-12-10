package zd.model;

import lombok.Data;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:04 PM
 */
@Data
public class Book extends Model {
    private String isbn;
    private String language;
    private String title;
    private String author;
    private String domain;
    private String publisher;
    private String description;
    private double price;
}
