/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import business_object.Product;
import business_object.TypeOfProduct;
import business_object.Warehouse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Validation implements IValidation {

    private Scanner sc = new Scanner(System.in);

    @Override
    public String inputString(String msg, Status status) {

        // vong lap su dung de nguoi dung nhap den khi dung 
        while (true) {
            System.out.println(msg);
            // allow user input a string 
            String input_raw = sc.nextLine();
            if (status == Status.UPDATE && input_raw.isEmpty()) {
                return input_raw;
            }
            if (input_raw == null || input_raw.length() == 0) {
                // error
                System.err.println("Must input a string not empty !!!");
                System.out.println("Please enter again!");
                continue;
            }
            return input_raw;
        }

    }

    @Override
    public String checkProductCodeExist(String msg ,List<Product> listProduct, Status status) {
        int flag;
        while(true) {
        flag = 0;
        String prdID = inputString(msg, status);
        
        for (Product product : listProduct) {
            if(product.getCode().equalsIgnoreCase(prdID)) {
                System.err.println("This Product Code has existed before.\n"
                        + "Please input another one.");
                flag = 1;
            }
        }
        
        if(flag == 1) {
            continue;
        }
        
        return prdID;
        }
    }

    @Override
    public String checkReceiptCodeExist(String msg, List<Warehouse> listWareHouse, Status status) {
        int flag = 0;
        while(true) {
        flag = 0;
        String receiptID = inputString(msg, status);
        
        for (Warehouse warehouse : listWareHouse) {
            if(warehouse.getCode() ==  Integer.parseInt(receiptID.trim())){
                System.err.println("This Product Code has existed before.\n"
                        + "Please input another one.");
                flag = 1;
            }
        }
        
        if(flag == 1) {
            continue;
        }
        return receiptID;
        }
    }

    @Override
    public String checkBeforeDate(String msg, Status status) {
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        while (true) {
            String dateStr = inputString(msg, status);
            if (status == Status.UPDATE && dateStr.isEmpty() && dateStr.contains(" ")) {
                return dateStr;
            }
            try {
                sdf.parse(dateStr);
            } catch (ParseException ex) {
                System.err.println("Please enter correct format with \"dd/MM/yyyy\" format");
                continue;
            }
            return dateStr;
        }
    }

    @Override
    public String checkAfterDate(String msg, String pd, Status status) {
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        while (true) {

            String initDate = checkBeforeDate(msg, status);
            try {
                Date d1 = sdf.parse(initDate);

            } catch (ParseException ex) {
                Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public String checkType(String msg, Status status) {
        
        while (true) {
            String type = inputString(msg, status);

            if (type.isEmpty() && status == Status.UPDATE) {
                return type;
            }

            TypeOfProduct typeOfProduct;
            try {
                typeOfProduct = TypeOfProduct.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid product type. There are only 2 types of Product which are \"DAILY\" and \"LONG\".");
                continue;
            }

            if (typeOfProduct != TypeOfProduct.DAILY && typeOfProduct != TypeOfProduct.LONG) {
                System.err.println("Invalid product type. There are only 2 types of Product which are \"DAILY\" and \"LONG\".");
                continue;
            }

            return type;
        }
    }

    @Override
    public String checkSize(String msg, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int checkInt(String msg, int min, int max) {
        while (true) {
            String rawInput = inputString(msg, Status.NONE);

            if (rawInput.isEmpty() || rawInput.contains(" ")) {
                return -1;
            }
            int returnNumber;
            try {
                returnNumber = Integer.parseInt(rawInput);
                if (returnNumber < min || returnNumber > max) {
                    System.err.println("Must input a number from " + min + " to " + max);
                    continue;
                }
            } catch (NumberFormatException ex) {
                System.err.println("Must enter a number");
                continue;
            }
            return returnNumber;
        }
    }

    @Override
    public double checkDouble(String msg, double min, double max) {
        while (true) {
            String rawInput = inputString(msg, Status.NONE);

            if (rawInput.isEmpty() || rawInput.contains(" ")) {
                return -1;
            }
            double returnNumber;
            try {
                returnNumber = Double.parseDouble(rawInput);
                if (returnNumber < min || returnNumber > max) {
                    System.err.println("Must input a number from " + min + " to " + max);
                    continue;
                }
            } catch (NumberFormatException ex) {
                System.err.println("Must enter a number");
                continue;
            }
            return returnNumber;
        }
    }

    @Override
    public boolean checkYesOrNo(String msg) {
        while (true) {
            String input = inputString(msg, Status.NONE);
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must input Y or N to select option");
                continue;
            }
        }
    }

    @Override
    public boolean checkUpdateOrDelete(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkFileOrCollection(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
