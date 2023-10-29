package com.codewithbubblu.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private static File credentialFile;
    private static File categoriesFile;
    private static File productsFile;
    private  static File cartFile;
    public static File getCredentialFile(){
        if(credentialFile==null){
            credentialFile=new File("src/main/java/com/codewithbubblu/assets/credentials.csv");
        }
        return credentialFile;
    }

    public static File getCategoriesFile(){
            categoriesFile=new File("src/main/java/com/codewithbubblu/assets/categories.csv");
        return categoriesFile;
    }

    public static File getProductsFile(){
            productsFile=new File("src/main/java/com/codewithbubblu/assets/products.csv");
        return productsFile;
    }

    public static File getCartsFile(){
            cartFile=new File("src/main/java/com/codewithbubblu/assets/carts.csv");
        return cartFile;
    }

    public static void appendStrTocartFile(String fileName, String str)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.newLine();
            out.write(str);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    public static void appendStrToproductFile(String fileName, String str)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.newLine();
            out.write(str);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }
}
