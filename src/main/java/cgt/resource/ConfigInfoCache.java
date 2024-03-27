package cgt.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigInfoCache {

    public String ConfigInfoCache(String strValue) {
        try {
            String path = this.getClass().getClassLoader().getResource("").toURI().getRawPath();
            //System.out.println("==========ConfigInfo_Cache class=====path: " + path);
            path = getPropertiesFilePath(path, "config.properties");
            InputStream file = new FileInputStream(new File(path));
            Properties props = new Properties();
            props.load(file);
            strValue = props.getProperty(strValue, " ");
        } catch (FileNotFoundException fex) {
            fex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strValue;
    }

    public static String getPropertiesFilePath(String path, String fileName) {
        path = path.substring(0, path.lastIndexOf('/') - 1);
        path = path.substring(0, path.lastIndexOf('/') - 1);
        path = path.substring(0, path.lastIndexOf('/'));
        path = path + "/" + fileName;
       // path = path.replace('/', '\\').replace("%20", " ");
        return path;
    }
}
