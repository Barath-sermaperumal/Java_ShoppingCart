package com.codewithbubblu.utils;

import java.io.File;

public class FileUtil {
    private static File credentialFile;
    private static File categoriesFile;
    public static File getCredentialFile(){
        if(credentialFile==null){
            credentialFile=new File("src/main/java/com/codewithbubblu/assets/credentials.csv");
        }
        return credentialFile;
    }

    public static File getCategoriesFile(){
        if(categoriesFile==null){
            categoriesFile=new File("src/main/java/com/codewithbubblu/assets/Categories.csv");
        }
        return categoriesFile;
    }
}
