package com.codewithbubblu.controller;

import com.codewithbubblu.controller.implementation.ICartController;
import com.codewithbubblu.utils.AppException;
import com.codewithbubblu.utils.FileUtil;
import com.codewithbubblu.utils.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.codewithbubblu.controller.AuthController.loggedInUserId;
import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.FileUtil.*;
import static com.codewithbubblu.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class CartController implements ICartController {
    HomeController homeController;
    OrderController orderController;
    public CartController(HomeController homeController) {
        this.homeController = homeController;
        orderController =new OrderController();

    }
    @Override
    public void addToCart(int categoryChoice) {
        boolean isFound = false;
        String[] proArr = new String[]{};
        try {
            Scanner scanner = new Scanner(getCartsFile());
            while (scanner.hasNext()) {
                String value = scanner.next().trim();
                String[] cartsArray = value.split(",");
                if (loggedInUserId == parseInt(cartsArray[0]) && categoryChoice ==parseInt(cartsArray[1])) {
                    isFound = true;
                    proArr=send(cartsArray);
                } else {}
            }
            addToCartTry(isFound,categoryChoice,proArr);
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private ArrayList<String> pickProductArray(int categoryChoice) {
        ArrayList<String> newProduct = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(getProductsFile());
            while (scanner.hasNext()) {
                String value = scanner.next().trim();
                String[] productsArray = value.split(",");
                if (parseInt(productsArray[0]) == categoryChoice) {
                    newProduct.add(String.valueOf(loggedInUserId));
                    newProduct.add(productsArray[0]);
                    newProduct.add(productsArray[1]);
                    newProduct.add(productsArray[2]);
                    newProduct.add(productsArray[3]);
                    newProduct.add(productsArray[4]);
                    newProduct.add(productsArray[5]);
                    newProduct.add("1");
                    break;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return newProduct;
    }
    @Override
    public void ShowCartProducts() {
        try {
            Scanner scanner = new Scanner(getCartsFile());
            while (scanner.hasNext()) {
                String value = scanner.next();
                String[] cartsArray = value.split(",");
                if (parseInt(cartsArray[0]) == loggedInUserId) {
                    System.out.println(cartsArray[2] + "x" + cartsArray[7] + "=>" + parseInt(cartsArray[4]) * parseInt(cartsArray[7]));
                }
            }
            println("1.checkOut");
            println("99.Back");
            int choice = enterInt(StringUtil.ENTER_CHOICE);
            if (choice == 99) {
                homeController.printMenu();
            } else if(choice==1) {
                orderController.orderfun();
            }
            else{
                homeController.invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void addToCartTry(boolean isFound,int categoryChoice,String[] proArr){
        ArrayList<String> existingProduct = new ArrayList<>();
        ArrayList<String> oldProduct = new ArrayList<>();
        if (isFound) {
            System.out.println("Existing user");
            System.out.println(proArr[1]+" "+categoryChoice);
            if (parseInt(proArr[1]) == categoryChoice) {
                System.out.println("Existing product");
                FileUtil.fileWritter(getCartsFile());
                oldProduct.add(proArr[0]);
                oldProduct.add(proArr[1]);
                oldProduct.add(proArr[2]);
                oldProduct.add(proArr[3]);
                oldProduct.add(proArr[4]);
                oldProduct.add(proArr[5]);
                oldProduct.add(proArr[6]);
                oldProduct.add(proArr[7]);
                existingProduct.add(proArr[0]);
                existingProduct.add(proArr[1]);
                existingProduct.add(proArr[2]);
                existingProduct.add(proArr[3]);
                existingProduct.add(proArr[4]);
                existingProduct.add(proArr[5]);
                existingProduct.add(proArr[6]);
                int count = parseInt(proArr[7]) + 1;
                existingProduct.add(String.valueOf(count));
                String newString = existingProduct.toString();
                String oldString = oldProduct.toString();
                newString = newString.replaceAll("\\[", "");
                newString = newString.replaceAll("\\]", "");
                newString = newString.replaceAll("\\s", "");
                oldString = oldString.replaceAll("\\[", "");
                oldString = oldString.replaceAll("\\]", "");
                oldString = oldString.replaceAll("\\s", "");
                try {
                    FileUtil.fileWritter(getCartsFile());
                    FileUtil.replaceCartFile(oldString,newString);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("new product");
                FileUtil.fileWritter(getCartsFile());
                ArrayList<String> newProduct = pickProductArray(categoryChoice);
                String s = newProduct.toString();
                s = s.replaceAll("\\[", "");
                s = s.replaceAll("\\]", "");
                s = s.replaceAll("\\s", "");
                appendStrTocartFile("src/main/java/com/codewithbubblu/assets/carts.csv", s);
            }
        }
        else {
            System.out.println("new user");
            FileUtil.fileWritter(getCartsFile());
            ArrayList<String> newProduct = pickProductArray(categoryChoice);
            String s = newProduct.toString();
            s = s.replaceAll("\\[", "");
            s = s.replaceAll("\\]", "");
            s = s.replaceAll("\\s", "");
            appendStrTocartFile("src/main/java/com/codewithbubblu/assets/carts.csv", s);
        }
    }
    public String[] send(String[] arr){
        return arr;
    }
}