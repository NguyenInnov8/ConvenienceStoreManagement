/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import business_object.Product;
import business_object.Warehouse;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IValidation {
    String inputString(String msg, Status status);
    
    String checkProductCodeExist(String msg ,List<Product> listProduct, Status status);

    String checkReceiptCodeExist(String msg, List<Warehouse> listWareHouse, Status status);

    String checkBeforeDate(String msg, Status status);

    String checkAfterDate(String msg, String pd, Status status);

    String checkType(String msg,Status status);
    
    String checkSize(String msg,String status);
    
    int checkInt(String msg, int min, int max);

    double checkDouble(String msg, double min, double max);

    boolean checkYesOrNo(String msg);

    boolean checkUpdateOrDelete(String msg);

    boolean checkFileOrCollection(String msg);

}
