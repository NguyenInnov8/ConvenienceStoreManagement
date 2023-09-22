/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import business_object.Product;
import business_object.TypeOfProduct;
import business_object.TypeOfReceipt;
import business_object.Warehouse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
    public String checkProductCodeExist(String msg, List<Product> listProduct, Status status) {
        int flag;
        while (true) {
            flag = 0;
            String prdID = inputString(msg, status);

            for (Product product : listProduct) {
                if (product.getCode().equalsIgnoreCase(prdID)) {
                    System.err.println("This Product Code has existed before.\n"
                            + "Please input another one.");
                    flag = 1;
                }
            }

            if (flag == 1) {
                continue;
            }

            return prdID;
        }
    }

    @Override
    public String checkReceiptCodeExist(String msg, List<Warehouse> listWareHouse, Status status) {
        int flag = 0;
        while (true) {
            flag = 0;
            String receiptID = inputString(msg, status);

            for (Warehouse warehouse : listWareHouse) {
                if (warehouse.getCode() == Integer.parseInt(receiptID.trim())) {
                    System.err.println("This Receipt Code has existed before.\n"
                            + "Please input another one.");
                    flag = 1;
                }
            }

            if (flag == 1) {
                continue;
            }
            return receiptID;
        }
    }

    @Override
    public Date checkBeforeDate(String msg, Status status) {
        String pattern = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        while (true) {
            String dateStr = inputString(msg, status);
            if (status == Status.UPDATE && dateStr.isEmpty()) {
                return null;
            }
            try {
                Date date = sdf.parse(dateStr);
                return date;
            } catch (ParseException e) {
                System.err.println("Incorrect date! Please enter again.");
            }
        }

    }

    @Override
    public Date checkAfterDate(String msg, Date productionDate, Status status) {
        while (true) {
            Date expiredDate = checkBeforeDate(msg, status);
            if (expiredDate.before(productionDate)) {
                System.err.println("Expiration date must after production date! Please enter again.");
                continue;
            }
            return expiredDate;
        }
    }

    @Override
    public TypeOfProduct checkType(String msg, Status status) {
            while(true){
            String type = inputString(msg, status);
            if(status == Status.UPDATE && type.isEmpty()){
                return null;
            }
            if(type.equalsIgnoreCase("DAILY")){
                return TypeOfProduct.DAILY;
            } else if(type.equalsIgnoreCase("LONG")){
                return TypeOfProduct.LONG;
            } else{
                System.err.println("Must input 1 in 2 type product is 'Daily' or 'Long'! Please input again.");
            }
        }
    }

    @Override
    public int checkInt(String msg, int min, int max) {
        while (true) {
            String rawInput = inputString(msg, Status.UPDATE);

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
            String rawInput = inputString(msg, Status.UPDATE);

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
    public TypeOfReceipt checkTradeType(String msg, Status status) {
        while (true) {
            String type = inputString(msg, status);
            if (status == Status.UPDATE && type.isEmpty()) {
                return null;
            }
            if (type.equalsIgnoreCase("IMPORT")) {
                return TypeOfReceipt.IMPORT;
            } else if (type.equalsIgnoreCase("EXPORT")) {
                return TypeOfReceipt.EXPORT;
            } else {
                System.err.println("Must input 1 in 2 trade type is 'Import' or 'Export'! Please input again.");
            }
        }

    }
}
