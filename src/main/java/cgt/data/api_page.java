/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.data;

import cgt.log.log_save;
import cgt.functions.Function;
import cgt.resource.ConfigInfoCache;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Ho Sy Minh
 */
public class api_page {

    public String msisdn;
    public String serviceCode;
    public String errorCode;
    public String message;

    private List<String> ListCode;
    private List<String> ListTitle;

    Function cf = new Function();
    log_save ls = new log_save();
    ConfigInfoCache config = new ConfigInfoCache();
    private String API_CMS = config.ConfigInfoCache("API_CMS");

    public String GetPageTitle(String msisdn, String pageCode, String service_id, String pageId) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS;

            String strPostData = "api_page?action=list&service_id=" + service_id;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 5);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();
                if (dem > 0) {
                    for (int i = 0; i < dem; i++) {
                        String CODE = cf.CheckNull(arr.getJSONObject(i).getString("CODE"));
                        String ID = cf.CheckNull(arr.getJSONObject(i).getString("ID"));
                        if (CODE.equals(pageCode) && ID.equals(pageId)) {
                            String TITLE = cf.CheckNull(arr.getJSONObject(i).getString("TITLE"));
                            strValues += TITLE;
                        }

                    }
                }

            }
//            System.out.println("vnpt.data.api.api_page.GetPageInfo(---strPostData:" + strPostData + "--strValues:" + strValues + "----strJsonContent:" + strJsonContent + "-------)");
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

    public String GetPageCode(String msisdn, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS;

            String strPostData = "api_page?action=list&service_id=" + service_id;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 5);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();
                if (dem > 0) {
                    for (int i = 0; i < dem; i++) {
                        String CODE = cf.CheckNull(arr.getJSONObject(i).getString("CODE"));
                        String TITLE = cf.CheckNull(arr.getJSONObject(i).getString("TITLE"));
                        String ID = cf.CheckNull(arr.getJSONObject(i).getString("ID"));
                        String LinkMenu = "p-" + CODE + "-" + ID + ".html";
                        strValues += "<li><a href=\"" + LinkMenu + "\" class=\"text-nav-mobile\"><div class=\"uk-border-rounded txt-color pd-desktop\">" + TITLE + "</div></a></li>";
                    }
                }

            }
//            System.out.println("vnpt.data.api.api_page.GetPageInfo(---strPostData:" + strPostData + "--strValues:" + strValues + "----strJsonContent:" + strJsonContent + "-------)");
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }
        return strValues;
    }

    public String GetPageCodeFooter(String msisdn, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS;

            String strPostData = "api_page?action=list&service_id=" + service_id;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 5);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();
                if (dem > 0) {
                    for (int i = 0; i < dem; i++) {
                        String CODE = cf.CheckNull(arr.getJSONObject(i).getString("CODE"));
                        String TITLE = cf.CheckNull(arr.getJSONObject(i).getString("TITLE"));
                        String ID = cf.CheckNull(arr.getJSONObject(i).getString("ID"));
                        String LinkMenu = "p-" + CODE + "-" + ID + ".html";
                        strValues += "<li><a href=\"" + LinkMenu + "\" class=\"text-nav-mobile\"><div class=\"uk-border-rounded pd-footer\">" + TITLE + "</div></a></li>";
                    }
                }

            }
//            System.out.println("vnpt.data.api.api_page.GetPageInfo(---strPostData:" + strPostData + "--strValues:" + strValues + "----strJsonContent:" + strJsonContent + "-------)");
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }
        return strValues;
    }

    public String GetPageInfo(String msisdn, String pageCode, String service_id, String pageId) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS;

            String strPostData = "api_page?action=list&service_id=" + service_id;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 5);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();
                if (dem > 0) {
                    for (int i = 0; i < dem; i++) {
                        String CODE = cf.CheckNull(arr.getJSONObject(i).getString("CODE"));
                        String ID = cf.CheckNull(arr.getJSONObject(i).getString("ID"));
                        if (CODE.equals(pageCode) && ID.equals(pageId)) {
                            String TITLE = cf.CheckNull(arr.getJSONObject(i).getString("TITLE"));
                            String CONTENT = cf.CheckNull(arr.getJSONObject(i).getString("CONTENT"));

                            strValues += "<div class=\"uk-card uk-card-default uk-card-body\">\n"
                                    + "<div class=\"uk-text-center uk-animation-slide-top\" style=\"font-weight: bold\">\n"
                                    + "<h1>" + TITLE + " </h1>\n"
                                    + "</div>\n"
                                    + "\n"
                                    + "<div class=\"uk-animation-slide-bottom\">\n"
                                    + "" + CONTENT + "\n"
                                    + "</div> \n"
                                    + "</div>";
                        }

                    }
                }

            }
//            System.out.println("vnpt.data.api.api_page.GetPageInfo(---strPostData:" + strPostData + "--strValues:" + strValues + "----strJsonContent:" + strJsonContent + "-------)");
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }
        return strValues;
    }
}
