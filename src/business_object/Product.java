/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_object;

import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Product {
    private String code;
    private String name;
    private double price;
    private int quantity;
    private TypeOfProduct type;
    private Date manufacturingDate, expiredDate;

    public Product(String code, String name, double price, int quantity, TypeOfProduct type, Date manufacturingDate, Date expiredDate) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.manufacturingDate = manufacturingDate;
        this.expiredDate = expiredDate;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public TypeOfProduct getType() {
        return type;
    }

    public void setType(TypeOfProduct type) {
        this.type = type;
    }
    
    public static Comparator sortAscQuantity = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Product prd1 = (Product) o1;
            Product prd2 = (Product) o2;
            
            return prd1.getQuantity() - prd2.getQuantity();
        }
    };

    @Override
    public String toString() {
        return this.code + ", " + this.name + ", " + this.price + ", " + this.quantity + ", " + this.manufacturingDate + ", " + this.expiredDate;
    }
}
