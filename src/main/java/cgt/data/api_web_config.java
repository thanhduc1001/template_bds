/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.data;

import cgt.functions.Function;
import cgt.functions.Function_time;
import cgt.log.log_save;
import cgt.resource.ConfigInfoCache;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Admin
 */
public class api_web_config {

    Function cf = new Function();
    Function_time cft = new Function_time();
    log_save ls = new log_save();
    ConfigInfoCache config = new ConfigInfoCache();
    private String API_LINK = config.ConfigInfoCache("API_LINK");
    private String API_CMS = config.ConfigInfoCache("API_CMS");
//    private String SERVICE_ID = config.ConfigInfoCache("SERVICE_ID");
    private String TITLE;
    private String LOGO;
    private String SLIDE_TEXT;
    private String TEMPLATE_ID;
    private String FOOTER;
    private String IMG_BANNER;
    private String BASE_COLOR;
    private String H_PACKAGE_COLOR;

    public String getH_PACKAGE_COLOR() {
        return H_PACKAGE_COLOR;
    }

    public void setH_PACKAGE_COLOR(String H_PACKAGE_COLOR) {
        this.H_PACKAGE_COLOR = H_PACKAGE_COLOR;
    }

    public String getBASE_COLOR() {
        return BASE_COLOR;
    }

    public void setBASE_COLOR(String BASE_COLOR) {
        this.BASE_COLOR = BASE_COLOR;
    }

    public String getIMG_BANNER() {
        return IMG_BANNER;
    }

    public void setIMG_BANNER(String IMG_BANNER) {
        this.IMG_BANNER = IMG_BANNER;
    }

    public String getFOOTER() {
        return FOOTER;
    }

    public void setFOOTER(String FOOTER) {
        this.FOOTER = FOOTER;
    }

    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID;
    }

    public String getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(String SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }
    private String SERVICE_ID;

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getLOGO() {
        return LOGO;
    }

    public void setLOGO(String LOGO) {
        this.LOGO = LOGO;
    }

    public String getSLIDE_TEXT() {
        return SLIDE_TEXT;
    }

    public void setSLIDE_TEXT(String SLIDE_TEXT) {
        this.SLIDE_TEXT = SLIDE_TEXT;
    }

    public void SetConfig(String domain) {
        try {
            Function fc = new Function();
            String url_api = API_CMS;

            String strPostData = "api_web_info?action=list&domain=" + domain;
            String strJsonContent = fc.PostJsonCache(strPostData, url_api, 30);

            JSONObject obj = new JSONObject(strJsonContent);
            JSONArray arr = (JSONArray) obj.get("result");
            JSONObject arrObject = arr.getJSONObject(0);
//                this.setTEMPLATE_ID(cf.CheckNull(arr.getJSONObject(0).getString("TITLE")));
            this.setSERVICE_ID(cf.CheckNullObjectKey(obj, "service_id"));
            this.setTITLE(cf.CheckNullObjectKey(arrObject, "TITLE"));
            this.setLOGO(cf.CheckNullObjectKey(arrObject, "LOGO"));
            this.setSLIDE_TEXT(cf.CheckNullObjectKey(arrObject, "SLIDE_TEXT"));
            this.setFOOTER(cf.CheckNullObjectKey(arrObject, "FOOTER"));
            this.setIMG_BANNER(cf.CheckNullObjectKey(arrObject, "IMG_BANNER"));
            this.setBASE_COLOR(cf.CheckNullObjectKey(arrObject, "BASE_COLOR"));
            this.setH_PACKAGE_COLOR(cf.CheckNullObjectKey(arrObject, "H_PACKAGE_COLOR"));

        } catch (Exception e) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

    }

}
