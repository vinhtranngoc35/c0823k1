package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Product {
    private int id;
    private String name;

    private BigDecimal price;

    private String description;

    private Category category;

    private int categoryId;

    private Date importDate;


    public Product(int id, String name, BigDecimal price, String description, int categoryId, Date importDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.importDate = importDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + description + "," + categoryId +","+ importDate;

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}