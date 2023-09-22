/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import business_object.Product;
import business_object.TypeOfProduct;
import business_object.TypeOfReceipt;
import business_object.Warehouse;
import data_objects.DaoFactory;
import data_objects.IDaoFactory;
import data_objects.IProductDao;
import data_objects.IWarehouseDao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import utils.IValidation;
import utils.Status;
import utils.Validation;

public class Service implements IService {

    static final IDaoFactory factory = new DaoFactory();
    static final IProductDao productDao = factory.productDao();
    static final IValidation validator = factory.validator();
    static final IWarehouseDao warehouseDao = factory.warehouseDao();

    @Override
    public void showAllProducts() {
        System.out.println("----------List Of Product-----------");
        for (Product product : productDao.getAllProducts()) {
            System.out.println(product);
        }
    }

    @Override
    public Product getProduct(String productCode) {
        return productDao.getProduct(productCode);
    }

    @Override
    public void updateProduct() {
        SimpleDateFormat dtobj = new SimpleDateFormat("dd/MM/yyyy");
        String productCode = "";
        while (true) {
            productCode = validator.inputString("Input product code:", Status.NONE);
            if (productDao.getProduct(productCode) != null) {
                break;
            } else {
                System.err.println("Product does not exist! Please enter again.");
            }
        }
        Product updatedProduct = productDao.getProduct(productCode);

        String productName = validator.inputString("Input product name: ", Status.UPDATE);
        if (updatedProduct.setName(productName)) {
            System.out.println("Changed product name to: " + productName);
        }

        Double price = validator.checkDouble("Input product price: ", 0, Double.MAX_VALUE);
        if (updatedProduct.setPrice(price)) {
            System.out.println("Changed product price to: " + price);
        }

        int quantity = validator.checkInt("Input product quantity", 0, Integer.MAX_VALUE);
        if (updatedProduct.setQuantity(quantity)) {
            System.out.println("Changed product quantity to: " + quantity);
        }

        TypeOfProduct type = validator.checkType("Input product type (Long & Daily): ", Status.UPDATE);
        if (updatedProduct.setType(type)) {
            System.out.println("Changed product type to: " + type);
        }

        Date manuDate = validator.checkBeforeDate("Input Manufacturing Date (dd/MM/yyyy): ", Status.UPDATE);
        if (updatedProduct.setManufacturingDate(manuDate)) {
            System.out.println("Changed Manufacturing date to: " + dtobj.format(manuDate));
        }

        Date expiredDate = validator.checkAfterDate("Input Expired Date (dd/MM/yyyy): ", manuDate, Status.UPDATE);
        if (updatedProduct.setExpiredDate(expiredDate)) {
            System.out.println("Changed Expired date to: " + dtobj.format(expiredDate));
        }
    }

    @Override
    public void deleteProduct() {
        String productCode = "";
        while (true) {
            productCode = validator.inputString("Input product code:", Status.NONE);
            if (productDao.getProduct(productCode) != null) {
                break;
            } else {
                System.err.println("Product does not exist! Please enter again.");
            }
        }
        
        if(validator.checkYesOrNo("Do you want to delete this product? Y/N")) {
            Product deletedProduct = productDao.getProduct(productCode);
            if(!warehouseDao.isProductExist(productCode)) {
                System.out.println("Successfully Delete Product with Code " + deletedProduct.getCode());
                productDao.deleteProduct(deletedProduct);
            } else {
                System.out.println("Failed to delete this Product");
                System.out.println("You can only remove the product from the store's list when the\n" +
"import / export information for this product has not been generated");
            }
        }
    }

    @Override
    public void addProduct() {
        do {
            SimpleDateFormat dtobj = new SimpleDateFormat("dd/MM/yyyy");
            String productCode = "";
            while (true) {
                productCode = validator.inputString("Input product code:", Status.NONE);
                if (productDao.getProduct(productCode) == null) {
                    break;
                } else {
                    System.err.println("Product code has existed before. \nPlease Input another product code");
                }
            }
            String productName = validator.inputString("Input product name: ", Status.UPDATE);
            double price = validator.checkDouble("Input product price: ", 1.0, Double.MAX_VALUE);
            int quantity = validator.checkInt("Enter product quantity", 1, Integer.MAX_VALUE);
            TypeOfProduct type = validator.checkType("Enter product Type (DAILY & LONG):", Status.UPDATE);
            Date manuDate = validator.checkBeforeDate("Input Manufacturing Date (dd/MM/yyyy): ", Status.UPDATE);
            Date expiredDate = validator.checkAfterDate("Input Expired Date (dd/MM/yyyy): ", manuDate, Status.UPDATE);
            Product addedProduct = new Product(productCode, productName, price, quantity, type, manuDate, expiredDate);
            if (productDao.addProduct(addedProduct)) {
                System.out.println("Product: ID " + addedProduct.getCode() + " has been added to the Database;");
            } else {
                System.out.println("Fail adding product to the database");
            }
        } while (validator.checkYesOrNo("Do you want to continue adding product? Press \"Y\" or \"N\""));
    }

