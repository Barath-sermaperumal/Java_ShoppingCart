package com.codewithbubblu.controller;

import com.codewithbubblu.App;
import com.codewithbubblu.controller.implementation.IAuthController;
import com.codewithbubblu.models.Role;
import com.codewithbubblu.models.User;
import com.codewithbubblu.utils.AppException;
import com.codewithbubblu.utils.StringUtil;
import com.codewithbubblu.view.LoginPage;
import com.codewithbubblu.view.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.AppInput.enterString;
import static com.codewithbubblu.utils.FileUtil.getCredentialFile;
import static com.codewithbubblu.utils.Utils.println;

public class AuthController implements IAuthController {
    private final HomeController homeController;
    private final AppController appController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;

    public AuthController(AppController appController){
        this.appController=appController;
        homeController=new HomeController(this);
        loginPage=new LoginPage();
        registerPage=new RegisterPage();
    }

    @Override
    public void login() {
        String email,password;
        email=enterString(StringUtil.ENTER_EMAIL);
        password=enterString(StringUtil.ENTER_PASSWORD);

        User user= validateUser(email,password);
        if(user!=null){
            homeController.printMenu();
        }
        else {
            loginPage.invalidCredentials();
            authMenu();
        }
    }

    private User validateUser(String email, String password) {
        try {
            Scanner scanner=new Scanner(getCredentialFile());
            while (scanner.hasNext()){
                String value=scanner.next().trim();
                if(!value.startsWith("id")){
                    String[] userArray=value.split(",");
                    if(userArray[2].equals(email) && userArray[3].equals(password)){
                        User user=new User();
                        user.setId(Integer.parseInt(userArray[0]));
                        user.setName(userArray[1]);
                        user.setEmail(userArray[2]);
                        user.setPassword(userArray[3]);
                        if(user.getEmail().equals("admin@gmail.com"))
                            user.setRole(Role.ADMIN);
                        else
                            user.setRole(Role.USER);
                        return user;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void logout() {
        authMenu();
    }

    @Override
    public void register() {
        String name,email,password,c_password;
        name=enterString(StringUtil.ENTER_NAME);
        email=enterString(StringUtil.ENTER_EMAIL);
        password=enterString(StringUtil.ENTER_PASSWORD);
        c_password=enterString(StringUtil.ENTER_PASSWORD_AGAIN);

        if(password.equals(c_password)){
            try{
                FileWriter csvWritter=new FileWriter(getCredentialFile(),true);
                int id=(int)(Math.random()*100);
                csvWritter.append("\n");
                csvWritter.append(id+","+name+","+email+","+password);
                csvWritter.flush();
                csvWritter.close();
                registerPage.printRegistrationSuccessful();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        else {
            registerPage.passwordMisMatch();
        }
        authMenu();
    }

    @Override
    public void authMenu() {
        appController.printAuthMenu();
        int choice;
        try{
           choice=enterInt(StringUtil.ENTER_CHOICE);
           if(choice==1){
               login();
           } else if (choice==2) {
               register();
           }
           else {
               invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
           }
        }
        catch (AppException appException){
            invalidChoice(appException);
        }
    }

    private void invalidChoice(AppException e){
        println(e.toString());
        authMenu();
    }
}
