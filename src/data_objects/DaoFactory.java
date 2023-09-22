/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_objects;

import utils.IValidation;
import utils.Validation;

/**
 *
 * @author ASUS
 */
public class DaoFactory implements IDaoFactory{

    @Override
    public IProductDao productDao() {
        return new ProductDao();
    }

    @Override
    public IValidation validator() {
        return new Validation();
    }

    @Override
    public IWarehouseDao warehouseDao() {
        return new WarehouseDao();
    }
}
