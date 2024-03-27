/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.data;

import org.json.JSONObject;
import cgt.log.log_save;
import cgt.functions.Function;
import cgt.resource.ConfigInfoCache;

/**
 *
 * @author Ho Sy Minh
 */
public class api_login {

    public String msisdn;
    public String serviceCode;
    public String errorCode;
    public String message;

    Function cf = new Function();
//    OracleDB db = new OracleDB();
    ConfigInfoCache config = new ConfigInfoCache();
    log_save ls = new log_save();

    private String API_LINK = config.ConfigInfoCache("API_LINK");
    private String API_CMS = config.ConfigInfoCache("API_CMS");
    private String SERVICE_ID = config.ConfigInfoCache("SERVICE_ID");

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //Gửi OTP đi
    public String GetOTP(String msisdn, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_otp";

            //Data truyền vào api
            String strPostData = "{"
                    + "\"phone_number\":\"" + msisdn + "\","
                    + "\"service_id\":\"" + service_id + "\","
                    + "\"action\":\"create_otp\""
                    + "}";
            strValues = fc.PostJson2(url_api, strPostData);
            strValues = cf.getJsonINT1(strValues, "errorCode");

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

    //Xử lý đăng nhập với OTP
    public String GetLoginOTP(String msisdn, String otp, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_otp";

            String strPostData = "{"
                    + "\"phone_number\":\"" + msisdn + "\","
                    + "\"service_id\":\"" + service_id + "\","
                    + "\"check_otp\":\"" + otp + "\","
                    + "\"action\":\"verify_otp\""
                    + "}";

            String strJsonContent = fc.PostJson2(url_api, strPostData);
            strValues = cf.getJsonINT1(strJsonContent, "errorCode");
//           System.out.println("vnpt.data.api.api_login.GetLoginOTP(---msisdn:"+msisdn+"------------strJsonContent:" + strJsonContent + "-------------)");
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

    //Đăng nhập bằng username, password
    public String GetLogin(String username, String password, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_login";
            //Data truyền vào API
            String strPostData = "{"
                    + "\"username\":\"" + username + "\","
                    + "\"service_id\":\"" + service_id + "\","
                    + "\"password\":\"" + password + "\""
                    + "}";
//            System.out.println("cgt.data.api_login.GetLogin(-----url_apiL:" + url_api + "-----strPostData:" + strPostData + "------)");
            String strJsonContent = fc.PostJson2(url_api, strPostData);
            if (strJsonContent.equals("")) {
                strValues = "0";
            } else {
                strValues = "1";
            }
//           System.out.println("vnpt.data.api.api_login.GetLogin(---msisdn:"+msisdn+"------------strJsonContent:" + strJsonContent + "-------------)");
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

    public String LienKetMXH(String msisdn, String typeSocial, String socialId) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_LINK + "user/link_social";

            String strPostData = "{"
                    + "\"msisdn\":\"" + msisdn + "\","
                    + "\"typeSocial\":" + typeSocial + ","
                    + "\"socialId\":\"" + socialId + "\""
                    + "}";
            String strContentJson = fc.PostJson(strPostData, url_api);
            strValues = cf.getJsonINT1(strContentJson, "errorCode");
//            System.out.println("vnpt.data.api.api_login.LienKetMXH(-----strPostData:" + strPostData + "--------strContentJson:" + strContentJson + "-----strValues:" + strValues + "----------)");
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

}
