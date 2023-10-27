package com.codewithbubblu.controller;

import com.codewithbubblu.controller.implementation.ICartController;
import com.codewithbubblu.utils.StringUtil;

import java.util.Scanner;

import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.FileUtil.getCartsFile;
import static com.codewithbubblu.utils.FileUtil.getProductsFile;
import static com.codewithbubblu.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class CartController implements ICartController {
    HomeController homeController;
    public CartController(HomeController homeController){
        this.homeController=homeController;

    }
    @Override
    public void addToCart(int choice) {
        try{
            Scanner scanner=new Scanner(getCartsFile());
            while (scanner.hasNext()){
                String value=scanner.next().trim();
                String[] cartsArray=value.split(",");
                int userId=parseInt(cartsArray[0]);
                int productId=parseInt(cartsArray[1]);
                int productCount=parseInt(cartsArray[7]);
                if(userId==productId){

                }
                else {

                }
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
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
