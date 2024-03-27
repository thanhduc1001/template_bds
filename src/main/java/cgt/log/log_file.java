/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Ho Sy Minh
 */
public class log_file {
     public static void openFile(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException ex) { }
    }
    
    public static void openFolder(String folderPath) {
        File f = new File(folderPath);
        if (!f.exists()) {
            f.mkdirs();
        }
    }
  
    public static void append(String line, String filePath) {
        openFile(filePath);
        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(filePath, true));
            outFile.write(line);
            outFile.newLine();
            outFile.flush();
        } catch (Exception ex) {
        } finally {
            try { outFile.close(); } catch (Exception e) {}
        }
    }

    public static String createFile(String dir, String prefix, String suffix) {
        String filePath;        
        filePath = dir + prefix +  "_" + NowDate("yyyyMMdd");
        int i = -1;
        File f;
        String temp;
        do {
            i++;
            temp = filePath + "_" + Integer.toString(i) + suffix;            
            f = new File(temp);
            
        } while (f.exists() && !(f.exists() && f.length() < 10485760)); //10MB = 10*1024*1024 = 10,485,760 Byte
        filePath = temp;
        return filePath;
    }
    
    public static void log(String dir, String prefix, String content) {
        String line = NowDate("[dd/MM/yyyy HH:mm:ss:SSS]") + " " + content;
        openFolder(dir);
        String fileName = createFile(dir, prefix, ".log");
        append(line, fileName);
    }
    
    public static String NowDate(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }
}
