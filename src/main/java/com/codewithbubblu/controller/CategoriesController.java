package com.codewithbubblu.controller;

import com.codewithbubblu.controller.implementation.IAuthController;
import com.codewithbubblu.controller.implementation.ICategoriesController;
import com.codewithbubblu.utils.StringUtil;

import java.util.Scanner;

import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.FileUtil.getCategoriesFile;
import static com.codewithbubblu.utils.Utils.println;

public class CategoriesController implements ICategoriesController {
    HomeController homeController;

    public CategoriesController(HomeController homeController){
        this.homeController=homeController;
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
                System.out.println("Under Development");
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
