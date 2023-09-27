package model;

public class Shareholder {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String nationalCode;
     private Brand[] brands;

    public Shareholder(Integer id, String name, String phoneNumber, String nationalCode, Brand[] brands) {
        this(name, phoneNumber, nationalCode, brands);
        this.id = id;
    }

    public Shareholder(String name, String phoneNumber, String nationalCode, Brand[] brands) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
        this.brands = brands;
    }

    public Shareholder() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Brand[] getBrands() {
        return brands;
    }

    public void setBrands(Brand[] brands) {
        this.brands = brands;
    }
}
