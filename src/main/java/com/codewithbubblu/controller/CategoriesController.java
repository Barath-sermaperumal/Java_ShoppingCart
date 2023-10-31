package com.codewithbubblu.controller;

import com.codewithbubblu.controller.implementation.IAuthController;
import com.codewithbubblu.controller.implementation.ICategoriesController;
import com.codewithbubblu.utils.AppException;
import com.codewithbubblu.utils.StringUtil;

import java.util.Scanner;

import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.FileUtil.*;
import static com.codewithbubblu.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class CategoriesController implements ICategoriesController {
    HomeController homeController;
    ProductController productController;
    CartController cartController;

    public CategoriesController(HomeController homeController) {
        this.homeController = homeController;
        productController = new ProductController(homeController);
        cartController = new CartController(homeController);
    }

    @Override
    public void showCategories() {
        try {
            Scanner scanner = new Scanner(getCategoriesFile());
            while (scanner.hasNext()) {
                String value = scanner.next().trim();
                String[] categoriesArray = value.split(",");
                System.out.println(categoriesArray[0] + "." + categoriesArray[1]);
            }
            println("99.Back");
            int categoryChoice = enterInt(StringUtil.ENTER_CHOICE);
            if (categoryChoice == 99) {
                homeController.printMenu();
            } else {
                if (categoryChoice == 1) {
                    productController.showSelectedProducts(1);
                } else if (categoryChoice == 2) {
                    productController.showSelectedProducts(2);
                } else if (categoryChoice == 3) {
                    productController.showSelectedProducts(3);
                } else if (categoryChoice == 4) {
                    productController.showSelectedProducts(4);
                } else {
                    homeController.invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
                }
                println("99.Back");
                int Choice = enterInt(StringUtil.ADD_TO_CART);
                if (Choice == 99) {
                    homeController.printMenu();
                } else {
                    cartController.addToCart(Choice);
                    println(StringUtil.CART_SUCCESSFULL);
                    productController.showProducts();
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void adminCategoryFunctions() {
        println(StringUtil.ENTER_ADMIN_CATEGORIES_CHOICES);
        try {
            int adminCategoryChoice = enterInt(StringUtil.ENTER_CHOICE);
            if (adminCategoryChoice == 99) {
                adminCategoryFunctions();
            } else {
                if (adminCategoryChoice == 1) {
                    showCategories();
                } else if (adminCategoryChoice == 2) {
                    addCategories();
                } else if (adminCategoryChoice == 3) {
                    int choice=enterInt(StringUtil.ENTER_CHOICE);
                    deleteCategories(choice);
                }else if (adminCategoryChoice == 3) {
                    editCategories();
                }else {
                    homeController.invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
                }
            }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    private void editCategories() {
    }

    @Override
    public void deleteCategories(int deleteChoice) {
        try {
            Scanner scanner = new Scanner(getCategoriesFile());
            while (scanner.hasNext()) {
                String value = scanner.next().trim();
                String[] categoriesArray = value.split(",");
                if(parseInt(categoriesArray[0])==deleteChoice){
                    replaceCartFile(value,"");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void addCategories() {
        String newProduct="";
        appendStrToCategoriesFile("src/main/java/com/codewithbubblu/assets/categories.csv",newProduct);
    }
}
