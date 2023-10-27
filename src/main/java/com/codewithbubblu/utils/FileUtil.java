package com.codewithbubblu.utils;

import java.io.File;

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
        if(categoriesFile==null){
            categoriesFile=new File("src/main/java/com/codewithbubblu/assets/categories.csv");
        }
        return categoriesFile;
    }

    public static File getProductsFile(){
        if(productsFile==null){
            productsFile=new File("src/main/java/com/codewithbubblu/assets/products.csv");
        }
        return productsFile;
    }

    public static File getCartsFile(){
        if(cartFile==null){
            cartFile=new File("src/main/java/com/codewithbubblu/assets/carts.csv");
        }
        return cartFile;
    }
}
