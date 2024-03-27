/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.cache;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import cgt.resource.ConfigInfoCache;

/**
 *
 * @author Ho Sy Minh
 */
public class cache_file {

    private static String crunchify_file_location = "D:\\PROJECT\\VNPT@CRM\\ToolAloBDS\\build\\web\\cache\\";

    ConfigInfoCache config = new ConfigInfoCache();
    private final String logdir = config.ConfigInfoCache("LOG_DIR");

    // Main Method
    public static void main(String[] args) {

        int iphut = 5;
        long miliSec = 0;
        String strFilename = "minh1.txt";
        String strNoiDung = "chao ban 1xx 1213";
        long time_todaday = System.currentTimeMillis();
        String strUrlFile = crunchify_file_location + strFilename;
        String time_write = ReadDateFromFileCache(strUrlFile);
        System.out.println("vnpt.media.ReadWriteUtilityForFile.main(====time_toaday:" + time_todaday + "======)");
        System.out.println("vnpt.media.ReadWriteUtilityForFile.main(====time_write:" + time_write + "======)");
        try {
            miliSec = Long.parseLong(time_write);
            long tmp = time_todaday - miliSec;
            if (tmp > 1000 * 60 * iphut) {
                System.out.println("-------------- > " + iphut + "p . ghi file nhe :" + (tmp / 1000 / 60) + "p------------------");
                // Save data to file
                // WriteToFileCache(strFilename, strNoiDung);

            } else {
                System.out.println("-------------- < " + iphut + "p. chua ghi file : " + (tmp / 1000 / 60) + "p------------------");
            }
        } catch (Exception e) {
            System.out.println("-->Loi");
        }
        // String strContent = ReadFromFileCache(crunchify_file_location + strFilename);
        // System.out.println("vnpt.media.ReadWriteUtilityForFile.main(======strContent:" + strContent + "================)");

    }

