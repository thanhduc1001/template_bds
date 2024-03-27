/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.functions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Random;
import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import cgt.cache.cache_file;
import cgt.resource.ConfigInfoCache;
import static cgt.functions.DateProc.Timestamp2YYYYMMDD;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;

/**
 *
 * @author minhhs@vnpt.vn
 */
public class Function {

    ConfigInfoCache config = new ConfigInfoCache();
    private String CACHE_FILE = config.ConfigInfoCache("CACHE_FILE");
    private String USERNAME_API = config.ConfigInfoCache("USERNAME_API");
    private String PASSWORD_API = config.ConfigInfoCache("PASSWORD_API");
    
    
    
    
    //================TOKEN AREA====================//
    //Khai báo biến tĩnh lưu trữ access_token
    private static String accessToken;
    
    //Hàm khởi tạo access_token, sử dụng khi load trang web lần đầu.
    public static void initAccessToken() {
        if (accessToken == null || accessToken.isEmpty()) {
            accessToken = PostJsonGetToken2(); // Gọi hàm lấy access token và lưu trữ nó
            refreshAccessToken();
        }
    }
    
    // Hàm này để lấy access token đã được lưu trữ
    public static String getAccessToken() {
        return accessToken;
    }
    
    // Hàm này để cập nhật lại access token nếu cần
    public static void refreshAccessToken() {
        ConfigInfoCache config = new ConfigInfoCache();
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        long refresh_time = Long.parseLong(config.ConfigInfoCache("REFRESH_TOKEN_TIME"));
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                accessToken = PostJsonGetToken2(); // Gọi lại hàm PostJsonGetToken2 sau (refresh_time) phút
            }
        }, 0, refresh_time, TimeUnit.MINUTES);
    }
    
    public static String PostJsonGetToken2() {

        ConfigInfoCache config = new ConfigInfoCache();
        String API_CMS = config.ConfigInfoCache("API_CMS");
        String username = config.ConfigInfoCache("USERNAME_API");
        String password = config.ConfigInfoCache("PASSWORD_API");

        String data = "{"
                + "\"username\":\"" + username + "\","
                + "\"password\":\"" + password + "\""
                + "}";

        HttpClient httpClient = null;
        HttpPost post = null;
        InputStream inputStream = null;
        String responseStr = "";
        String token = "";
        try {
            String postUrl = API_CMS + "auth/token";
            httpClient = HttpClientBuilder.create().build();
            post = new HttpPost(postUrl);

            StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);

            post.setEntity(entity);
            post.addHeader("Accept", "application/json");
            post.addHeader("Content-Type", "application/json; charset=utf-8");

            HttpResponse response = httpClient.execute(post);

            int status = response.getStatusLine().getStatusCode();
            if (status != 200) {
                System.out.println("status != 200 ");
                return "";
            }

            HttpEntity entity2 = response.getEntity();

            if (entity2 != null) {
                String webpage = EntityUtils.toString(entity2, StandardCharsets.UTF_8);
                responseStr = webpage;
            }
            JSONObject obj = new JSONObject(responseStr);
            JSONArray arr = (JSONArray) obj.get("result");
            JSONObject arrObject = arr.getJSONObject(0);
            Function fc = new Function();
            token = fc.CheckNullObjectKey(arrObject, "access_token");
            return token;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }
    //================TOKEN AREA====================//

    public String Encypt_MD5(String strPw) {
        MessageDigest mymd5 = null;
        try {
            mymd5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
        }
        mymd5.update(strPw.getBytes());

        byte[] digest = mymd5.digest();
        StringBuffer hexStr = new StringBuffer();

        for (int i = 0; i < digest.length; i++) {
            strPw = Integer.toHexString(0xFF & digest[i]);

            if (strPw.length() < 2) {
                strPw = "0" + strPw;
            }
            hexStr.append(strPw);
        }
        String strValue = DigestUtils.sha256Hex(hexStr.toString());
        return strValue;
    }

    public String Encypt_MD5_SHA(String strPw) {
        MessageDigest mymd5 = null;
        try {
            mymd5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
        }
        mymd5.update(strPw.getBytes());

        byte[] digest = mymd5.digest();
        StringBuffer hexStr = new StringBuffer();

        for (int i = 0; i < digest.length; i++) {
            strPw = Integer.toHexString(0xFF & digest[i]);

            if (strPw.length() < 2) {
                strPw = "0" + strPw;
            }
            hexStr.append(strPw);
        }
        String strValue = DigestUtils.sha256Hex(hexStr.toString());
        return strValue;
    }

    public String Encypt_MD5_OLD(String strPw) {
        MessageDigest mymd5 = null;
        try {
            mymd5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
        }
        mymd5.update(strPw.getBytes());

        byte[] digest = mymd5.digest();
        StringBuffer hexStr = new StringBuffer();

        for (int i = 0; i < digest.length; i++) {
            strPw = Integer.toHexString(0xFF & digest[i]);

            if (strPw.length() < 2) {
                strPw = "0" + strPw;
            }
            hexStr.append(strPw);
        }
        return hexStr.toString();
    }

    public String RemoveHTML(String org) {
        String str = org;
        try {
            // System.out.println("Before removing HTML Tags: " + str);
            str = str.replaceAll("\\<.*?\\>", "");
            //System.out.println("After removing HTML Tags: " + str);
        } catch (Exception ex) {
            System.out.println(" RemoveHTMLTag error: " + ex);
        }
        return str;
    }

    public String getIntro(String org, int kytu, String strLast) {
        String str = "";
        try {

            if (org.length() > kytu) {
                str = org.substring(0, kytu) + strLast;
            } else {
                str = org;
            }

        } catch (Exception ex) {
            System.out.println(" getIntro error: " + ex);
        }
        return str;
    }

    public String UrlVn(String org) {
        org = org.trim();
        org = convertStringRemoveVn(org);
        String strUrl = BoDauTiengViet(org).replaceAll(" ", "-").toLowerCase();

        try {
            strUrl = strUrl.trim();
            //strUrl = strUrl.replaceAll("[()|~`!@#$%^&*_=+{}/;:',?']", "");
            strUrl = strUrl.replaceAll("[^0-9a-zA-Z-_]", "");
            strUrl = strUrl.replaceAll("---", "-");
            strUrl = strUrl.replaceAll("--", "-");

        } catch (Exception e) {
        }

        return strUrl;
    }

    public int checkIndexOf(String strTitle, String strKey) {
        try {
            strTitle = strTitle.toLowerCase();
            strKey = strKey.toLowerCase();
            int dem = strTitle.indexOf(strKey);
            return dem;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("checkIndexOf(): error: " + e.getMessage() + " ===");
        }
        return -2;
    }

    public static String getDomain(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();

            // Kiểm tra xem domain có null không và có chứa "www." không
            if (domain != null && domain.startsWith("www.")) {
                domain = domain.substring(4);
            }

            return domain;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDateStringYYYYMMDD(java.sql.Timestamp ts) {
        if (ts == null) {
            return "";
        }
        String str = Timestamp2YYYYMMDD(ts);
        str = str.replaceAll("/", "");
        return str;
    }

    public String getLastDay(int num, String Format) {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(Format);
        String dates = "";
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -num);
            dates = formatter.format(cal.getTime());
            return dates;
        } catch (Exception e) {
            System.out.println("eror getLastDay");
            return "";
        }
    }

    public String getLastThu(int num, String Format) {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(Format);
        String dates = "";
        try {
//            Calendar cal = Calendar.getInstance();
//            cal.add(Calendar.DATE, -num);
//            // dates = formatter.format(cal.getTime());
//            DayOfWeek dayOfWeek = DayOfWeek.valueOf(cal.getTime());
//            
//            int val = dayOfWeek.getValue();
//            dates = val + "";
            return dates;
        } catch (Exception e) {
            System.out.println("eror getLastDay");
            return "";
        }
    }

    public String ShowThuNgay(String org) {
        String str = org;
        try {

            if (org.equals("0")) {
                str = "Thứ 2";
            } else if (org.equals("1")) {
                str = "Thứ 3";
            } else if (org.equals("2")) {
                str = "Thứ 4";
            } else if (org.equals("3")) {
                str = "Thứ 5";
            } else if (org.equals("4")) {
                str = "Thứ 6";
            } else if (org.equals("5")) {
                str = "Thứ 7";
            } else if (org.equals("6")) {
                str = "Chủ nhật";
            }

        } catch (Exception ex) {
            System.out.println(" ShowBongDB error: " + ex);
        }
        return str;
    }

    public String getTimeID() {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
        String dates = "";
        try {
            dates = formatter.format(date) + randInt(1000, 9999);
            return dates;
        } catch (Exception e) {
            System.out.println("eror getTimeID");
            return "";
        }
    }

    public String requestDate(String requestDate) {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(requestDate);
        String dates = "";
        try {
            dates = formatter.format(date);
            return dates;
        } catch (Exception e) {
            System.out.println("eror requestDate");
            return "";
        }
    }

    public String getTimeFormat(String strformat) {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(strformat);
        String dates = "";
        try {
            dates = formatter.format(date);
            return dates;
        } catch (Exception e) {
            System.out.println("eror getTimeFormat");
            return "";
        }
    }

    public int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static void location(String strurl)
            throws IOException {
        try {

        } catch (Exception e) {
            System.out.println("eror location : " + e);
        }
    }

    public static void writeFile(String canonicalFilename, String text)
            throws IOException {
        try {
            File file = new File(canonicalFilename);
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(text);
            out.close();
        } catch (Exception e) {
            System.out.println("eror writeFile : " + e);
        }
    }

    public static void writeFileAsBytes(String fullPath, byte[] bytes) throws IOException {
        try {
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fullPath));
            InputStream inputStream = new ByteArrayInputStream(bytes);
            int token = -1;

            while ((token = inputStream.read()) != -1) {
                bufferedOutputStream.write(token);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("eror writeFileAsBytes : " + e);
        }
    }

    public static void writeFileAsFile(String fullPath, File filename) throws IOException {
        try {
            byte[] buffer = new byte[100000];
            BufferedInputStream bufferedInputStream = null;

            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fullPath));
            //InputStream inputStream = new ByteArrayInputStream(bytes);
            bufferedInputStream = new BufferedInputStream(new FileInputStream(filename));
            int size;
            while ((size = bufferedInputStream.read(buffer)) > -1) {
                bufferedOutputStream.write(buffer, 0, size);
            }
            // int token = -1;

