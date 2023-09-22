/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import business_object.Product;
import business_object.TypeOfReceipt;

/**
 *
 * @author ASUS
 */
public interface IService {
    // Product repo
    void showAllProducts();
    Product getProduct(String productCode);
    void updateProduct();
    void deleteProduct();
    void addProduct();
    // Warehouse Repository
    void addReceipt(TypeOfReceipt type);
    // Report Repository
    void showExpiredProducts();
    void showSellingProducts();
    void showOutOfStockProducts();
    void showReceipt();
    // File handling repository
    boolean loadProductsFromFile();
    boolean saveProductsToFile();
    boolean loadWarehouseFromFile();
    boolean saveWarehouseToFile();
    
}