    // check file xem có chưa nếu có thì đọc ra 
    public int CacheFile_Check(String strKey, int iphut) {
        int ikq = 0; // mac dinh = 0 la chua co file
        long miliSec = 0;
        String strUrlFile = logdir + strKey;
        long time_todaday = System.currentTimeMillis();
        String time_write = ReadDateFromFileCache(strUrlFile);
        //System.out.println("vnpt.media.ReadWriteUtilityForFile.main(====time_toaday:" + time_todaday + "======)");
        //System.out.println("vnpt.media.ReadWriteUtilityForFile.main(====time_write:" + time_write + "======)");
        try {
            miliSec = Long.parseLong(time_write);
            long tmp = time_todaday - miliSec;
            //System.out.println("--------------" + iphut + "p . ghi file nhe :" + (tmp / 1000 / 60) + "p------------------");
            if (tmp > 1000 * 60 * iphut) {
                ikq = 1;
                // System.out.println("vnpt.media.cache_file.CacheFile_Check(============1=============)");
            } else {
                ikq = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ikq;
    }

    public int CacheFile_CheckWrite(String Url) {
        int ikq = 0;
        File crunchifyFile = new File(Url);
        if (!crunchifyFile.exists()) {
            log("File doesn't exist");
            ikq = 1;
        }
        return ikq;
    }

    // Save to file Utility
    public int CacheFile_Write_io_cu(String myFileName, String myData) {
        int iok = 0;
        if (myData.equals("")) {
            return 0;
        }
        File crunchifyFile = new File(logdir + myFileName);
        if (!crunchifyFile.exists()) {
            try {
                File directory = new File(crunchifyFile.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                crunchifyFile.createNewFile();
            } catch (IOException e) {
                log("Excepton Occured: " + e.toString());
            }
        }

        try {
            // Convenience class for writing character files
            FileWriter crunchifyWriter = new FileWriter(crunchifyFile.getAbsoluteFile(), false);

            // Writes text to a character-output stream
            //BufferedWriter bufferWriter = new BufferedWriter(crunchifyWriter);
            // MINHHS THAY THE HAM NAY DE GHI FILE TIENG VIET
            Writer bufferWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(crunchifyFile), StandardCharsets.UTF_8));

            bufferWriter.write(myData.toString());
            bufferWriter.close();
            crunchifyWriter.close();
            iok = 1;

        } catch (IOException e) {
            log("Hmm.. Got an error while saving Company data to file " + e.toString());
        }
        return iok;
    }

    public int CacheFile_Write(String myFileName, String sData) {

        int iok = 0;
        if (sData.equals("")) {
            return 0;
        }

       
        //  System.out.println("vnpt.cache.cache_file.CacheFile_Write(-------ghi file :" + myFileName + " ------)");
        try {
            byte[] byteArray = sData.getBytes("UTF-8");
            ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

//            Set options = new HashSet();
//            options.add(StandardOpenOption.CREATE);
//            options.add(StandardOpenOption.WRITE);
            Set<StandardOpenOption> options = new HashSet<>();
            options.add(StandardOpenOption.CREATE);
            options.add(StandardOpenOption.WRITE);

            Path path = Paths.get(logdir + myFileName);
            Path parentDirectory = path.getParent();
            if (!Files.exists(parentDirectory)) {
                Files.createDirectories(parentDirectory);
            }
            System.out.println("Is directory writable: " + Files.isWritable(path.getParent()));


            FileChannel fileChannel = FileChannel.open(path, options);
            fileChannel.write(byteBuffer);
            //Files.write(path, lines, StandardCharsets.UTF_8);
            fileChannel.close();
            iok = 1;
        } catch (IOException e) {
            //  e.printStackTrace();
            System.out.println("vnpt.cache.cache_file.CacheFile_Write(error)");
            System.out.println();
            System.out.println();
        }
        return iok;
    }

//    public String CacheFile_Read(String filename) {
//
//        String strContent = "";
//        try {
//            RandomAccessFile randomAccessFile = new RandomAccessFile(logdir + filename, "r");
//            FileChannel fileChannel = randomAccessFile.getChannel();
//            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
//            Charset charset = Charset.forName("UTF-8");
//            while (fileChannel.read(byteBuffer) > 0) {
//                byteBuffer.rewind();
//                //System.out.print(charset.decode(byteBuffer));
//                strContent += charset.decode(byteBuffer).toString();
//                byteBuffer.flip();
//            }
//            fileChannel.close();
//            randomAccessFile.close();
//        } catch (IOException e) {
//        }
//
//        return strContent;
//    }
    
    public String CacheFile_Read(String filename) {
        StringBuilder strContent = new StringBuilder();

        try {
            Path path = Paths.get(logdir, filename);

            // Sử dụng Files.readAllBytes để đọc toàn bộ nội dung
            byte[] bytes = Files.readAllBytes(path);
            strContent.append(new String(bytes, StandardCharsets.UTF_8));

            // Hoặc sử dụng Files.newBufferedReader để đọc từng dòng
            // BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            // String line;
            // while ((line = reader.readLine()) != null) {
            //     strContent.append(line).append("\n");
            // }
            // reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strContent.toString();
    }

    public String CacheFile_Read_io_old(String filename) {
        String strContent = "";
        String Url = logdir + filename;
        //System.out.println("vnpt.cache.cache_file.CacheFile_Read(-----filename:" + filename + "-----)");
        File file = new File(Url);
        try {
            strContent = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return strContent;

    }

    public String CacheFile_Read1xx(String Url) {
        Url = logdir + Url;
        String strValues = "";
        File crunchifyFile = new File(Url);
        if (!crunchifyFile.exists()) {
            //log("File doesn't exist");
            return "";
        }

        FileInputStream fis = null;
        BufferedReader reader = null;

        try {
            fis = new FileInputStream(Url);
            reader = new BufferedReader(new InputStreamReader(fis));

            // objReader = new BufferedReader( new InputStreamReader( new FileInputStream(Url), "UTF8"));
            String line = reader.readLine();
            while (line != null) {
                strValues += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return strValues;
    }

    public String CacheFile_Read1(String Url) {
        Url = logdir + Url;
        String strValues = "";
        File crunchifyFile = new File(Url);
        if (!crunchifyFile.exists()) {
            log("File doesn't exist");
            return "";
        }
        BufferedReader objReader = null;
        String line = "";
        try {

            objReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Url), "UTF8"));

            while ((line = objReader.readLine()) != null) {
                strValues += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (objReader != null) {
                    objReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return strValues;
    }

    public static String ReadDateFromFileCache(String Url) {
        String strValues = "";
        File crunchifyFile = new File(Url);
        if (!crunchifyFile.exists()) {
            log("File " + Url + " doesn't exist");
        }

        try {
            // crunchifyFile.setLastModified(0);
            String time_write = "" + crunchifyFile.lastModified();
            strValues = time_write;

        } catch (Exception e) {
            e.printStackTrace();
        }

        // log("doc file: " + Url);
        //log("strValues: " + strValues);
        return strValues;

    }

    private static void log(String string) {
        System.out.println(string);
    }

}
