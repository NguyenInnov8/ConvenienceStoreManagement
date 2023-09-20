/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import business_object.Product;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IService {
    // Product repo
    List<Product> getAllProducts();
    Product getProduct(String productCode);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    void addProduct();
    
    
}
