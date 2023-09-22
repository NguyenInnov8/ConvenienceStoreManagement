/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import business_object.Warehouse;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IWarehouseDao {
    boolean addReceipt(Warehouse receipt);
    int getSize();
    List<Warehouse> getReceiptList(String productCode);
    boolean isProductExist(String productCode);
    boolean loadFromFile();
    boolean saveToFile();
}
