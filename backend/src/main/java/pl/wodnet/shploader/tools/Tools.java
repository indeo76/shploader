package pl.wodnet.shploader.tools;


import com.google.common.io.Files;

import java.io.File;

public class Tools {
    public static String getNameWithoutExtension(String filename) {
        return Files.getNameWithoutExtension(filename);
    }

    public static void listFilesRecursive(File dirPath){
        File filesList[] = dirPath.listFiles();
        for(File file : filesList) {
            if(file.isFile()) {
                System.out.println("File path: "+file.getName());
            } else {
                listFilesRecursive(file);
            }
        }
    }
}
