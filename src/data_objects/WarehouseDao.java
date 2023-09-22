/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import business_object.Product;
import business_object.Warehouse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class WarehouseDao implements IWarehouseDao{
    private final String WAREHOUSE_FILE_PATH = "src\\Warehouse.dat";
    private List<Warehouse> receiptList;

    public WarehouseDao() {
        this.receiptList = new ArrayList<>();
    }

    @Override
    public boolean addReceipt(Warehouse receipt) {
        return receiptList.add(receipt);
    }

    @Override
    public int getSize() {
        return receiptList.size();
    }

    @Override
    public List<Warehouse> getReceiptList(String productCode) {
        List<Warehouse> tempList = new ArrayList<>();
        for (Warehouse warehouse : receiptList) {
            for (Product product: warehouse.getItems()) {
                if(product.getCode().equalsIgnoreCase(productCode)) {
                    tempList.add(warehouse);
                    break;
                }
            }
        }
        return tempList;
    }

    @Override
    public boolean isProductExist(String productCode) {
        for(Warehouse warehouse: receiptList){
            for(Product product: warehouse.getItems()){
                if(product.getCode().equalsIgnoreCase(productCode)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean loadFromFile() {
        File file = new File(WAREHOUSE_FILE_PATH);
        try{
            FileInputStream readData = new FileInputStream(file);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            boolean more = true;
            while(more){
                Warehouse warehouse = (Warehouse) readStream.readObject();
                if(warehouse != null){
                    receiptList.add(warehouse);
                } else{
                    more = false;
                }
            }
            readStream.close();
            readData.close();
        }catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;    
    }

    @Override
    public boolean saveToFile() {
        File file = new File(WAREHOUSE_FILE_PATH);
        try{
            FileOutputStream writeData = new FileOutputStream(file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            for(Warehouse warehouse: receiptList){
                writeStream.writeObject(warehouse);
            }
            writeStream.flush();
            writeStream.close();
            writeData.close();
        }catch (IOException e) {
            return false;
        }
        return true;
        
    }
    
   
    
}
