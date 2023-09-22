/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Warehouse {
    private int code;
    private Date timeStamp;
    private List<Product> items;
    private TypeOfReceipt type;

    public Warehouse(int code, Date timeStamp, List<Product> items , TypeOfReceipt type) {
        this.code = code;
        this.timeStamp = timeStamp;
        this.items = items;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public TypeOfReceipt getType() {
        return type;
    }

    public void setType(TypeOfReceipt type) {
        this.type = type;
    }
}
