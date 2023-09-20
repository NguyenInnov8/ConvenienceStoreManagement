/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class MyUtils {
    public static Scanner sc = new Scanner(System.in);
    public static int inputInteger(String msg, int min, int max) {
        int data = Integer.MIN_VALUE;
        while(true) {
            try {
                if(msg != null) {
                System.out.println(msg);
            }
                data = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            
            if(data >= min && data <= max)
                break;
            System.out.println("Please input a valid value. Value is only in the domain [" + min + ", " + max + "]");
        }
        return data;
    }
    
    public static double inputDouble(String msg, int min, int max) {
        double data = Integer.MIN_VALUE;
        while(true) {
            try {
                if(msg != null) {
                System.out.println(msg);
            }
                data = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            
            if(data >= min && data <= max)
                break;
            System.out.println("Please input a valid value. Value is only in the domain [" + min + ", " + max + "]");
        }
        return data;
    }
    
     public static String inputString(String msg) {
        do { 
            if (msg != null)
            System.out.println(msg);
            String data = sc.nextLine();
            
            if(!data.isEmpty()) {
                return data;
            }
            System.out.println("Value cannot empty");
        } while(true);
    }
}
