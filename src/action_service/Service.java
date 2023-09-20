/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import business_object.Product;
import data_objects.DaoFactory;
import data_objects.IDaoFactory;
import data_objects.IProductDao;
import java.util.List;


public class Service implements IService{
    static final IDaoFactory factory = new DaoFactory();
    static final IProductDao productDao = factory.productDao();

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Product getProduct(String productCode) {
        return productDao.getProduct(productCode);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

    @Override
    public void addProduct() {
        productDao.addProduct();
    }
}