    // Warehouse impl
    @Override
    public void addReceipt(TypeOfReceipt type) {
        List<Product> items = new ArrayList<>();
        do {
            Product importProduct;
            String productCode = validator.inputString("Input product code:", Status.NONE);
            if (productDao.getProduct(productCode) == null) {

                if (type == TypeOfReceipt.EXPORT) {
                    System.err.println("Product code does not exist! Please input again");
                    continue;
                } else {
                    String productName = validator.inputString("Input product name: ", Status.UPDATE);
                    double price = validator.checkDouble("Input product price: ", 1.0, Double.MAX_VALUE);
                    int quantity = validator.checkInt("Enter product quantity", 1, Integer.MAX_VALUE);
                    TypeOfProduct typeofProduct = validator.checkType("Enter product Type (DAILY & LONG):", Status.UPDATE);
                    Date manuDate = validator.checkBeforeDate("Input Manufacturing Date (dd/MM/yyyy): ", Status.UPDATE);
                    Date expiredDate = validator.checkAfterDate("Input Expired Date (dd/MM/yyyy): ", manuDate, Status.UPDATE);
                    importProduct = new Product(productCode, productName, price, quantity, typeofProduct, manuDate, expiredDate);

                    if (productDao.addProduct(importProduct)) {
                        System.out.println("Successfully added product");
                    }
                }
            } else {
                Product addedProduct = productDao.getProduct(productCode);
                int newQuantity = validator.checkInt("Input quantity", 0, Integer.MAX_VALUE);;
                if (type == TypeOfReceipt.EXPORT) {
                    while (true) {
                        if ((addedProduct.getQuantity() - newQuantity) >= 0) {
                            break;
                        } else {
                            System.err.println("Not enough quantity to export! Please input again");
                        }
                    }
                }

                String productName = addedProduct.getName();
                double price = addedProduct.getPrice();
                TypeOfProduct productType = addedProduct.getType();
                Date manufacturingDate = addedProduct.getManufacturingDate();
                Date expirationDate = addedProduct.getExpiredDate();
                importProduct = new Product(productCode, productName, price, newQuantity, productType, manufacturingDate, expirationDate);

                if (type == TypeOfReceipt.EXPORT) {
                    addedProduct.setQuantity(addedProduct.getQuantity() - newQuantity);
                } else {
                    addedProduct.setQuantity(addedProduct.getQuantity() + newQuantity);
                }
            }
            items.add(importProduct);
        } while (validator.checkYesOrNo("Continue to add product? y/n?"));
        int code = warehouseDao.getSize() + 1000001;
        Date now = new Date();
        Warehouse warehouse = new Warehouse(code, now, items, type);
        if (warehouseDao.addReceipt(warehouse)) {
            System.out.println("Successfully add " + type + " receipt with information");
            System.out.println(warehouse);
        }
    }

    @Override
    public void showExpiredProducts() {
        System.out.println("------Expired Products List------");
        for (Product product : productDao.getExpiredProduct()) {
            System.out.println(product);
        }
    }

    @Override
    public void showSellingProducts() {
        System.out.println("-----Selling Products List------");
        for (Product product : productDao.getSellingProduct()) {
            System.out.println(product);
        }
    }

    @Override
    public void showOutOfStockProducts() {
        System.out.println("-----Out Of Stock Products List-----");
        List<Product> list = productDao.getOutOfStockProduct();
        Collections.sort(list, Product.sortAscQuantity);
        for (Product product : list) {
            System.out.println(product);
        }
    }

    @Override
    public void showReceipt() {
        String productCode = "";
        while (true) {
            productCode = validator.inputString("Input product code:", Status.NONE);
            if (productDao.getProduct(productCode) != null) {
                break;
            } else {
                System.err.println("Product does not exist! Please enter again.");
            }
        }
        for (Warehouse warehouse : warehouseDao.getReceiptList(productCode)) {
            String listItems = "";
            for (Product product : warehouse.getItems()) {
                if (product.getCode().equalsIgnoreCase(productCode)) {
                    listItems += product.toString() + "\n";
                }
            }
            System.out.println(warehouse.toString() + "\n------List Of Products------\n" + listItems);
        }
    }

    @Override
    public boolean loadProductsFromFile() {
        return productDao.loadProductFromFile();
    }

    @Override
    public boolean saveProductsToFile() {
        System.out.println("...Saving the list product to file...");
        return productDao.saveProductsToFile();
    }

    @Override
    public boolean loadWarehouseFromFile() {
        return warehouseDao.loadFromFile();
    }

    @Override
    public boolean saveWarehouseToFile() {
        System.out.println("Saving receipt list to file");
        return warehouseDao.saveToFile();
    }
}
