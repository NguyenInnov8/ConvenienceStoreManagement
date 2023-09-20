/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import business_object.Warehouse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class WarehouseDao implements IWarehouseDao{
    List<Warehouse> receiptList;

    public WarehouseDao() {
        this.receiptList = new ArrayList<>();
    }
    
    @Override
    public void createReceipt(Warehouse receipt) {
    }
    
}
