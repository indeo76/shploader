package pl.wodnet.shploader.tools;


import com.google.common.io.Files;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public static BigDecimal toBigDecimal(Double value, int scale) {
        if (value == null) {
            return null;
        }
        return BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_UP);
    }
}
