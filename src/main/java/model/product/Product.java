package model.product;

/**
 * Zhannur Diyas
 * 11/25/2016 | 4:03 PM
 */
/*
TODO: Connect Lombok, @Entity?
 */
public abstract class Product {
    private String language;
    private String title;
    private String description;
    private double price;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
