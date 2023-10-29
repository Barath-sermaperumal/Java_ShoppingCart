package com.codewithbubblu.controller;

import com.codewithbubblu.controller.implementation.ICartController;
import com.codewithbubblu.utils.StringUtil;

import java.util.ArrayList;
import java.util.Scanner;

import static com.codewithbubblu.controller.AuthController.loggedInUserId;
import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.FileUtil.*;
import static com.codewithbubblu.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class CartController implements ICartController {
    HomeController homeController;
    public CartController(HomeController homeController){
        this.homeController=homeController;

    }
    @Override
    public void addToCart(int categoryChoice) {
        ArrayList<String> existingProduct=new ArrayList<>();
        try{
            Scanner scanner=new Scanner(getCartsFile());
            while (scanner.hasNext()){
                String value=scanner.next().trim();
                String[] cartsArray=value.split(",");
                if(loggedInUserId==parseInt(cartsArray[0])){
                    System.out.println("Existing user");
                    if(parseInt(cartsArray[1])==categoryChoice){
                        System.out.println("Existing product");
                        existingProduct.add(cartsArray[0]);
                        existingProduct.add(cartsArray[1]);
                        existingProduct.add(cartsArray[2]);
                        existingProduct.add(cartsArray[3]);
                        existingProduct.add(cartsArray[4]);
                        existingProduct.add(cartsArray[5]);
                        existingProduct.add(cartsArray[6]);
                        int count=parseInt(cartsArray[7])+1;
                        existingProduct.add(String.valueOf(count));
                        String s=existingProduct.toString();
                        s=s.replaceAll("\\[", "");
                        s=s.replaceAll("\\]","");
                        System.out.println(s);
                        appendStrTocartFile("src/main/java/com/codewithbubblu/assets/carts.csv",s);
                        System.out.println(existingProduct);
                        break;
                    }
                    else{
                        System.out.println("new product");
                        ArrayList<String> newProduct=pickProductArray(categoryChoice);
                        String s=newProduct.toString();
                        s=s.replaceAll("\\[", "");
                        s=s.replaceAll("\\]","");
                        System.out.println(s);
                        appendStrTocartFile("src/main/java/com/codewithbubblu/assets/carts.csv",s);
                        break;
                    }
                }
                else {
                    System.out.println("new user");
                    ArrayList<String> newProduct=pickProductArray(categoryChoice);
                    String s=newProduct.toString();
                    s=s.replaceAll("\\[", "");
                    s=s.replaceAll("\\]","");
                    System.out.println(s);
                    appendStrTocartFile("src/main/java/com/codewithbubblu/assets/carts.csv",s);
                    System.out.println(pickProductArray(categoryChoice));
                    break;
                }
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
//        ArrayList<String> newProduct=new ArrayList<>();
//        try{
//            Scanner productScanner=new Scanner(getProductsFile());
//            Scanner cartScanner=new Scanner(getCartsFile());
//            while (productScanner.hasNext()||cartScanner.hasNext()){
//                String productValue=productScanner.next().trim();
//                String cartValue=cartScanner.next().trim();
//                String[] productsArray=productValue.split(",");
//                String[] cartsArray=cartValue.split(",");
//                System.out.println(loggedInUserId);
//                System.out.println(cartsArray[0]);
//                if(loggedInUserId==parseInt(cartsArray[0])){
//                    if(categoryChoice==parseInt(cartsArray[1])){
////                        newProduct.add(cartsArray[0]);
////                        newProduct.add(cartsArray[1]);
////                        newProduct.add(cartsArray[2]);
////                        newProduct.add(cartsArray[3]);
////                        newProduct.add(cartsArray[4]);
////                        newProduct.add(cartsArray[5]);
////                        newProduct.add(cartsArray[6]);
////                        newProduct.add(String.valueOf(parseInt(cartsArray[7])+1));
////                        System.out.println(newProduct);
////                        break;
//                    }
//                    else{
////                        newProduct.add(String.valueOf(loggedInUserId));
////                        newProduct.add(productsArray[0]);
////                        newProduct.add(productsArray[1]);
////                        newProduct.add(productsArray[2]);
////                        newProduct.add(productsArray[3]);
////                        newProduct.add(productsArray[4]);
////                        newProduct.add(productsArray[5]);
////                        newProduct.add("1");
////                        System.out.println(newProduct);
////                        break;
//                    }
//                }
//                else {
////                    newProduct.add(String.valueOf(loggedInUserId));
////                    newProduct.add(productsArray[0]);
////                    newProduct.add(productsArray[1]);
////                    newProduct.add(productsArray[2]);
////                    newProduct.add(productsArray[3]);
////                    newProduct.add(productsArray[4]);
////                    newProduct.add(productsArray[5]);
////                    newProduct.add("1");
////                    System.out.println(newProduct);
////                    break;
//                }
//            }
//            productScanner.close();
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
    }

    private ArrayList<String> pickProductArray(int categoryChoice) {
        ArrayList<String> newProduct=new ArrayList<>(){};
        try{
            Scanner scanner=new Scanner(getProductsFile());
            while (scanner.hasNext()){
                String value=scanner.next().trim();
                String[] productsArray=value.split(",");
                if(parseInt(productsArray[0])==categoryChoice){
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
        }
        catch (Exception e){
            System.out.println(e);
        }
        return newProduct;
    }


    @Override
    public void ShowCartProducts() {
        try{
            Scanner scanner=new Scanner(getCartsFile());
            while (scanner.hasNext()){
                String value=scanner.next().trim();
                String[] cartsArray=value.split(",");
                System.out.println(cartsArray[2]+"x"+cartsArray[7]+"=>"+parseInt(cartsArray[4])*parseInt(cartsArray[7]));
            }
            println("1.checkOut");
            println("99.Back");
            int choice=enterInt(StringUtil.ENTER_CHOICE);
            if(choice==99){
                homeController.printMenu();
            }
            else {
                System.out.println("under construction");
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
