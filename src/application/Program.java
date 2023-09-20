/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import action_service.IService;
import action_service.Service;
import java.util.Date;

public class Program {
    public static void main(String[] args) {
        IService myService = new Service();
        myService.addProduct();
    }
}
