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
public class api_package {

    public String msisdn;
    public String serviceCode;
    public String errorCode;
    public String message;

    Function cf = new Function();
    Function_time cft = new Function_time();
    log_save ls = new log_save();
    ConfigInfoCache config = new ConfigInfoCache();
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

    public String GetListPackV2(String cat, String service_id) {
        String strValues = "";
        Function fc = new Function();

        try {
            String url_api_2 = API_CMS + "api_package?action=list&service_id=" + service_id + "&cat=" + cat;
            String strJsonContent = fc.PostJsonAuth("", url_api_2, 30);

//                System.out.println("vnpt.data.api.api_home.GetListContentHome(=====strJsonContent:" + strJsonContent + "========)");
            if (!strJsonContent.equals("")) {

//                    System.out.println("vnpt.data.api.api_user.GetListPackV2()");
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr_2 = (JSONArray) obj.get("result");
                int dem = 0;

                strValues += "<div uk-slider>\n"
                        + "    <div class=\"uk-position-relative uk-visible-toggle uk-light\" tabindex=\"-1\">\n"
                        + "        <ul class=\"uk-slider-items uk-grid-20-m uk-child-width-1-1 uk-child-width-1-2@s uk-child-width-1-2@m\">\n"
                        + "       ";

                for (int j = 0; j < arr_2.length(); j++) {
                    String IDp = cf.CheckNull(arr_2.getJSONObject(j).getString("ID"));
                    String NAME_PACK = cf.CheckNull(arr_2.getJSONObject(j).getString("NAME"));
//                        String CATEGORY_ID = cf.CheckNull(arr_2.getJSONObject(j).getString("CATEGORY_ID"));
                    String INFO = cf.CheckNull(arr_2.getJSONObject(j).getString("INFO"));
                    String CMD = cf.CheckNull(arr_2.getJSONObject(j).getString("CMD"));
                    String DS = cf.CheckNull(arr_2.getJSONObject(j).getString("DS"));
                    String IMG_LINK = cf.CheckNull(arr_2.getJSONObject(j).getString("IMG_LINK"));
                    String CMD_UNREG = cf.CheckNull(arr_2.getJSONObject(j).getString("CMD_UNREG"));

//                        String strLinkImg = IMG_LINK;
                    dem += 1;
                    if (dem % 2 == 0) {
                    } else {
                        strValues += " <li class=\"rps-pack\">";
                    }

                    if (IMG_LINK.endsWith("images/") || IMG_LINK.equals("")) {
                        IMG_LINK = "images/noimage.jpg";
                    }

                    strValues += "       "
                            + "                <div class=\"uk-card  uk-card-default uk-card-body uk-margin-small uk-border-rounded \" style=\"padding: 20px\" >\n"
                            + "                    <div class=\"uk-child-width-1-2  uk-text-center \" uk-grid>\n"
                            + "                        <div class=\"uk-text-left\">\n"
                            + "                            <span class=\"package_name\" style=\"font-weight: bold; color:#039ED7; \">" + NAME_PACK + "</span>    <br>\n"
                            + "                            Soạn tin: <span  style=\"font-weight: bold; color:#1B9F02; \"> " + CMD + " </span> <br>\n"
                            + "                            Gửi : <span  style=\"font-weight: bold; color:#1B9F02; \"> " + DS + " </span> <br>\n"
                            + "                        </div>\n"
                            + "                        <div>\n"
                            + "                            <div class=\"\">\n"
                            + "                                <img class=\"custom-imagePackage\" src=\"" + IMG_LINK + "\">\n"
                            + "                            </div>\n"
                            + "                        </div>\n"
                            + "                    </div>                            \n"
                            + "                     <div class=\"uk-child-width-1-2  uk-text-center\" uk-grid>\n"
                            + "                        <div class=\"uk-text-left\">\n"
                            + "                            <div  class=\"modal-desktop uk-margin-small uk-visible@m\" > <a   onclick=\"ShowContent('" + NAME_PACK + "',`" + INFO + "`,'" + CMD + "','" + DS + "');\"  href=\"#modal-package\" uk-toggle  class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded main_web_btn\" style=\"background: #039ED7; color: #ffffff\" >   Đăng ký </a>  </div> "
                            + "                            <div  class=\"modal-mobile1 uk-margin-small uk-hidden@m\" > <a   onclick=\"CallSMS('" + DS + "','" + CMD + "');\" href=\"#!\" class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded main_web_btn\" style=\"background: #039ED7; color: #ffffff\" >   Đăng ký  </a>  </div> "
                            + "                        </div>\n"
                            + "                                  \n"
                            + "                        <div>\n"
                            + "                            <div class=\"\">\n"
                            + "                                <div class=\"modal-mobile uk-margin-small\" >  <a onclick=\"ShowContent('" + NAME_PACK + "',`" + INFO + "`,'" + CMD + "','" + DS + "');\"  href=\"#modal-package\" uk-toggle  class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded\" style=\"border: 1px solid #039ED7\" >  Chi tiết <span uk-icon=\"icon: triangle-right\"></span>  </a>  </div>\n"
                            + "                            </div>\n"
                            + "                        </div>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "           "
                            + "           ";
                    if (dem % 2 == 0) {
                        strValues += " </li>";
                    }

                }

                strValues += ""
                        + "\n"
                        + "        </ul>\n"
                        + "\n"
                        + "        <a class=\"uk-position-center-left uk-position-small uk-hidden-hover\" href=\"#\" uk-slidenav-previous uk-slider-item=\"previous\"></a>\n"
                        + "        <a class=\"uk-position-center-right uk-position-small uk-hidden-hover\" href=\"#\" uk-slidenav-next uk-slider-item=\"next\"></a>\n"
                        + "    </div>\n"
                        + "    <ul class=\"uk-slider-nav uk-dotnav uk-flex-center uk-margin\"></ul>\n"
                        + "</div>";
                strValues += "";

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

    public String ListPackByUser(String service_id, String msisdn) {
        String strValues = "";
        Function fc = new Function();

        try {
            String url_api_2 = API_CMS + "api_package?service_id=" + service_id + "&msisdn=" + msisdn + "&action=list_by_user";
            String strJsonContent = fc.PostJsonAuth("", url_api_2, 30);

//                System.out.println("vnpt.data.api.api_home.GetListContentHome(=====strJsonContent:" + strJsonContent + "========)");
            if (!strJsonContent.equals("")) {

//                    System.out.println("vnpt.data.api.api_user.GetListPackV2()");
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr_2 = (JSONArray) obj.get("result");

                if (arr_2.length() > 0) {
                    int dem = 0;

                    strValues += "<div uk-slider>\n"
                            + "    <div class=\"uk-position-relative uk-visible-toggle uk-light\" tabindex=\"-1\">\n"
                            + "        <ul class=\"uk-slider-items uk-grid-20-m uk-child-width-1-1 uk-child-width-1-2@s uk-child-width-1-2@m\">\n"
                            + "       ";

                    for (int j = 0; j < arr_2.length(); j++) {
                        String IDp = cf.CheckNull(arr_2.getJSONObject(j).getString("ID"));
                        String NAME_PACK = cf.CheckNull(arr_2.getJSONObject(j).getString("NAME"));
                        //                        String CATEGORY_ID = cf.CheckNull(arr_2.getJSONObject(j).getString("CATEGORY_ID"));
                        String INFO = cf.CheckNull(arr_2.getJSONObject(j).getString("INFO"));
                        String CMD = cf.CheckNull(arr_2.getJSONObject(j).getString("CMD"));
                        String DS = cf.CheckNull(arr_2.getJSONObject(j).getString("DS"));
                        String IMG_LINK = cf.CheckNull(arr_2.getJSONObject(j).getString("IMG_LINK"));
                        String CMD_UNREG = cf.CheckNull(arr_2.getJSONObject(j).getString("CMD_UNREG"));

                        //                        String strLinkImg = IMG_LINK;
                        dem += 1;
                        if (dem % 2 == 0) {
                        } else {
                            strValues += " <li>";
                        }

                        if (IMG_LINK.endsWith("images/") || IMG_LINK.equals("")) {
                            IMG_LINK = "images/noimage.jpg";
                        }

                        strValues += "       "
                                + "                <div class=\"uk-card  uk-card-default uk-card-body uk-margin-small uk-border-rounded \" style=\"padding: 20px\" >\n"
                                + "                    <div class=\"uk-child-width-1-2  uk-text-center \" uk-grid>\n"
                                + "                        <div class=\"uk-text-left\">\n"
                                + "                            <span class=\"package_name\" style=\"font-weight: bold; color:#039ED7; \">" + NAME_PACK + "</span>    <br>\n"
                                //                                    + "                            Soạn tin: <span  style=\"font-weight: bold; color:#1B9F02; \"> " + CMD + " </span> <br>\n"
                                //                                    + "                            Gửi : <span  style=\"font-weight: bold; color:#1B9F02; \"> " + DS + " </span> <br>\n"
                                + "                        </div>\n"
                                + "                        <div>\n"
                                + "                            <div class=\"\">\n"
                                + "                                <img src=\"" + IMG_LINK + "\">\n"
                                + "                            </div>\n"
                                + "                        </div>\n"
                                + "                    </div>                            \n"
                                + "                     <div class=\"uk-child-width-1-2  uk-text-center\" uk-grid>\n"
                                + "                        <div class=\"uk-text-left\">\n"
                                + "                            <div  class=\"modal-destop uk-margin-small uk-visible@m\" > <a   onclick=\"ShowContent('" + NAME_PACK + "',`" +  INFO + "`,'" + CMD + "','" + DS + "');\"  href=\"#modal-package\" uk-toggle  class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded main_web_btn\" style=\"background: #039ED7; color: #ffffff\" >   Hủy </a>  </div> "
                                + "                            <div  class=\"modal-mobile1 uk-margin-small uk-hidden@m\" > <a   onclick=\"CallUnregSMS('" + DS + "','" + CMD_UNREG + "','" + NAME_PACK + "');\" href=\"#!\" class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded main_web_btn\" style=\"background: #039ED7; color: #ffffff\" >   Huỷ  </a>  </div> "
                                + "                        </div>\n"
                                + "                                  \n"
                                + "                        <div>\n"
                                + "                            <div class=\"\">\n"
                                + "                                <div class=\"modal-mobile uk-margin-small\" >  <a onclick=\"ShowContent('" + NAME_PACK + "',`" + INFO + "`,'" + CMD + "','" + DS + "','1');\"  href=\"#modal-package\" uk-toggle  class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded\" style=\"border: 1px solid #039ED7\" >  Chi tiết <span uk-icon=\"icon: triangle-right\"></span>  </a>  </div>\n"
                                + "                            </div>\n"
                                + "                        </div>\n"
                                + "                    </div>\n"
                                + "                </div>\n"
                                + "           "
                                + "           ";
                        if (dem % 2 == 0) {
                            strValues += " </li>";
                        }

                    }

                    strValues += ""
                            + "\n"
                            + "        </ul>\n"
                            + "\n"
                            + "        <a class=\"uk-position-center-left uk-position-small uk-hidden-hover\" href=\"#\" uk-slidenav-previous uk-slider-item=\"previous\"></a>\n"
                            + "        <a class=\"uk-position-center-right uk-position-small uk-hidden-hover\" href=\"#\" uk-slidenav-next uk-slider-item=\"next\"></a>\n"
                            + "    </div>\n"
                            + "    <ul class=\"uk-slider-nav uk-dotnav uk-flex-center uk-margin\"></ul>\n"
                            + "</div>";
                    strValues += "";
                } else {
                    strValues = "<div class=\"uk-alert-warning\" uk-alert>\n"
                            + "  <a class=\"uk-alert-close\" uk-close></a>\n"
                            + "  <p>Bạn chưa đăng ký gói cước nào! Vui lòng đăng ký gói cước: <a href=\"goi-cuoc.html\">tại đây</a>  </p>\n"
                            + "</div>";
                }

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }
}
