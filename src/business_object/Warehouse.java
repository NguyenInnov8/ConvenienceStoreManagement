/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_object;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Warehouse {
    private int code;
    private long timeStamp;
    private List<Product> items;
    private TypeOfReceipt type;

    public Warehouse(int code, long timeStamp, TypeOfReceipt type) {
        this.code = code;
        this.timeStamp = timeStamp;
        this.items = new ArrayList<>();
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
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
