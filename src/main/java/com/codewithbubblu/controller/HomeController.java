package com.codewithbubblu.controller;

import com.codewithbubblu.controller.implementation.IHomeController;
import com.codewithbubblu.utils.AppException;
import com.codewithbubblu.utils.AppScanner;
import com.codewithbubblu.utils.StringUtil;
import com.codewithbubblu.view.HomePage;
import com.codewithbubblu.view.WelcomePage;

import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.Utils.print;
import static com.codewithbubblu.utils.Utils.println;

public class HomeController implements IHomeController {

    HomePage homePage;
    AuthController authController;
    CategoriesController categoriesController;
    public HomeController(AuthController authController){
        homePage=new HomePage();
        this.authController=authController;
        categoriesController=new CategoriesController();
    }

    @Override
    public void printMenu() {
        homePage.printWelcome();
        homePage.printMenu();
        try {
            int choice=enterInt(StringUtil.ENTER_CHOICE);
                if(choice==1){
                    println(StringUtil.CATEGORIES);
                    categoriesController.showProducts();

                } else if (choice==2) {
                    System.out.println("2");
                } else if (choice==3) {
                    System.out.println("3");
                } else if (choice==4) {
                    System.out.println("4");
                } else if (choice==5) {
                    authController.authMenu();
                } else {
                    invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
                }
                int categoryChoice=enterInt(StringUtil.ENTER_CHOICE);
                if(categoryChoice==99){
                    printMenu();
                }
                else{

                }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    private void invalidChoice(AppException e) {
        println(e.toString());
        printMenu();
    }

}
