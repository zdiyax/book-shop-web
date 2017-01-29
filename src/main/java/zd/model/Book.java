package zd.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book extends Model {
    private String isbn;
    private String language;
    private String title;
    private String author;
    private String domain;
    private String publisher;
    private String description;
    private BigDecimal price;
}
