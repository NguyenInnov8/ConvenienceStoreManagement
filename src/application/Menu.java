/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import utils.MyUtils;
import action_service.Service;


/**
 *
 * @author ASUS
 */
public class Menu {
    Service myService;
    int choice;

    void mainMenu() {
        System.out.println("****----Store Management-----****");
        System.out.println("1. Manage Product\n"
                + "2. Mangage Warehouse\n"
                + "3. Report\n"
                + "4. Save data to file\n"
                + "5. Exit");
    }

    void productManagementMenu() {
        System.out.println("****----Products Management-----****");
        System.out.println("1. Add a product\n"
                + "2. Update product information.\n"
                + "3. Delete product.\n"
                + "4. Show all product. \n"
                + "5. Back to Main Menu .");
    }

    public void manageWarehouseMenu() {
        System.out.println("****----Manage Warehouse-----****");
        System.out.println("1. Create an import receipt.\n"
                + "2. Create an export receipt.\n"
                + "3. Back to Main Menu. ");
    }

    public void reportMenu() {
        System.out.println("****----Report-----****");
        System.out.println("1.Products that have expired\n"
                + "2. The products that the store is selling.\n"
                + "3. Products that are running out of stock (sorted in ascending order).\n"
                + "4. Import/export receipt of a product. \n"
                + "5. Back to Main Menu");
    }

    public void storeDateToFilesMenu() {
        System.out.println("****----Storing Data-----****");
        System.out.println("1. Load data from file. \n"
                + "2. Save data from file. \n"
                + "3. Back to Main Menu");

    }
    
    public void mainMenuImpl() {
        do {
            mainMenu();
            choice = MyUtils.inputInteger("Please enter your choice: ", 1, 5);
            
            switch (choice) {
                case 1: 
                    productManagementImpl();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        } while (choice != 5);
    }

    public void productManagementImpl() {
        do {
            productManagementMenu();
            choice = MyUtils.inputInteger("Please enter your choice", 1, 5);

            switch (choice) {
                case 1:
          
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    mainMenuImpl();
                    break;
            }

        } while (choice != 5);
    }

}
