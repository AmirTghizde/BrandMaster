package model;

public class Shareholder {
    private Integer id;
    private String name;
    private int phoneNumber;
    private int nationalCode;
     private Brand[] brands;

    public Shareholder(Integer id, String name, int phoneNumber, int nationalCode, Brand[] brands) {
        this(name, phoneNumber, nationalCode, brands);
        this.id = id;
    }

    public Shareholder(String name, int phoneNumber, int nationalCode, Brand[] brands) {
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(int nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Brand[] getBrands() {
        return brands;
    }

    public void setBrands(Brand[] brands) {
        this.brands = brands;
    }
}
