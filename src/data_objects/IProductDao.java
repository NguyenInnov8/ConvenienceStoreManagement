/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import business_object.Product;
import java.util.List;


public interface IProductDao {
    List<Product> getAllProducts();
    Product getProduct(String productCode);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    boolean addProduct(Product product);
    List<Product> getExpiredProduct();
    List<Product> getSellingProduct();
    List<Product> getOutOfStockProduct();
    boolean saveProductsToFile();
    boolean loadProductFromFile();
}
