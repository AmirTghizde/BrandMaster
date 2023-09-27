package model;

import javax.xml.crypto.Data;

public class Product {
    private Integer id;
    private String name;
    private String createDate;
    private Category category;
    private Brand brand;

    public Product(Integer id, String name, String createDate) {
        this(name, createDate);
        this.id = id;
    }

    public Product(String name, String createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
