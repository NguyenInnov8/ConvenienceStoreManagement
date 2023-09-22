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

    public boolean setName(String name) {
        if(name.isEmpty()) {
            return false;
        }
        this.name = name;
        return true;
    }

    public double getPrice() {
        return price;
    }

    public boolean setPrice(double price) {
        if(price < 0) {
            return false;
        }
        this.price = price;
        return true;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean setQuantity(int quantity) {
        if(quantity < 0) {
            return false;
        }
        this.quantity = quantity;
        return true;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public boolean setManufacturingDate(Date manufacturingDate) {
        if(manufacturingDate == null) {
            return false;
        }
        this.manufacturingDate = manufacturingDate;
        return true;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public boolean setExpiredDate(Date expiredDate) {
        if(expiredDate == null) {
            return false;
        }
        
        this.expiredDate = expiredDate;
        return true;
    }

    public TypeOfProduct getType() {
        return type;
    }

    public boolean setType(TypeOfProduct type) {
       if(type == null) {
           return false;
       }
       this.type = type;
       return true;
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
