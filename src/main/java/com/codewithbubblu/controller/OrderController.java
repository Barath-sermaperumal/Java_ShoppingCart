package com.codewithbubblu.controller;

import com.codewithbubblu.utils.AppException;
import com.codewithbubblu.utils.StringUtil;

import java.util.Date;
import java.util.Scanner;

import static com.codewithbubblu.controller.AuthController.loggedInUserId;
import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.FileUtil.*;
import static com.codewithbubblu.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class OrderController {
    public static void adminOrderFunctions() {
        println(StringUtil.ENTER_ADMIN_ORDERS_CHOICES);
    }

    public static void orderfun(){
        try {
            Date currentDate = new Date();
            appendStrToOrdersFile("src/main/java/com/codewithbubblu/assets/orders.csv",String.valueOf(currentDate));
            Scanner scanner = new Scanner(getCartsFile());
            while (scanner.hasNext()) {
                String value = scanner.next();
                String[] cartsArray = value.split(",");
                if (parseInt(cartsArray[0]) == loggedInUserId) {

                   appendStrToOrdersFile("src/main/java/com/codewithbubblu/assets/orders.csv",value);
                   replaceCartFile(value,"");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
