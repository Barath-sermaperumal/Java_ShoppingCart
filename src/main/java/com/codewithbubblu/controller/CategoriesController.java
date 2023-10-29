package com.codewithbubblu.controller;

import com.codewithbubblu.controller.implementation.IAuthController;
import com.codewithbubblu.controller.implementation.ICategoriesController;
import com.codewithbubblu.utils.AppException;
import com.codewithbubblu.utils.StringUtil;

import java.util.Scanner;

import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.FileUtil.getCategoriesFile;
import static com.codewithbubblu.utils.Utils.println;

public class CategoriesController implements ICategoriesController {
    HomeController homeController;
    ProductController productController;
    CartController cartController;

    public CategoriesController(HomeController homeController){
        this.homeController=homeController;
        productController=new ProductController(homeController);
        cartController=new CartController(homeController);
    }
    @Override
    public void showCategories() {
        try{
            Scanner scanner=new Scanner(getCategoriesFile());
            while (scanner.hasNext()){
                String value=scanner.next().trim();
                String[] categoriesArray=value.split(",");
                System.out.println(categoriesArray[0]+"."+categoriesArray[1]);
            }
            println("99.Back");
            int categoryChoice=enterInt(StringUtil.ENTER_CHOICE);
            if(categoryChoice==99){
                homeController.printMenu();
            }
            else{
                if(categoryChoice==1){
                    productController.showSelectedProducts(1);
                } else if (categoryChoice==2) {
                    productController.showSelectedProducts(2);
                } else if (categoryChoice==3) {
                    productController.showSelectedProducts(3);
                } else if (categoryChoice==4) {
                    productController.showSelectedProducts(4);
                }
                else {
                    homeController.invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
                }
                println("99.Back");
                int Choice=enterInt(StringUtil.ADD_TO_CART);
                if(Choice==99){
                    homeController.printMenu();
                }
                else{
                    cartController.addToCart(Choice);
                    println(StringUtil.CART_SUCCESSFULL);
                    productController.showProducts();
                }
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void adminCategoryFunctions() {
        println(StringUtil.ENTER_ADMIN_CATEGORIES_CHOICES);
    }
}