//            while ((token = inputStream.read()) != -1) {
//                bufferedOutputStream.write(token);
//            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            // inputStream.close();
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        } catch (Exception e) {
            System.out.println("eror writeFileAsBytes : " + e);
        }
    }

    public static void copyFile(File source, File destination) throws IOException {
        byte[] buffer = new byte[100000];

        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(source));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destination));
            int size;
            while ((size = bufferedInputStream.read(buffer)) > -1) {
                bufferedOutputStream.write(buffer, 0, size);
            }
        } catch (IOException e) {
            // TODO may want to do something more here
            throw e;
        } finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                }
            } catch (IOException ioe) {
                // TODO may want to do something more here
                throw ioe;
            }
        }
    }

    public String StatusADS(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Đang chờ gửi";
            } else if (value.equals("2")) {
                sValue = "Đã gửi";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusADS");
            return "";
        }

    }

    public int CheckJsonBigData(String json) {
        String sValue = "";
        try {

            int f = json.indexOf("0,");
            // System.out.println("CheckJsonBigData:" + f); 
            if (f > 0) {
                return 1;
            } else {
                return 0;
            }

        } catch (Exception e) {
            System.out.println("eror CheckJsonBigData");
            return 0;
        }

    }

    public String StatusServiceDetail(String value) {
        // trang thai chi tiet chien dich dataplus
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ xử lý</font>";
            } else if (value.equals("1")) {
                sValue = "<i class=\"fa fa-check\"></i> Thành công";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Backlist</font>";
            } else if (value.equals("3")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Bị giới hạn điều kiện</font>";
            } else if (value.equals("4")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Quá giới hạn Đơn hàng</font>";
            } else if (value.equals("5")) {
                sValue = " <i class=\"fa fa-clock-o\"></i> Đang xử lý ";
            } else if (value.equals("6")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Không thành công </font>";
            } else if (value.equals("7")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Đơn hàng hết hạn </font>";
            } else if (value.equals("8")) {
                sValue = "<font style=\"color: red\"> Đơn hàng đang tạm dừng</font>";
            } else if (value.equals("10")) {
                sValue = "<font style=\"color: red\"> Đang tạo...</font>";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusServiceDetail");
            return "";
        }

    }

    public String StatusActive(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Active";
            } else if (value.equals("0")) {
                sValue = "Lock";
            } else if (value.equals("2")) {
                sValue = "Delete";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String VinaSportShowName(String value) {
        String sValue = "";
        value = value.toUpperCase();
        try {
            if (value.equals("SPORT_FREE")) {
                sValue = "Tin miễn phí";
            } else if (value.equals("SPORT_NEW")) {
                sValue = "Tin mới";
            } else if (value.equals("SPORT_OLYMPIC")) {
                sValue = "Olympic Tokyo 2020";
            } else if (value.equals("SPORT_WORLDCUP")) {
                sValue = "World Cup 2022";
            } else if (value.equals("SPORT_HOT")) {
                sValue = "Nổi bật";
            } else if (value.equals("SPORT_COMMON")) {
                sValue = "Thể thao";
            } else if (value.equals("SPORT_VIETNAM")) {
                sValue = "Bóng đá Việt Nam";
            } else if (value.equals("SPORT_QUOCTE")) {
                sValue = "Bóng đá quốc tế";
            } else if (value.equals("SPORT_NHA")) {
                sValue = "Ngoại hạng Anh";
            } else if (value.equals("SPORT_TBN")) {
                sValue = "Tây Ban Nha";
            } else if (value.equals("SPORT_ITALIA")) {
                sValue = "Italia";
            } else if (value.equals("SPORT_DUC")) {
                sValue = "Đức";
            } else if (value.equals("SPORT_PHAP")) {
                sValue = "Pháp";
            } else if (value.equals("SPORT_GIAIKHAC")) {
                sValue = "Các giải khác	";
            } else if (value.equals("SPORT_IDEA")) {
                sValue = "Ý kiến chuyên gia";
            } else if (value.equals("SPORT_TRANSFER")) {
                sValue = "Tin chuyển nhượng";
            } else if (value.equals("SPORT_RESTROOM")) {
                sValue = "Tin hậu trường";
            } else if (value.equals("SPORT_VIDEO")) {
                sValue = "Video";
            } else if (value.equals("SPORT_TIMETABLE")) {
                sValue = "Lịch thi đấu";
            } else if (value.equals("SPORT_SCORE")) {
                sValue = "Kết quả bóng đá";
            } else if (value.equals("SPORT_RANK")) {
                sValue = "Bảng xếp hạng";
            } else if (value.equals("SPORT_AFFCUP")) {
                sValue = "AFF Cup 2022";
            } else if (value.equals("SPORT_SG31")) {
                sValue = "Sea Games 31";
            } else {
                sValue = value;
            }
            sValue = sValue.trim();
            return sValue;
        } catch (Exception e) {
            System.out.println("VinaSportShowName");
            return "";
        }

    }

    public String StatusVsignTB(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Kích hoạt";
            } else if (value.equals("0")) {
                sValue = "Tạm dừng";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String StatusVsignTBReg(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Đăng Ký";
            } else if (value.equals("2")) {
                sValue = "Hủy Đăng Ký";
            } else if (value.equals("3")) {
                sValue = "Tạm dừng";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String StatusServiceActive(String value) {
        // trang thai chien dich dataplus
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Đang chạy";
            } else if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ duyệt...</font>";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-exclamation-triangle\"></i> Từ chối </font>";
            } else if (value.equals("3")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-pause\"></i> Tạm dừng </font>";
            } else if (value.equals("5")) {
                sValue = "Kết thúc";
            } else if (value.equals("4")) {
                sValue = "<font style=\"color: red\">Đang tạo...</font>";
            } else if (value.equals("6")) {
                sValue = "Đã xử lý xong";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusServiceActive");
            return "";
        }

    }

    public String StatusDataLog(String value) {
        // trang thai bang tbl_data_log
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<i class=\"fa fa-check\"></i> Thành công";
            } else if (value.equals("1")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-exclamation-triangle\"></i> Không thành công</font>";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Backlist</font>";
            } else if (value.equals("3")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Bị giới hạn điều kiện</font>";
            } else if (value.equals("4")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Đơn hàng không đủ tiền</font>";
            } else if (value.equals("5")) {
                sValue = " <i class=\"fa fa-clock-o\"></i> Đang xử lý ";
            } else if (value.equals("6")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> GPGS Off </font>";
            } else if (value.equals("7")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Đơn hàng hết hạn </font>";
            } else if (value.equals("8")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-close\"></i> Đơn hàng đang tạm dừng </font>";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusDataLog");
            return "";
        }

    }

    public String StatusDaiLy(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Cấp 2";
            } else if (value.equals("0")) {
                sValue = "Cấp 3";
            } else if (value.equals("2")) {
                sValue = "Cấp 1";
            } else if (value.equals("-1")) {
                sValue = "Delete";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusDaiLy");
            return "";
        }

    }

    public String StatusCD(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Đang chạy";
            } else if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Tạm dừng</font>";
            } else if (value.equals("3")) {
                sValue = "<font style=\"color: red\">Chờ duyệt...</font>";
            } else if (value.equals("2")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String StatusCDSend(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chưa duyệt</font>";
            } else if (value.equals("1")) {
                sValue = "Đã duyệt";
            } else if (value.equals("2")) {
                sValue = "Đang chạy...";
            } else if (value.equals("3")) {
                sValue = "<i class=\"fa fa-check\"></i> Đã gửi";
            } else if (value.equals("4")) {
                sValue = "<font style=\"color: red\">Tạm dừng</font>";
            } else if (value.equals("5")) {
                sValue = "<font style=\"color: red\">Chờ duyệt...</font>";
            } else if (value.equals("6")) {
                sValue = "API kết nối";
            } else if (value.equals("7")) {
                sValue = "<font style=\"color: red\">Tạm dừng do hết quota</font>";
            } else if (value.equals("8")) {
                sValue = "Tiếp tục chạy sau khi thêm quota";
            } else if (value.equals("9")) {
                sValue = "Kết thúc do hết thời gian";
            } else if (value.equals("10")) {
                sValue = "Chiến dịch kết thúc do đã gửi hết quota của chiến dịch";
            } else if (value.equals("99")) {
                sValue = "Kết thúc! Đã hoàn tin";
            } else if (value.equals("11")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-exclamation-triangle\"></i> Từ chối </font>";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String StatusSDT(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Hợp lệ";
            } else if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Không hợp lệ</font>";
            } else if (value.equals("2")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String StatusSEX(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "Nam";
            } else if (value.equals("1")) {
                sValue = "Nữ";
            } else if (value.equals("-1")) {
                sValue = "Khác";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusSEX");
            return "";
        }

    }

    public String StatusVSIGNmemberDetail(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Hoạt động";
            } else if (value.equals("2")) {
                sValue = "Hủy";
            } else if (value.equals("3")) {
                sValue = "Tạm dừng";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVSIGNmember");
            return "";
        }

    }

    public String StatusVSIGNmember(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Active";
            } else if (value.equals("0")) {
                sValue = "Hide";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVSIGNmember");
            return "";
        }

    }

    public String StatusComment(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Đã duyệt";
            } else if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ duyệt</font>";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            return "";
        }

    }

    public String StatusSupport(String value) {
        String sValue = "";
        try {
            if (value.equals("4")) {
                sValue = "Đã đóng";
            } else if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chưa xem</font>";
            } else if (value.equals("1")) {
                sValue = "<font style=\"color: red\">Đã xem</font>";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\">Đang xử lý...</font>";
            } else if (value.equals("3")) {
                sValue = "<font style=\"color: red\">Đã xử lý</font>";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            return "";
        }

    }

    public String StatusBlackWhiteList(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Hoạt động";
            } else if (value.equals("0")) {
                sValue = "Tạm dừng";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusBlackWhiteList");
            return "";
        }

    }

    public String CheckNull(String value) {
        String sValue = "";
        try {
            if (value == null) {
                sValue = "";
            } else if (value.equals("null")) {
                sValue = "";
            } else {
                sValue = value;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String CheckNullObjectKey(JSONObject obj, String Key) {
        String sValue = "";
        try {
            if (!obj.has(Key)) {
                sValue = "";
            } else if (obj.getString(Key) == null) {
                sValue = "";
            } else {
                sValue = obj.getString(Key);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String getSplit(String value, String ssplit) {
        String sValue = "";
        try {
            String[] arr = value.split(ssplit);
            if (arr.length > 0) {
                sValue = arr[0];
            } else {
                sValue = value;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String getSplitCP(String value) {
        String sValue = "";
        try {
            value = value + "";
            //System.out.println(value);
            String[] arr = value.split("\\|");
            int idem = 0;
            for (String a : arr) {
                idem += 1;
                if (idem == 1) {
                    sValue = a;
                }
                // System.out.println("=" + a);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String SetTimeVideo(String value) {
        String sValue = "00:00";
        try {
            if (value != null && !value.equals("")) {
                int igiay = Integer.parseInt(value);
                if (igiay > 0) {
                    int h = igiay / 3600;
                    int m = igiay % 3600 / 60;
                    int s = igiay % 60;
                    if (h == 0) {
                        sValue = m + ":" + s;
                    } else {
                        sValue = h + ":" + m + ":" + s;
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String CutTimeDay(String value) {
        String sValue = "";
        try {
            sValue = value.substring(0, 10);

            sValue = sValue.replaceAll("-", "/");

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String CutNewTimeDay(String value) {
        String sValue = "";
        try {
            sValue = value.substring(11, 16) + " | " + value.substring(0, 10);

            sValue = sValue.replaceAll("-", "/");

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String CutDDMM(String value) {
        String sValue = "";
        try {
            sValue = value.substring(0, 5);
            sValue = sValue.replaceAll("-", "/");

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String CutHH24MI(String value) {
        String sValue = "";
        try {
            sValue = value.substring(11, 16);
            sValue = sValue.trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String CutddMMyyyy(String value) {
        String sValue = "";
        try {
            sValue = value.substring(0, 10);
            sValue = sValue.replaceAll("-", "/").trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String CutThu(String value) {
        String sValue = "";
        try {
            sValue = value.substring(10, value.length());
            sValue = sValue.trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String CutThuName(String value) {
        String sValue = "";
        try {
            if (value.equals("Monday")) {
                sValue = "Thứ 2";
            } else if (value.equals("Tuesday")) {
                sValue = "Thứ 3";
            } else if (value.equals("Wednesday")) {
                sValue = "Thứ 4";
            } else if (value.equals("Thursday")) {
                sValue = "Thứ 5";
            } else if (value.equals("Friday")) {
                sValue = "Thứ 6";
            } else if (value.equals("Saturday")) {
                sValue = "Thứ 7";
            } else if (value.equals("Sunday")) {
                sValue = "Chủ nhật";
            } else {
                sValue = value;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sValue;
    }

    public String CheckNullInt(String value) {
        String sValue = "0";
        try {
            if (value == null) {
                sValue = "0";
            } else if (value.equals("null")) {
                sValue = "";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            return "0";
        }

    }

    public String Status_vSign(String value) {
        String sValue = "";
        try {
            if (value == null) {
                sValue = "";
            } else if (value.equals("1")) {
                sValue = "<span class=\"oi oi-eye\"></span>";
            } else if (value.equals("0")) {
                sValue = " <span class=\"oi oi-lock-locked\"></span>";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            return "";
        }

    }

    public String Status_vSign_Text(String value) {
        String sValue = "";
        try {
            if (value == null) {
                sValue = "";
            } else if (value.equals("1")) {
                sValue = "Hiện";
            } else if (value.equals("0")) {
                sValue = "Ẩn";
            } else if (value.equals("2")) {
                sValue = "Chờ duyệt";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            return "";
        }

    }

    public String Status_vSign_key(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "V";
            } else if (value.equals("1")) {
                sValue = "E";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror Status_vSign_key");
            return "";
        }

    }

    public String Status_vSign_Action(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "thất bại";
            } else if (value.equals("1")) {
                sValue = "thành công";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror Status_vSign_key");
            return "";
        }

    }

    public String Status_vSign_Camkey(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "Không cấm";
            } else if (value.equals("1")) {
                sValue = "Cấm";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror Status_vSign_Camkey");
            return "";
        }

    }

    public String StatusVMContent(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ duyệt...</font>";
            } else if (value.equals("1")) {
                sValue = "Đã duyệt";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-exclamation-triangle\"></i> Ẩn </font>";
            } else if (value.equals("3")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-exclamation-triangle\"></i> Từ chối </font>";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String StatusVMViewPopular(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Ẩn</font>";
            } else if (value.equals("1")) {
                sValue = "Hiện";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMViewPopular");
            return "";
        }

    }

    public String StatusVMAlbum(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ duyệt</font>";
            } else if (value.equals("1")) {
                sValue = "Đã duyệt";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\">Ẩn</font>";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMViewPopular");
            return "";
        }

    }

    public String StatusNotify(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Đang gửi</font>";
            } else if (value.equals("1")) {
                sValue = "Đã gửi";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\">Chờ duyệt</font>";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMViewPopular");
            return "";
        }

    }

    public String StatusVMUser(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ duyệt</font>";
            } else if (value.equals("1")) {
                sValue = "Đã duyệt";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\">Ẩn</font>";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMUser");
            return "";
        }

    }

    public String StatusMoMt(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">thất bại</font>";
            } else if (value.equals("1")) {
                sValue = "Thành công";
            } else {
                sValue = value;
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMUser");
            return "";
        }

    }

    public String StatusVMBanner(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ duyệt</font>";
            } else if (value.equals("1")) {
                sValue = "Đã duyệt";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\">Ẩn</font>";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMUser");
            return "";
        }

    }

    public String StatusVMArstist(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ duyệt</font>";
            } else if (value.equals("1")) {
                sValue = "Đã duyệt";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\">Ẩn</font>";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMViewPopular");
            return "";
        }

    }

    public String StatusVMGenre(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Ẩn</font>";
            } else if (value.equals("1")) {
                sValue = "Hiện";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMViewPopular");
            return "";
        }

    }

    public String StatusVMSubject(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Ẩn</font>";
            } else if (value.equals("1")) {
                sValue = "Hiện";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMViewPopular");
            return "";
        }

    }

    public String StatusVMNgay(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Ngày";
            } else if (value.equals("2")) {
                sValue = "Tuần";
            } else if (value.equals("3")) {
                sValue = "Tháng";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String StatusVMArtist(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Ẩn</font>";
            } else if (value.equals("1")) {
                sValue = "Hiện";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMViewPopular");
            return "";
        }

    }

    public String StatusVMContentLink(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ đồng bộ</font>";
            } else if (value.equals("1")) {
                sValue = "Đã đồng bộ CDN";
            } else if (value.equals("-1")) {
                sValue = "Đã xóa";
            } else if (value.equals("2")) {
                sValue = "<font style=\"color: red\">CDN Lỗi </font>";
            } else {
                sValue = "<font style=\"color: red\">Lỗi </font>";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMViewPopular");
            return "";
        }

    }

    public String StatusVMContentType(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<span class=\"badge badge-subtle badge-success\"> <i class=\"oi oi-pulse\"></i> Audio</span>";
            } else if (value.equals("1")) {
                sValue = "<span class=\"badge badge-subtle badge-primary\"><i class=\"fa fa-play text-teal\"></i> Video</span>";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusVMContentType");
            return "";
        }

    }

    public String StatusDongBo(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Đã đồng bộ";
            } else if (value.equals("0")) {
                sValue = "Chờ đồng bộ...";
            } else if (value.equals("2")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String StatusContent(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "<font style=\"color: red\">Chờ duyệt...</font>";
            } else if (value.equals("1")) {
                sValue = "Đã duyệt";
            } else if (value.equals("2")) {
                sValue = "Đã xóa";
            } else if (value.equals("3")) {
                sValue = "<font style=\"color: red\"><i class=\"fa fa-exclamation-triangle\"></i> Từ chối </font>";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusActive");
            return "";
        }

    }

    public String BrContent(String value) {
        String sValue = "";
        try {
            sValue = value.replace("\n", "<br>");
            return sValue;
        } catch (Exception e) {
            System.out.println("eror BrContent");
            return "";
        }

    }

    public String StatusUssdSendLog(String value) {
        String sValue = "";
        try {
            if (value.equals("0")) {
                sValue = "Thành công";
            } else if (value.equals("27")) {
                sValue = "Thuê bao tắt máy hoặc ngoài vùng phủ sóng";
            } else if (value.equals("45")) {
                sValue = "Thuê bao đang bận, không thực hiện được giao";
            } else if (value.equals("34")) {
                sValue = "HLR báo lỗi giao dịch không thành công";
            } else if (value.equals("72")) {
                sValue = "Phiên USSD đang bận ( khách hàng đang thực hiện 1 phiên ussd khác)";
            } else if (value.equals("301")) {
                sValue = "Bản tin bị reject trên HLR";
            } else if (value.equals("303")) {
                sValue = "Người dùng từ chối giao dịch";
            } else if (value.equals("305")) {
                sValue = "Giao dịch bị timeout";
            } else if (value.equals("307")) {
                sValue = "Chặn do nội dung không được phép gửi";
            } else if (value.equals("308")) {
                sValue = "Chặn do thuê bao không được phép gửi";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusUssdSendLog");
            return "";
        }

    }

    public String StatusMenu(String value) {
        String sValue = "";
        try {
            if (value.equals("1")) {
                sValue = "Hiện";
            } else if (value.equals("0")) {
                sValue = "Ẩn";
            } else if (value.equals("2")) {
                sValue = "Đã xóa";
            }
            return sValue;
        } catch (Exception e) {
            System.out.println("eror StatusMenu");
            return "";
        }

    }

    public String AlertLocation(String value, String link) {
        String sValue = "";
        try {
            sValue = "<script>alert('" + value + "'); location='" + link + "';</script>";
            return sValue;
        } catch (Exception e) {
            System.out.println("eror AlertLocation");
            return "";
        }

    }

    public int CountSDT(String value) {
        try {
            int iValue = value.split(",").length;
            return iValue;
        } catch (Exception e) {
            System.out.println("eror CountSDT");
            return 0;
        }

    }

    public int CheckEmailValidator(String value) {
        try {
            String mydomain = value;
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Boolean b = mydomain.matches(emailregex);

            if (b == false) {
                return -1;
            } else if (b == true) {
                System.out.println("Email Address is Valid");
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static boolean isNumberic(String sNumber) {
        if (sNumber == null || "".equals(sNumber)) {
            return false;
        }
        for (int i = 0; i < sNumber.length(); i++) {
            char ch = sNumber.charAt(i);
            char ch_max = (char) 0x39;
            char ch_min = (char) 0x30;
            if ((ch < ch_min) || (ch > ch_max)) {
                return false;
            }
        }
        return true;
    }

    public String SendMailUrl(String strFrom, String strTo, String strCC, String strSubject, String strContent) {
        String strDomainEmail = "http://email.vnptads.com/";
        readStrContentLink(strDomainEmail + "?strFrom=" + strFrom + "&strTo=" + strTo + "&strCC=" + strCC + "&strSubject=" + strSubject + "&strContent=" + strContent + "");
        return "";
    }

    public String GetURLContent(String strUrl) {
        String Values = "";
        URL url;
        try {
            // get URL content
            url = new URL(strUrl);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;

            //save to this filename
            String fileName = "/users/mkyong/test.html";
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            //use FileWriter to write file
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            while ((inputLine = br.readLine()) != null) {
                bw.write(inputLine);
            }

            bw.close();
            br.close();

            System.out.println("Done");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Values;
    }

    public String readStrContentLink(String strUrl) {
        // System.out.println("readStrContentLink => " + strUrl);
        URLConnection conn = null;
        String strContentLink = "";
        String thisLine = "";
        InputStreamReader is = null;
        try {
            URL strurl_acount = new URL(strUrl);
            conn = strurl_acount.openConnection();
            conn.setConnectTimeout(500);
            is = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader theHTML_acount = new BufferedReader(is);
            while ((thisLine = theHTML_acount.readLine()) != null) {
                strContentLink += thisLine;
            }

            theHTML_acount.close();
            is.close();

        } catch (IOException e) {
            strContentLink = "";
        }
        //System.out.println("Send mail Done");
        return strContentLink;
    }
    
    public String readStrContentAuthen(String strUrl, Map<String, String> headers) {
        URLConnection conn = null;
        String strContentLink = "";
        String thisLine = "";
        InputStreamReader is = null;
        try {
            URL url = new URL(strUrl);
            conn = url.openConnection();
            conn.setConnectTimeout(500);
            
            // Truyền các headers vào request
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            
            is = new InputStreamReader(conn.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(is);
            while ((thisLine = reader.readLine()) != null) {
                strContentLink += thisLine;
            }

            reader.close();
            is.close();

        } catch (IOException e) {
            strContentLink = "";
        }
        return strContentLink;
    }

    @Resource
    WebServiceContext wsCtx;

    public String getClientIP() {
        String clientIP = "-1";

        if (wsCtx != null) {
            MessageContext msgCtxt = wsCtx.getMessageContext();
            //HttpServletRequest req = (HttpServletRequest) msgCtxt.get(MessageContext.SERVLET_REQUEST);
            clientIP = "";//req.getRemoteAddr();
        }
        System.out.println("clientIP:" + clientIP);
        return clientIP;
    }

    public String getClientIP2() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                int i = 1;
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    // ip = addr.getHostAddress();                    
                    // System.out.println(iface.getDisplayName() + " " + ip);
                    if (i == 1) {
                        ip = addr.getHostAddress();
                    }
                    i = i + 1;
                }

            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        System.out.println("clientIP2:" + ip);
        return ip;
    }

    public static boolean isValidDestAddress(String userId) {
        if (userId == null || "".equals(userId)) {
            return false;
        }

        userId = userId.replaceAll("O", "0");

        if (((userId.startsWith("90") || userId.startsWith("91")
                || userId.startsWith("98") || userId.startsWith("97")
                || userId.startsWith("96") || userId.startsWith("93")
                || userId.startsWith("94") || userId.startsWith("95")
                || userId.startsWith("92") || userId.startsWith("86")
                || userId.startsWith("88") || userId.startsWith("89"))
                && userId.length() == 9)
                || ((userId.startsWith("090") || userId.startsWith("091")
                || userId.startsWith("098") || userId.startsWith("097")
                || userId.startsWith("096") || userId.startsWith("093")
                || userId.startsWith("094") || userId.startsWith("095")
                || userId.startsWith("092") || userId.startsWith("086")
                || userId.startsWith("088") || userId.startsWith("089"))
                && userId.length() == 10)
                || ((userId.startsWith("8490") || userId.startsWith("8491")
                || userId.startsWith("8498") || userId.startsWith("8497")
                || userId.startsWith("8496") || userId.startsWith("8486")
                || userId.startsWith("8488") || userId.startsWith("8489")
                || userId.startsWith("8493") || userId.startsWith("8494")
                || userId.startsWith("8495") || userId.startsWith("8492")
                || userId.startsWith("0168") || userId.startsWith("0169")
                || userId.startsWith("0165") || userId.startsWith("0164")
                || userId.startsWith("0123") || userId.startsWith("0122")
                || userId.startsWith("0125") || userId.startsWith("0121")
                || userId.startsWith("0128") || userId.startsWith("0127")
                || userId.startsWith("0129")
                || userId.startsWith("0167")
                || userId.startsWith("0126") || userId.startsWith("0166"))
                && userId.length() == 11)
                || ((userId.startsWith("84168") || userId.startsWith("84169")
                || userId.startsWith("84165") || userId.startsWith("84164")
                || userId.startsWith("84123") || userId.startsWith("84122")
                || userId.startsWith("84125") || userId.startsWith("84121")
                || userId.startsWith("84128") || userId.startsWith("84127")
                || userId.startsWith("84129")
                || userId.startsWith("84126") || userId.startsWith("84167")
                || userId.startsWith("84166"))
                && userId.length() == 12)) {

            return true;

        } else {

            return false;

        }

    }

    //Format SQL Query funtion
    public String formatSQLInput(String strInputEntry) {
        String formatSQLInput = "";
        if (formatSQLInput != null) {
            strInputEntry = strInputEntry.replace("", "");
            strInputEntry = strInputEntry.replace("=", "&#061;");
            strInputEntry = strInputEntry.replace("'", "''");
            strInputEntry = strInputEntry.replace("select", "sel&#101;ct");
            strInputEntry = strInputEntry.replace("join", "jo&#105;n");
            strInputEntry = strInputEntry.replace("union", "un&#105;on");
            strInputEntry = strInputEntry.replace("where", "wh&#101;re");
            strInputEntry = strInputEntry.replace("insert", "ins&#101;rt");
            strInputEntry = strInputEntry.replace("delete", "del&#101;te");
            strInputEntry = strInputEntry.replace("update", "up&#100;ate");
            strInputEntry = strInputEntry.replace("like", "lik&#101;");
            strInputEntry = strInputEntry.replace("drop", "dro&#112;");
            strInputEntry = strInputEntry.replace("create", "cr&#101;ate");
            strInputEntry = strInputEntry.replace("modify", "mod&#105;fy");
            strInputEntry = strInputEntry.replace("rename", "ren&#097;me");
            strInputEntry = strInputEntry.replace("alter", "alt&#101;r");
            strInputEntry = strInputEntry.replace("cast", "ca&#115;t");
            strInputEntry = strInputEntry.replace("form", "");
        }
        return strInputEntry;
    }

    public String formatInputInt(String strInputEntry) {
        strInputEntry = formatInput(strInputEntry);
        strInputEntry = strInputEntry.replaceAll("[^0-9]", "");
        return strInputEntry;
    }

    public String formatInputIntAz(String strInputEntry) {
        strInputEntry = formatInput(strInputEntry);
        strInputEntry = strInputEntry.replaceAll("[^0-9a-zA-Z]", "");
        return strInputEntry;
    }

    public String formatInputIntAzUrl(String strInputEntry) {
        strInputEntry = formatInput(strInputEntry);
        strInputEntry = strInputEntry.replaceAll("[^0-9a-zA-Z-_]", "");
        return strInputEntry;
    }

    public String formatInputDate(String strInputEntry) {
        strInputEntry = formatInput(strInputEntry);
        strInputEntry = strInputEntry.replaceAll("[^0-9-/]", "");
        return strInputEntry;
    }

    public String formatInputSearch(String strInputEntry) {
        // strInputEntry = formatInput(strInputEntry);
        strInputEntry = strInputEntry.replaceAll("[()'\"]", "");
        return strInputEntry;
    }

    public String formatInputComment(String strInputEntry) {
        strInputEntry = strInputEntry.replaceAll("[()'\"]", "");
        return strInputEntry;
    }

    public String formatInputContent(String strInputEntry) {
        strInputEntry = strInputEntry.trim();
        byte[] bytes = strInputEntry.getBytes(StandardCharsets.ISO_8859_1);
        strInputEntry = new String(bytes, StandardCharsets.UTF_8);
        if (strInputEntry != null) {
            //  strInputEntry = strInputEntry.replace("<", "&lt;");
            //strInputEntry = strInputEntry.replace(">", "&gt;");
            //  strInputEntry = strInputEntry.replace("'", "&#146;");

        }
        return strInputEntry;

    }

    public String formatInput(String strInputEntry) {
        strInputEntry = strInputEntry.trim();
        byte[] bytes = strInputEntry.getBytes(StandardCharsets.ISO_8859_1);
        strInputEntry = new String(bytes, StandardCharsets.UTF_8);
        if (strInputEntry != null) {
            strInputEntry = strInputEntry.replace("<", "&lt;");
            strInputEntry = strInputEntry.replace(">", "&gt;");
            strInputEntry = strInputEntry.replace("'", "&#146;");
            strInputEntry = strInputEntry.replace("script", "&#115;cript");
            strInputEntry = strInputEntry.replace("SCRIPT", "&#083;CRIPT");
            strInputEntry = strInputEntry.replace("Script", "&#083;cript");
            strInputEntry = strInputEntry.replace("script", "&#083;cript");
            strInputEntry = strInputEntry.replace("object", "&#111;bject");
            strInputEntry = strInputEntry.replace("OBJECT", "&#079;BJECT");
            strInputEntry = strInputEntry.replace("Object", "&#079;bject");
            strInputEntry = strInputEntry.replace("object", "&#079;bject");
            strInputEntry = strInputEntry.replace("applet", "&#097;pplet");
            strInputEntry = strInputEntry.replace("APPLET", "&#065;PPLET");
            strInputEntry = strInputEntry.replace("Applet", "&#065;pplet");
            strInputEntry = strInputEntry.replace("applet", "&#065;pplet");
            strInputEntry = strInputEntry.replace("embed", "&#101;mbed");
            strInputEntry = strInputEntry.replace("EMBED", "&#069;MBED");
            strInputEntry = strInputEntry.replace("Embed", "&#069;mbed");
            strInputEntry = strInputEntry.replace("embed", "&#069;mbed");
            strInputEntry = strInputEntry.replace("event", "&#101;vent");
            strInputEntry = strInputEntry.replace("EVENT", "&#069;VENT");
            strInputEntry = strInputEntry.replace("Event", "&#069;vent");
            strInputEntry = strInputEntry.replace("event", "&#069;vent");
            strInputEntry = strInputEntry.replace("document", "&#100;ocument");
            strInputEntry = strInputEntry.replace("DOCUMENT", "&#068;OCUMENT");
            strInputEntry = strInputEntry.replace("Document", "&#068;ocument");
            strInputEntry = strInputEntry.replace("document", "&#068;ocument");
            strInputEntry = strInputEntry.replace("cookie", "&#099;ookie");
            strInputEntry = strInputEntry.replace("COOKIE", "&#067;OOKIE");
            strInputEntry = strInputEntry.replace("Cookie", "&#067;ookie");
            strInputEntry = strInputEntry.replace("cookie", "&#067;ookie");
            strInputEntry = strInputEntry.replace("form", "&#102;orm");
            strInputEntry = strInputEntry.replace("FORM", "&#070;ORM");
            strInputEntry = strInputEntry.replace("Form", "&#070;orm");
            strInputEntry = strInputEntry.replace("form", "&#070;orm");
            strInputEntry = strInputEntry.replace("iframe", "i&#102;rame");
            strInputEntry = strInputEntry.replace("IFRAME", "I&#070;RAME");
            strInputEntry = strInputEntry.replace("Iframe", "I&#102;rame");
            strInputEntry = strInputEntry.replace("iframe", "i&#102;rame");
            strInputEntry = strInputEntry.replace("textarea", "&#116;extarea");
            strInputEntry = strInputEntry.replace("TEXTAREA", "&#84;EXTAREA");
            strInputEntry = strInputEntry.replace("Textarea", "&#84;extarea");
            strInputEntry = strInputEntry.replace("textarea", "&#84;extarea");
            strInputEntry = strInputEntry.replace("input", "&#073;nput");
            strInputEntry = strInputEntry.replace("Input", "&#073;nput");
            strInputEntry = strInputEntry.replace("INPUT", "&#073;nput");
            strInputEntry = strInputEntry.replace("input", "&#073;nput"); //Reformat a few bits
            strInputEntry = strInputEntry.replace("<STR&#079;NG>", "<strong>");
            strInputEntry = strInputEntry.replace("<str&#111;ng>", "<strong>");
            strInputEntry = strInputEntry.replace("</STR&#079;NG>", "</strong>");
            strInputEntry = strInputEntry.replace("</str&#111;ng>", "</strong>");
            strInputEntry = strInputEntry.replace("f&#111;nt", "font");
            strInputEntry = strInputEntry.replace("F&#079;NT", "FONT");
            strInputEntry = strInputEntry.replace("F&#111;nt", "Font");
            strInputEntry = strInputEntry.replace("f&#079;nt", "font");
            strInputEntry = strInputEntry.replace("f&#111;nt", "font");
            strInputEntry = strInputEntry.replace("m&#111;no", "mono");
            strInputEntry = strInputEntry.replace("M&#079;NO", "MONO");
            strInputEntry = strInputEntry.replace("M&#079;no", "Mono");
            strInputEntry = strInputEntry.replace("m&#079;no", "mono");
            strInputEntry = strInputEntry.replace("m&#111;no", "mono");
        }
        return strInputEntry;

    }

    public String BoDau(String org) {
        org = org.trim();
        org = convertStringRemoveVn(org);
        String strUrl = BoDauTiengViet(org).replaceAll(" ", "-").toLowerCase();

        try {
            strUrl = strUrl.trim();
            strUrl = strUrl.replaceAll("[()|~`!@#$%^&*_=+{}/;:'.,?]", "");
            strUrl = strUrl.replaceAll("---", "-");
            strUrl = strUrl.replaceAll("]", "");
            strUrl = strUrl.replaceAll("\\[", "");
            strUrl = strUrl.replaceAll("--", "-");
            strUrl = strUrl.replaceAll("-", " ");
        } catch (Exception e) {
        }

        return strUrl;
    }

    public String BoDauKey(String org) {
        org = org.trim();
        org = convertStringRemoveVn(org);
        String strUrl = BoDauTiengViet(org).replaceAll(" ", "-").toLowerCase();

        try {
            strUrl = strUrl.trim();
            //strUrl = strUrl.replaceAll("[()|~`!@#$%^&*_=+{}/;:'.,?]", "");
//            strUrl = strUrl.replaceAll("---", "-");
//            strUrl = strUrl.replaceAll("]", "");
//            strUrl = strUrl.replaceAll("\\[", "");
            strUrl = strUrl.replaceAll("--", "-");
            strUrl = strUrl.replaceAll("-", " ");
        } catch (Exception e) {
        }

        return strUrl;
    }

    public static String BoDauTiengViet(String org) {
        char arrChar[] = org.toCharArray();
        char result[] = new char[arrChar.length];
        for (int i = 0; i < arrChar.length; i++) {
            switch (arrChar[i]) {
                case '\u00E1':
                case '\u00E0':
                case '\u1EA3':
                case '\u00E3':
                case '\u1EA1':
                case '\u0103':
                case '\u1EAF':
                case '\u1EB1':
                case '\u1EB3':
                case '\u1EB5':
                case '\u1EB7':
                case '\u00E2':
                case '\u1EA5':
                case '\u1EA7':
                case '\u1EA9':
                case '\u1EAB':
                case '\u1EAD':
                case '\u0203':
                case '\u01CE': {
                    result[i] = 'a';
                    break;
                }
                case '\u00E9':
                case '\u00E8':
                case '\u1EBB':
                case '\u1EBD':
                case '\u1EB9':
                case '\u00EA':
                case '\u1EBF':
                case '\u1EC1':
                case '\u1EC3':
                case '\u1EC5':
                case '\u1EC7':
                case '\u0207': {
                    result[i] = 'e';
                    break;
                }
                case '\u00ED':
                case '\u00EC':
                case '\u1EC9':
                case '\u0129':
                case '\u1ECB': {
                    result[i] = 'i';
                    break;
                }
                case '\u00F3':
                case '\u00F2':
                case '\u1ECF':
                case '\u00F5':
                case '\u1ECD':
                case '\u00F4':
                case '\u1ED1':
                case '\u1ED3':
                case '\u1ED5':
                case '\u1ED7':
                case '\u1ED9':
                case '\u01A1':
                case '\u1EDB':
                case '\u1EDD':
                case '\u1EDF':
                case '\u1EE1':
                case '\u1EE3':
                case '\u020F': {
                    result[i] = 'o';
                    break;
                }
                case '\u00FA':
                case '\u00F9':
                case '\u1EE7':
                case '\u0169':
                case '\u1EE5':
                case '\u01B0':
                case '\u1EE9':
                case '\u1EEB':
                case '\u1EED':
                case '\u1EEF':
                case '\u1EF1': {
                    result[i] = 'u';
                    break;
                }
                case '\u00FD':
                case '\u1EF3':
                case '\u1EF7':
                case '\u1EF9':
                case '\u1EF5': {
                    result[i] = 'y';
                    break;
                }
                case '\u0111': {
                    result[i] = 'd';
                    break;
                }
                case '\u00C1':
                case '\u00C0':
                case '\u1EA2':
                case '\u00C3':
                case '\u1EA0':
                case '\u0102':
                case '\u1EAE':
                case '\u1EB0':
                case '\u1EB2':
                case '\u1EB4':
                case '\u1EB6':
                case '\u00C2':
                case '\u1EA4':
                case '\u1EA6':
                case '\u1EA8':
                case '\u1EAA':
                case '\u1EAC':
                case '\u0202':
                case '\u01CD': {
                    result[i] = 'A';
                    break;
                }
                case '\u00C9':
                case '\u00C8':
                case '\u1EBA':
                case '\u1EBC':
                case '\u1EB8':
                case '\u00CA':
                case '\u1EBE':
                case '\u1EC0':
                case '\u1EC2':
                case '\u1EC4':
                case '\u1EC6':
                case '\u0206': {
                    result[i] = 'E';
                    break;
                }
                case '\u00CD':
                case '\u00CC':
                case '\u1EC8':
                case '\u0128':
                case '\u1ECA': {
                    result[i] = 'I';
                    break;
                }
                case '\u00D3':
                case '\u00D2':
                case '\u1ECE':
                case '\u00D5':
                case '\u1ECC':
                case '\u00D4':
                case '\u1ED0':
                case '\u1ED2':
                case '\u1ED4':
                case '\u1ED6':
                case '\u1ED8':
                case '\u01A0':
                case '\u1EDA':
                case '\u1EDC':
                case '\u1EDE':
                case '\u1EE0':
                case '\u1EE2':
                case '\u020E': {
                    result[i] = 'O';
                    break;
                }
                case '\u00DA':
                case '\u00D9':
                case '\u1EE6':
                case '\u0168':
                case '\u1EE4':
                case '\u01AF':
                case '\u1EE8':
                case '\u1EEA':
                case '\u1EEC':
                case '\u1EEE':
                case '\u1EF0': {
                    result[i] = 'U';
                    break;
                }

                case '\u00DD':
                case '\u1EF2':
                case '\u1EF6':
                case '\u1EF8':
                case '\u1EF4': {
                    result[i] = 'Y';
                    break;
                }
                case '\u0110':
                case '\u00D0':
                case '\u0089': {
                    result[i] = 'D';
                    break;
                }
                default:
                    result[i] = arrChar[i];
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            //Go dau trong TH vi du Còn Yêu Thì Cứ Yêu
            // >= 768 => ky tu dau trong unicode, Range U+0300..U+036F
            if (result[i] < 768) {
                stringBuilder.append(result[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static String convertStringRemoveVn(String oldStr) {
        String newStr = "";
        String aStr = "\u00E0\u00E1\u00E2\u00E3\u0103\u1EA1\u1EA3\u1EA5\u1EA7\u1EA9\u1EAB\u1EAD\u1EAF\u1EB1\u1EB3\u1EB5\u1EB7";
        String aUpStr = "\u00C0\u00C1\u00C2\u00C3\u1EA0\u0102\u1EA2\u1EA4\u1EA6\u1EA8\u1EAA\u1EAC\u1EAE\u1EB0\u1EB2\u1EB4\u1EB6";
        String eStr
                = "\u00E8\u00E9\u00EA\u1EB9\u1EBB\u1EBD\u1EBF\u1EC1\u1EC3\u1EC5\u1EC7";
        String eUpStr
                = "\u00C8\u00C9\u00CA\u1EB8\u1EBA\u1EBC\u1EBE\u1EC0\u1EC2\u1EC4\u1EC6";
        String oStr = "\u00F2\u00F3\u00F4\u00F5\u01A1\u1ECD\u1ECF\u1ED1\u1ED3\u1ED5\u1ED7\u1ED9\u1EDB\u1EDD\u1EDF\u1EE1\u1EE3";
        String oUpStr = "\u00D2\u00D3\u00D4\u00D5\u01A0\u1ECC\u1ECE\u1ED0\u1ED2\u1ED4\u1ED6\u1ED8\u1EDA\u1EDC\u1EDE\u1EE0\u1EE2";

        String iStr = "\u00EC\u00ED\u0129\u1EC9\u1ECB";
        String iUpStr = "\u00CC\u00CD\u0128\u1EC8\u1ECA";
        String yStr = "\u00FD\u1EF3\u1EF5\u1EF7\u1EF9";
        String yUpStr = "\u00DD\u1EF2\u1EF4\u1EF6\u1EF8";
        String uStr
                = "\u00F9\u00FA\u0169\u01B0\u1EE5\u1EE7\u1EE9\u1EEB\u1EED\u1EEF\u1EF1";
        String uUpStr
                = "\u00D9\u00DA\u0168\u01AF\u1EE4\u1EE6\u1EE8\u1EEA\u1EEC\u1EEE\u1EF0";
        String dStr = "\u0111";
        String dUpStr = "\u0110";

        if ((oldStr == null) || (oldStr.trim().equals(""))) {
            return newStr;
        } else {
            for (int i = 0; i < oldStr.length(); i++) {
                char temp = oldStr.charAt(i);

                if (aStr.indexOf(temp) != -1) {

                    oldStr = oldStr.replace(temp, 'a');

                } else if (aUpStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'A');

                } else if (eStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'e');

                } else if (eUpStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'E');

                } else if (oStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'o');

                } else if (oUpStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'O');

                } else if (iStr.indexOf(temp) != -1) {

                    oldStr = oldStr.replace(temp, 'i');

                } else if (iUpStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'I');

                } else if (yStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'y');

                } else if (yUpStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'Y');

                } else if (uStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'u');

                } else if (uUpStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'U');

                } else if (dStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'd');

                } else if (dUpStr.indexOf(temp) != -1) {
                    oldStr = oldStr.replace(temp, 'D');

                }

            }
            newStr = oldStr;
        }

        return newStr;
    }

    public static String replaceAllSep(String info) {
        try {

            if (info != null && !info.equals("")) {
                byte[] bytes = info.getBytes(StandardCharsets.ISO_8859_1);
                info = new String(bytes, StandardCharsets.UTF_8);

                // info = info.replaceAll("'", " ");
                info = info.replaceAll("\"", "");
                info = info.replaceAll("\\\\", " ");
                info = info.replaceAll("\t", " ");
                info = info.replaceAll("\n", " ");
                info = info.replaceAll("\r", " ");
                info = info.replaceAll("\f", " ");
                info = info.replaceAll("\\\n", " ");
                info = info.replaceAll("\\n", " ");
                info = info.replaceAll("&", " ");
                info = info.replaceAll(">", " ");
                info = info.replaceAll("<", " ");
            }
            return info;
        } catch (Exception e) {
            System.out.println("eror replaceAllSep");
            return "";
        }

    }

    public static String FormatCauHoi(String info) {
        try {

            if (info != null && !info.equals("")) {
                byte[] bytes = info.getBytes(StandardCharsets.ISO_8859_1);
                info = new String(bytes, StandardCharsets.UTF_8);

                info = info.replaceAll("\"", "");
                // info = info.replaceAll("\\\\", "");
                //info = info.replaceAll("\t", " ");
                //info = info.replaceAll("\n", "\t");
                // info = info.replaceAll("\r", " ");
                // info = info.replaceAll("\f", " ");
                // info = info.replaceAll("\\\n", "");
                // info = info.replaceAll("\\n", "");
                info = info.replaceAll("&", "");
                info = info.replaceAll(">", "");
                info = info.replaceAll("<", "");
            }
            return info;
        } catch (Exception e) {
            System.out.println("eror replaceAllSep");
            return "";
        }

    }

    public static String FormatFormVN(String info) {
        try {

            if (info != null && !info.equals("")) {
                byte[] bytes = info.getBytes(StandardCharsets.ISO_8859_1);
                info = new String(bytes, StandardCharsets.UTF_8);
            }
            return info;
        } catch (Exception e) {
            System.out.println("eror FormatFormVN");
            return "";
        }

    }

    public static String ShowCauHoi(String info) {
        try {

            if (info != null && !info.equals("")) {
                byte[] bytes = info.getBytes(StandardCharsets.ISO_8859_1);
                info = new String(bytes, StandardCharsets.UTF_8);

                info = info.replaceAll("\t", "<br>");

            }
            return info;
        } catch (Exception e) {
            System.out.println("eror replaceAllSep");
            return "";
        }

    }

    public String FormatToVND_MinhHS(String Value) {
        String svalue = "";
        try {
            if (Value.length() <= 3) {
                svalue = Value;
            } else if (Value.length() > 3 && Value.length() <= 6) {
                svalue = Value.substring(0, Value.length() - 3) + "." + Value.substring(Value.length() - 3, Value.length());

            } else if (Value.length() == 7) {
                //4.000.000              
                svalue = Value.substring(0, 1) + "." + Value.substring(1, 4) + "." + Value.substring(4, Value.length());
            } else if (Value.length() == 8) {
                //40.000.000                
                svalue = Value.substring(0, 2) + "." + Value.substring(2, 5) + "." + Value.substring(5, Value.length());
            } else if (Value.length() == 9) {
                //400.000.000                
                svalue = Value.substring(0, 3) + "." + Value.substring(3, 6) + "." + Value.substring(6, Value.length());
            } else if (Value.length() == 10) {
                svalue = Value.substring(0, 1) + "." + Value.substring(1, 4) + "." + Value.substring(4, 7) + "." + Value.substring(7, Value.length());
            } else if (Value.length() == 11) {
                svalue = Value.substring(0, 2) + "." + Value.substring(2, 5) + "." + Value.substring(5, 8) + "." + Value.substring(8, Value.length());
            } else {
                svalue = Value;
            }
            return svalue;
        } catch (Exception ex) {
            return Value;
        }
    }

    //======================FormatToVND=======================
    public String FormatToVND(String sMoney) {
        if (sMoney != null && !sMoney.equals("")) {
            if (sMoney.startsWith("-")) {
                sMoney = sMoney.replace("-", "");
                return "-" + FormatTo3(sMoney);
            } else {
                return FormatTo3(sMoney);
            }
        } else {
            return "0";
        }
    }

    public String FormatTo3(String sMoney) {
        String sArgument = setArgument(sMoney);
        int iLeng = sArgument.length();
        int iCount = (iLeng - 1) / 3;
        String sTest = "";
        int id = iCount * 3;
        for (int i = 0; i < iCount; i++) {
            sTest += sArgument.substring(i * 3, (i * 3) + 3) + ",";
        }
        sTest += sArgument.substring(id, iLeng);
        String sReturn = setArgument(sTest);
        return sReturn;
    }

    public static String setArgument(String sBegin) {
        String sEnd = "";
        for (int i = sBegin.length() - 1; i >= 0; i--) {
            sEnd += sBegin.charAt(i);
        }
        return sEnd;
    }
    //======================FormatToVND=======================

    public String PostJsonCache(String data, String strUrl, int phut) {
        String strContent = "";
        try {
            // check cấu hình CACHE_FILE=1 là cho phép cache file web 
            if (CACHE_FILE.equals("1")) {
                cache_file cache = new cache_file();
                // khởi tạo tên file 
                String strFileCache = "cache_web/" + Encypt_MD5(strUrl + data) + ".txt";
                // check file với số phút cache
                int icheck_cache = cache.CacheFile_Check(strFileCache, phut);
                if (icheck_cache == 1) {
                    // không có file => thực hiện đọc API để lấy json dữ liệu 
                    strContent = readStrContentLink(strUrl + data);
                    // nếu đọc api không lỗi thì sẽ có json và thực hiện ghi file 
                    if (strContent.length() > 10) {
                        cache.CacheFile_Write(strFileCache, strContent);
                    }
                }
                // đọc dữ liệu từ file cache
                strContent = cache.CacheFile_Read(strFileCache);
            } else {
                // dọc dữ liệu từ API 
                strContent = readStrContentLink(strUrl + data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strContent;
    }
    
    public String PostJsonAuth(String data, String strUrl, int phut) {
        String strContent = "";
        Map<String, String> headers = new HashMap();
        headers.put("Authorization", "Bearer " + getAccessToken());
        headers.put("Content-Type", "application/json");
        try {
            // check cấu hình CACHE_FILE=1 là cho phép cache file web 
            if (CACHE_FILE.equals("1")) {
                cache_file cache = new cache_file();
                // khởi tạo tên file 
                String strFileCache = "cache_web/" + Encypt_MD5(strUrl + data) + ".txt";
                // check file với số phút cache
                int icheck_cache = cache.CacheFile_Check(strFileCache, phut);
                if (icheck_cache == 1) {
                    // không có file => thực hiện đọc API để lấy json dữ liệu 
                    strContent = readStrContentAuthen(strUrl + data, headers);
                    // nếu đọc api không lỗi thì sẽ có json và thực hiện ghi file 
                    if (strContent.length() > 10) {
                        cache.CacheFile_Write(strFileCache, strContent);
                    }
                }
                // đọc dữ liệu từ file cache
                strContent = cache.CacheFile_Read(strFileCache);
            } else {
                // dọc dữ liệu từ API 
                strContent = readStrContentAuthen(strUrl + data, headers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strContent;
    }

    public String PostJsonData(String apiUrl, String postData) {
        HttpClient httpClient = null;
        HttpPost post = null;
        InputStream inputStream = null;
        String responseStr = "";
        try {
            String postUrl = apiUrl;
            httpClient = HttpClientBuilder.create().build();
            post = new HttpPost(postUrl);

            StringEntity entity = new StringEntity(postData, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            post.addHeader("Accept", "application/json");
            post.addHeader("Content-Type", "application/json; charset=utf-8");

            HttpResponse response = httpClient.execute(post);

            int status = response.getStatusLine().getStatusCode();
            if (status != 200) {
                System.out.println("status != 200 ");
                return "";
            }

            HttpEntity entity2 = response.getEntity();

            if (entity2 != null) {
                String webpage = EntityUtils.toString(entity2, StandardCharsets.UTF_8);
                responseStr = webpage;
            }

            return responseStr;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }

    public String PostJson(String data, String strUrl) {

        String msisdn = getJsonstr1(data, "msisdn");
        String strToken = PostJsonGetToken(msisdn);

        String token = getJsonstr1(strToken, "token");
//        String token = PostJsonToken();

//        System.out.println("alert(---msisdn:" + msisdn + "----------)");
//        System.out.println("alert(---strUrl:" + strUrl + "----------)");
//        System.out.println("alert(---strToken:" + strToken + "----------)");
//        System.out.println("alert(---token:" + token + "----------)");
        HttpClient httpClient = null;
        HttpPost post = null;
        InputStream inputStream = null;
        String responseStr = "";
        try {
            String postUrl = strUrl;
            httpClient = HttpClientBuilder.create().build();
            post = new HttpPost(postUrl);

            StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);

            post.setEntity(entity);
            post.addHeader("Accept", "application/json");
            post.addHeader("Authorization", "Bearer " + token);
            post.addHeader("Content-Type", "application/json; charset=utf-8");

            HttpResponse response = httpClient.execute(post);

            int status = response.getStatusLine().getStatusCode();
            if (status != 200) {
                System.out.println("status != 200 ");
                return "";
            }

            HttpEntity entity2 = response.getEntity();

            if (entity2 != null) {
                String webpage = EntityUtils.toString(entity2, StandardCharsets.UTF_8);
                responseStr = webpage;
            }
            //  System.out.println("alert(---responseStr:" + responseStr + "----------)");
            return responseStr;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }

    public String PostJson2(String strUrl, String data) {

        HttpClient httpClient = null;
        HttpPost post = null;
        InputStream inputStream = null;
        String responseStr = "";
        try {
            String postUrl = strUrl;
            httpClient = HttpClientBuilder.create().build();
            post = new HttpPost(postUrl);

            StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);
            System.out.println("cgt.functions.Function.PostJson2(------strUrl:"+strUrl+"----data:"+data+":-------)");
            post.setEntity(entity);
            post.addHeader("Accept", "application/json");
            post.addHeader("Authorization", "Bearer " + getAccessToken());
            post.addHeader("Content-Type", "application/json; charset=utf-8");

            HttpResponse response = httpClient.execute(post);

            int status = response.getStatusLine().getStatusCode();
            if (status != 200) {
                System.out.println("status != 200---status:"+status+"------- ");
                return "";
            }

            HttpEntity entity2 = response.getEntity();

            if (entity2 != null) {
                String webpage = EntityUtils.toString(entity2, StandardCharsets.UTF_8);
                responseStr = webpage;
            }
              System.out.println("alert(---responseStr:" + responseStr + "----------)");
            return responseStr;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }

//    public static String encryptAES(String plainText) {
//        try {
//            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            SecretKeySpec mKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
//            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};
//            IvParameterSpec ivParameterSpec;
//            ivParameterSpec = new IvParameterSpec(iv);
//            AlgorithmParameterSpec mSpec = ivParameterSpec;
//            mCipher.init(Cipher.ENCRYPT_MODE, mKey, mSpec);
//            byte[] encrypted = mCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
//            return new String(Base64.getEncoder().encode(encrypted), StandardCharsets.UTF_8);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
    public String PostJsonGetToken(String msisdn) {

        ConfigInfoCache config = new ConfigInfoCache();
        String API_LINK = config.ConfigInfoCache("API_LINK");
        String SERVICE_ID = config.ConfigInfoCache("SERVICE_ID");

        msisdn = msisdn84(msisdn);

        String authenCode = encryptAES(msisdn + "|" + SERVICE_ID + "|vLiVe");

        String data = "{"
                + "\"msisdn\":\"" + msisdn + "\","
                + "\"serviceCode\":\"" + SERVICE_ID + "\","
                + "\"authenCode\":\"" + authenCode + "\""
                + "}";

        // System.out.println("vnpt.media.Function.PostJsonGetToken(-data:" + data + "---API_LINK:" + API_LINK + "-------------)");
        // System.out.println("vnpt.media.Function.PostJsonGetToken(-msisdn:" + msisdn + "---SERVICE_ID:" + SERVICE_ID + "-------authenCode:" + authenCode + "------------)");
        HttpClient httpClient = null;
        HttpPost post = null;
        InputStream inputStream = null;
        String responseStr = "";
        try {
            String postUrl = API_LINK + "get_token";
            httpClient = HttpClientBuilder.create().build();
            post = new HttpPost(postUrl);

            StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);

            post.setEntity(entity);
            post.addHeader("Accept", "application/json");
            post.addHeader("Content-Type", "application/json; charset=utf-8");

            HttpResponse response = httpClient.execute(post);

            int status = response.getStatusLine().getStatusCode();
            if (status != 200) {
                System.out.println("status != 200 ");
                return "";
            }

            HttpEntity entity2 = response.getEntity();

            if (entity2 != null) {
                String webpage = EntityUtils.toString(entity2, StandardCharsets.UTF_8);
                responseStr = webpage;
            }
            System.out.println("vnpt.media.Function.PostJsonGetToken(----responseStr:" + responseStr + "------------)");
            return responseStr;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseStr;
    }
    

    public String PostJsonToken(String msisdn, String service_id) {
        String token = "";
        try {
            String secureCode = msisdn + "|" + service_id + "|vLiVe";

            token = encryptAES(secureCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public String msisdn84(String msisdn) {
        String strValue = msisdn;
        try {
            if (msisdn.startsWith("0")) {
                strValue = "84" + msisdn.substring(1, msisdn.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strValue;
    }

    public String PostJsonTokenOld() {
        String token = "";
        String username = "vLiVe";
        String password = "9ViJHPeufzp3cY44Sx1XHpRQ38vtdHGsTapv/5KFE9Q=";
        String requestId = getTimeID();
        String requestDate = requestDate("dd-MM-yyyy HH:mm:ss");
        try {
            String secureCode = username + "|" + password + "|" + requestId + "|" + requestDate;
            token = DigestUtils.sha256Hex(secureCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String encryptAES(String plainText) {
        try {
            String SECRET_KEY = "VNPTMedi@Chum3dv";
            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec mKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};
            IvParameterSpec ivParameterSpec;
            ivParameterSpec = new IvParameterSpec(iv);
            AlgorithmParameterSpec mSpec = ivParameterSpec;
            mCipher.init(Cipher.ENCRYPT_MODE, mKey, mSpec);
            byte[] encrypted = mCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(encrypted), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //=========================== get json ============================
    public String getJsonstr1(String strJson, String str1) {
        String strValues = "";
        try {
            JSONObject obj = new JSONObject(strJson);
            strValues = obj.getString(str1);
            return strValues;
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("eror getJsonstr1=====strJson:" + strJson + "==str1:" + str1 + "==========");
            return "";
        }
    }

    public String getJsonINT1(String strJson, String str1) {
        String strValues = "";
        try {
            JSONObject obj = new JSONObject(strJson);
            strValues = obj.getInt(str1) + "";
            return strValues;
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("eror getJsonINT1");
            return "";
        }
    }

    public String getJsonLong1(String strJson, String str1) {
        String strValues = "";
        try {
            JSONObject obj = new JSONObject(strJson);
            strValues = obj.getLong(str1) + "";
            return strValues;
        } catch (Exception e) {
            // e.printStackTrace();
            // System.out.println("eror getJsonstr====" + strJson + "======");
            return "";
        }
    }

    public String getJsonstr2(String strJson, String str1, String str2) {
        String strValues = "";
        try {
            JSONObject obj = new JSONObject(strJson);
            strValues = obj.getJSONObject(str1).getString(str2);
            return strValues;
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("eror====== getJsonstr2====" + strJson + "======");
            return "";
        }
    }

    public String getJsonInt2(String strJson, String str1, String str2) {
        String strValues = "";
        try {
            JSONObject obj = new JSONObject(strJson);
            strValues = obj.getJSONObject(str1).getInt(str2) + "";
            return strValues;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("eror getJsonstr");
            return "";
        }
    }

    public String getJsonBoolean2(String strJson, String str1, String str2) {
        String strValues = "";
        try {
            JSONObject obj = new JSONObject(strJson);
            strValues = obj.getJSONObject(str1).getBoolean(str2) + "";
            return strValues;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("eror getJsonstr");
            return "";
        }
    }
    
    //=========================== get json ============================
    public static void main(String[] args) {

    }

}
