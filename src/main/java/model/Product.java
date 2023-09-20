package model;

import javax.xml.crypto.Data;

public class Product {
    private Integer id;
    private String name;
    private Data createDate;
    private Category category;
    private Brand brand;

    public Product(Integer id, String name, Data createDate) {
        this(name, createDate);
        this.id = id;
    }

    public Product(String name, Data createDate) {
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

    public Data getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Data createDate) {
        this.createDate = createDate;
    }
}
