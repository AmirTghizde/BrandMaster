package model;

public class Brand {
    private Integer id;
    private String name;
    private String website;
    private String description;
    private Shareholder[] shareholders;
    private Product[] products;

    public Brand(Integer id, String name, String website, String description) {
        this(name, website, description);
        this.id = id;
    }

    public Brand(String name, String website, String description) {
        this.name = name;
        this.website = website;
        this.description = description;
    }

    public Brand() {
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
