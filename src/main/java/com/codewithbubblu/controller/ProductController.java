package com.codewithbubblu.controller;

import com.codewithbubblu.utils.StringUtil;

import java.util.Scanner;

import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.FileUtil.getCategoriesFile;
import static com.codewithbubblu.utils.FileUtil.getProductsFile;
import static com.codewithbubblu.utils.Utils.print;
import static com.codewithbubblu.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class ProductController {
    HomeController homeController;
    CartController cartController;
    public ProductController(HomeController homeController){
        this.homeController=homeController;
        cartController=new CartController(homeController);
    }
    public void showProducts() {
        try{
            Scanner scanner=new Scanner(getProductsFile());
            while (scanner.hasNext()){
                String value=scanner.next().trim();
                String[] productsArray=value.split(",");
                System.out.println(productsArray[0]+"."+productsArray[1]+"=>"+productsArray[3]);
            }
            println("99.Back");
            int categoryChoice=enterInt(StringUtil.ADD_TO_CART);
            if(categoryChoice==99){
                homeController.printMenu();
            }
            else{
                cartController.addToCart(categoryChoice);
                println(StringUtil.CART_SUCCESSFULL);
                showProducts();
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void adminProductFunctions() {
        println(StringUtil.ENTER_ADMIN_PRODUCTS_CHOICES);
    }

    public void showSelectedProducts(int id){
        try{
            Scanner scanner=new Scanner(getProductsFile());
            while (scanner.hasNext()) {
                String value = scanner.next().trim();
                String[] productsArray = value.split(",");
                if(parseInt(productsArray[5])==id){
                    System.out.println(productsArray[0]+"."+productsArray[1]+"=>"+productsArray[3]);
                }
                else {}
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
