/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.data;

import org.json.JSONArray;
import org.json.JSONObject;
import cgt.log.log_save;
import cgt.functions.Function;
import cgt.functions.Function_time;
import cgt.resource.ConfigInfoCache;

/**
 *
 * @author Ho Sy Minh
 */
public class api_home {

    Function cf = new Function();
    Function_time cft = new Function_time();
    log_save ls = new log_save();
    ConfigInfoCache config = new ConfigInfoCache();
//    private String API_LINK = config.ConfigInfoCache("API_LINK");
    private String API_CMS = config.ConfigInfoCache("API_CMS");
    private String SERVICE_ID = config.ConfigInfoCache("SERVICE_ID");

    public String GetCatName(String code, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_category?action=list&service_id=" + service_id;

            String strJsonContent = fc.PostJsonAuth("", url_api, 30);

            if (!strJsonContent.equals("")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");

                for (int i = 0; i < arr.length(); i++) {

                    try {
                        String ID = arr.getJSONObject(i).getInt("ID") + "";
                        String CODE = cf.CheckNull(arr.getJSONObject(i).getString("CODE"));
                        String NAME = cf.CheckNull(arr.getJSONObject(i).getString("NAME"));

                        if (code.equals(CODE)) {
                            strValues = NAME;
                        }

                    } catch (Exception e) {
                    }

                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strValues;
    }

    public int GetTotalCat(String service_id) {
        int totalCat = 0;
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_category?action=list&service_id=" + service_id;

            String strJsonContent = fc.PostJsonAuth("", url_api, 30);

            if (!strJsonContent.equals("")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                totalCat = arr.length();
            }
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }
        return totalCat;
    }

    public String GetListHomeIcon(String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_category?action=list&service_id=" + service_id;

            String strJsonContent = fc.PostJsonAuth("", url_api, 30);

            if (!strJsonContent.equals("")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();

                for (int i = 0; i < arr.length(); i++) {

                    try {
                        String ID = arr.getJSONObject(i).getInt("ID") + "";
                        String CODE = cf.CheckNull(arr.getJSONObject(i).getString("CODE"));
                        String NAME = cf.CheckNull(arr.getJSONObject(i).getString("NAME"));
                        String IMG = cf.CheckNull(arr.getJSONObject(i).getString("IMAGE"));
                        String linkdetail = "view-" + CODE + ".html";

                        strValues += " <div class=\"uk-first-column uk-animation-slide-bottom-medium\">\n"
                                + "    <div class=\"uk-text-center\">\n"
                                + "        <a href=\"" + linkdetail + "\"><img src=\" " + IMG + "\"  style=\"height: 110px\"  ></a>\n"
                                + "        <br><br>\n"
                                + "        <a href=\"" + linkdetail + "\"><span class=\"name\"> " + NAME + " </span></a>\n"
                                + "    </div>\n"
                                + "</div>";

                    } catch (Exception e) {
                    }

                }

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

    public String GetListHomeGoiCuoc(String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_category?action=list&service_id=" + service_id;
            String url_api_2 = API_CMS + "api_package?action=list&service_id=" + service_id;

            String strJsonContent = fc.PostJsonAuth("", url_api, 30);
            String strJsonContent_2 = fc.PostJsonAuth("", url_api_2, 30);

            if (!strJsonContent.equals("")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");

                JSONObject obj_2 = new JSONObject(strJsonContent_2);
                JSONArray arr_2 = (JSONArray) obj_2.get("result");

                for (int i = 0; i < arr.length(); i++) {

                    try {
                        String ID = arr.getJSONObject(i).getInt("ID") + "";
                        String CODE = cf.CheckNull(arr.getJSONObject(i).getString("CODE"));
                        String NAME = cf.CheckNull(arr.getJSONObject(i).getString("NAME"));

                        String linkdetail = "package-" + CODE + ".html";

                        strValues += " <div style=\" padding-top: 20px\" class=\"uk-margin-small uk-text-bolder\"> <a href=\"" + linkdetail + "\"   style=\"text-decoration: none; font-weight: bold; color:#039ED7; font-size: 18px \">  " + NAME.toUpperCase() + " __  </a>  </div>  ";

                        strValues += "";
                        strValues += "<div uk-slider>\n"
                                + "    <div class=\"uk-position-relative uk-visible-toggle uk-light\" tabindex=\"-1\">\n"
                                + "        <ul class=\"uk-slider-items uk-grid-20-m uk-child-width-1-1 uk-child-width-1-2@s uk-child-width-1-2@m\">\n"
                                + "       ";

                        int idem = 0;

                        for (int j = 0; j < arr_2.length(); j++) {

                            String IDp = cf.CheckNull(arr_2.getJSONObject(j).getString("ID"));
                            String NAME_PACK = cf.CheckNull(arr_2.getJSONObject(j).getString("NAME"));
                            String CATEGORY_ID = cf.CheckNull(arr_2.getJSONObject(j).getString("CATEGORY_ID"));
                            String INFO = cf.CheckNull(arr_2.getJSONObject(j).getString("INFO"));
//                          ===  Thêm thẻ br tự động xuống dòng ===
                            INFO = INFO.replaceAll("\\\\r\\\\n", "<br />");
//                           =====================
                            String CMD = cf.CheckNull(arr_2.getJSONObject(j).getString("CMD"));
                            String DS = cf.CheckNull(arr_2.getJSONObject(j).getString("DS"));
                            String IMG_LINK = cf.CheckNull(arr_2.getJSONObject(j).getString("IMG_LINK"));
                            String CMD_UNREG = cf.CheckNull(arr_2.getJSONObject(j).getString("CMD_UNREG"));

                            if (CATEGORY_ID.equals(ID)) {

                                idem += 1;
                                if (idem % 2 == 0) {
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
                                        + "                            <span style=\"font-weight: bold; color:#039ED7; \">" + NAME_PACK + "</span>    <br>\n"
                                        + "                            Soạn tin: <span  style=\"font-weight: bold; color:#1B9F02; \"> " + CMD + " </span> <br>\n"
                                        + "                            Gửi : <span  style=\"font-weight: bold; color:#1B9F02; \"> " + DS + " </span> <br>\n"
                                        + "                        </div>\n"
                                        + "                        <div>\n"
                                        + "                            <div class=\"\">\n"
                                        + "                                <img src=\"" + IMG_LINK + "\">\n"
                                        + "                            </div>\n"
                                        + "                        </div>\n"
                                        + "                    </div>                            \n"
                                        + "                     <div class=\"uk-child-width-1-2  uk-text-center\" uk-grid>\n"
                                        + "                        <div class=\"uk-text-left\">\n"
                                        + "                            <div  class=\"uk-margin-small uk-visible@m\" > <a   onclick=\"ShowContent('" + NAME_PACK + "',`" + INFO + "`,'" + CMD + "','" + DS + "');\"  href=\"#modal-package\" uk-toggle  class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded\" style=\"background: #039ED7; color: #ffffff\" >   Đăng ký </a>  </div> "
                                        + "                            <div  class=\"uk-margin-small uk-hidden@m\" > <a   onclick=\"CallSMS('" + DS + "','" + CMD + "');\" class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded\" style=\"background: #039ED7; color: #ffffff\" >   Đăng ký  </a>  </div> "
                                        + "                        </div>\n"
                                        + "                                  \n"
                                        + "                        <div>\n"
                                        + "                            <div class=\"\">\n"
                                        + "                                <div class=\"uk-margin-small\" >  <a onclick=\"ShowContent('" + NAME_PACK + "',`" + INFO + "`,'" + CMD + "','" + DS + "');\"  href=\"#modal-package\" uk-toggle  class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded\" style=\"border: 1px solid #039ED7\" >  Chi tiết <span uk-icon=\"icon: triangle-right\"></span>  </a>  </div>\n"
                                        + "                            </div>\n"
                                        + "                        </div>\n"
                                        + "                    </div>\n"
                                        + "                </div>\n"
                                        + "           "
                                        + "           ";
                                if (idem % 2 == 0) {
                                    strValues += " </li>";
                                }
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

                    } catch (Exception e) {
                        System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                    }

                }

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }
        return strValues;
    }

    public String GetListTopMenu(String cat, String service_id, String strLinkCat) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_category?action=list&service_id=" + service_id;

            String strJsonContent = fc.PostJsonAuth("", url_api, 30);

            if (!strJsonContent.equals("")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();

                for (int i = 0; i < dem; i++) {

                    try {
                        String ID = arr.getJSONObject(i).getInt("ID") + "";
                        String CODE = cf.CheckNull(arr.getJSONObject(i).getString("CODE"));
                        String NAME = cf.CheckNull(arr.getJSONObject(i).getString("NAME"));

                        String linkdetail = cat + "-" + CODE + ".html";
                        String strActive = "";
                        if (strLinkCat.startsWith(CODE)) {
                            strActive = "_active";
                        } else {
                            strActive = "";
                        }
                        strValues += " <li><a href=\"" + linkdetail + "\"><div class=\"menu_top_text" + strActive + " uk-border-rounded txt-color\">" + NAME + "</div></a></li> ";
                        strActive = "";

                    } catch (Exception e) {
                    }

                }

            }

        } catch (Exception e) {
            System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
        }
        return strValues;
    }

    public String GetListTopMenu2(String cat, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_category?action=list&service_id=" + service_id;

            String strJsonContent = fc.PostJsonAuth("", url_api, 30);

            if (!strJsonContent.equals("")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();

                for (int i = 0; i < dem; i++) {

                    try {
                        String ID = arr.getJSONObject(i).getInt("ID") + "";
                        String CODE = cf.CheckNull(arr.getJSONObject(i).getString("CODE"));
                        String NAME = cf.CheckNull(arr.getJSONObject(i).getString("NAME"));

                        String linkdetail = cat + "-" + CODE + ".html";

                        strValues += " <li><a href=\"" + linkdetail + "\">" + NAME + "</a></li>";

                    } catch (Exception e) {
                        System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                    }

                }

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

//        for (int i = 0; i < 6; i++) {
//            try {
//                String ID = String.valueOf(i);
//                String CODE = "demo" + i;
//                String NAME = "Danh mục " + i;
//
//                String linkdetail = cat + "-" + CODE + ".html";
//
//                strValues += " <li><a href=\"" + linkdetail + "\">" + NAME + "</a></li>";
//
//            } catch (Exception e) {
//            }
//
//        }
        return strValues;
    }

    //home: tin tức phim
    public String GetListHome1v2(String msisdn, String subjectCode, String page, String max, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS;

//            String strPostData = "api_content?action=list&service_id=" + service_id + "&code="+subjectCode+"&page=" + page + "&limit=" + max;
            String strPostData = "api_content?action=list&service_id=" + service_id + "&page=" + page + "&limit=" + max + "&cat=" + "tin-tuc-phim";
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 30);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            //System.out.println("vnpt.data.api.api_home.GetListContentHome(=====strJsonContent:" + strJsonContent + "========)");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();

                strValues += "<div class=\"home__margin\">";

                strValues += "<div class=\"uk-grid-35-m\" uk-grid>";
                for (int i = 0; i < dem; i++) {

                    try {
                        String contentID = arr.getJSONObject(i).getInt("contentID") + "";
                        String image = cf.CheckNull(arr.getJSONObject(i).getString("image"));
                        String title = cf.CheckNull(arr.getJSONObject(i).getString("title"));
                        String description = arr.getJSONObject(i).getString("description");
                        String createDate = cf.CheckNull(arr.getJSONObject(i).getString("createDate"));
                        String subjectCodeDetail = arr.getJSONObject(i).getString("subjectCode");
                        String subjectName = arr.getJSONObject(i).getString("subjectName");
                        String linkdetail = cf.UrlVn(title) + "-n" + contentID + ".html";
                        String linkcat = "view-" + subjectCodeDetail.toLowerCase() + ".html";
                        description = cf.getIntro(description, 250, "...");
                        if (image.endsWith("images/") || image.equals("")) {
                            image = "images/noimage.jpg";
                        }
                        strValues += "<div class=\"uk-width-1-1\">\n"
                                + "<div class=\"uk-grid-collapse uk-background-default uk-box-shadow-medium uk-grid-match home__box3\" uk-grid>\n"
                                + "<div class=\"uk-width-2-3@m\">\n"
                                + "<div class=\"uk-cover-container\">\n"
                                + "<a href=\"" + linkdetail + "\">"
                                + "<img src=\"" + image + "\" alt=\"\" uk-cover>\n"
                                + "<canvas width=\"776\" height=\"436\"></canvas>\n"
                                + "</a>"
                                + "</div>\n"
                                + "</div>\n"
                                + "<div class=\"uk-width-expand\">\n"
                                + "<div class=\"home__box3__box uk-padding-small\">\n"
                                + "<h4 class=\"uk-h3 home__box3__title\"><a href=\"" + linkdetail + "\">" + title + "</a></h4>\n"
                                + "<div class=\"uk-grid-10 uk-child-width-auto uk-flex-middle\" uk-grid>\n"
                                + "<div>\n"
                                + "<a href=\"" + linkcat + "\" class=\"uk-button uk-button-default uk-button-small  uk-border-rounded menu_category_btn\">" + subjectName + "</a>\n"
                                + "</div>\n"
                                + "<div>\n"
                                + "<span class=\"home__box3__time\">" + createDate + "</span>\n"
                                + "</div>\n"
                                + "</div>\n"
                                + "<p class=\"\">" + description + "</p>\n"
                                + "<p class=\"uk-text-right\">  <a  href=\"" + linkdetail + "\" style=\"border: 1px solid #039ED7\"  class=\"uk-button uk-button-small uk-border-rounded\" style=\"\" >  Chi tiết <span uk-icon=\"icon: triangle-right\"></span>  </a> </p>"
                                + "</div>\n"
                                + "</div>\n"
                                + "</div>\n"
                                + "</div>";
                    } catch (Exception e) {
                        System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                    }

                }

                strValues += " </div>";

                strValues += " </div>";

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }
        return strValues;
    }
    //home: tin tức phim slide

    public String GetListHome2v2(String msisdn, String subjectCode, String page, String max, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS;
            String strPostData = "api_content?action=list&service_id=" + service_id + "&page=" + page + "&limit=" + max + "&cat=" + "tin-tuc-phim";
//            String strPostData = "api_content?action=list&service_id=" + service_id + "&page=" + page + "&limit=" + max;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 30);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            //System.out.println("vnpt.data.api.api_home.GetListContentHome(=====strPostData:"+strPostData+"========)");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();
                for (int i = 0; i < dem; i++) {

                    try {
                        String strCatHTML = "";
                        String contentID = arr.getJSONObject(i).getInt("contentID") + "";
                        String image = cf.CheckNull(arr.getJSONObject(i).getString("image"));
                        String title = cf.CheckNull(arr.getJSONObject(i).getString("title"));
                        String description = arr.getJSONObject(i).getString("description");
                        String createDate = cf.CheckNull(arr.getJSONObject(i).getString("createDate"));
                        String subjectCodeDetail = arr.getJSONObject(i).getString("subjectCode");
                        String subjectCodeName = arr.getJSONObject(i).getString("subjectName");

                        String linkdetail = cf.UrlVn(title) + "-n" + contentID + ".html";
                        String linkcat = "view-" + subjectCodeDetail.toLowerCase() + ".html";
                        if (image.endsWith("images/") || image.equals("")) {
                            image = "images/noimage.jpg";
                        }
                        //title = cf.getIntro(title, 50, "...");
                        // description = cf.getIntro(description, 150, "...");
                        if (!subjectCodeName.equals("")) {
                            strCatHTML = "<div><a href=\"" + linkcat + "\" class=\"uk-button uk-button-default uk-button-small  uk-border-rounded menu_category_btn\">" + subjectCodeName + "</a></div>";
                        }

                        strValues += "  <li class=\"uk-width-1-2@s uk-width-1-3@m\">\n"
                                + "                                <div style=\"background: #365486\"  class=\"uk-grid-collapse uk-background-default uk-box-shadow-medium uk-padding-small uk-grid-match home__box3\" uk-grid>\n"
                                + "                                    <div class=\"uk-padding-small\">\n"
                                + "                                        <div class=\"uk-cover-container\">\n"
                                + "                                           <a href=\"" + linkdetail + "\"> <img src=\"" + image + "\" alt=\"\" uk-cover>\n"
                                + "                                            <canvas width=\"776\" height=\"476\"></canvas> </a>\n"
                                + "                                        </div>\n"
                                + "                                    </div>\n"
                                + "                                    <div class=\"\">\n"
                                + "                                        <div class=\"home__box3__box uk-padding-small\">\n"
                                + "                                            <h4 class=\"home_video_box_title\"><a style=\"color: #ffffff\" title=\"" + title + "\" href=\"" + linkdetail + "\">" + title + "</a></h4>\n"
                                + "                                            <div class=\"uk-grid-10 uk-child-width-auto uk-flex-middle\" uk-grid>\n"
                                + "                                                " + strCatHTML + "\n"
                                + "                                                <div>\n"
                                + "                                                    <span class=\"home__box3__time\" style=\"color: #ffffff\">" + createDate + "</span>\n"
                                + "                                                </div>\n"
                                + "                                            </div>\n"
                                + "                                            <p class=\"home_video_box_des\" style=\"color: #ffffff\">" + description + "</p>\n"
                                + "                                        </div>\n"
                                + "                                    </div>\n"
                                + "                                </div>\n"
                                + "                            </li> ";
                    } catch (Exception e) {
                        System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                    }

                }

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }
        return strValues;
    }

    //    view phim ngắn cat
    public String GetListHome3v2(String msisdn, String subjectCode, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();

            String url_api = API_CMS;

            String strPostData = "api_content?action=list&service_id=" + service_id + "&cat=" + "phim-ngan";
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 30);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");

            // System.out.println("vnpt.data.api.api_cat.GetListContentCat(-strPostData:" + strPostData + "---strJsonContent:" + strJsonContent + "---------)");
            if (sok.equals("1")) {

                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");

                int dem = arr.length();
                if (dem > 0) {
                    try {
                        strValues += "<div class=\"home__margin1\"><div class=\"uk-width-3-3@m home__box2__box\"> ";
                        strValues += "<div class=\"uk-width-expand uk-padding-small\">\n"
                                + " <div class=\"uk-child-width-1-3@m\" uk-grid>\n";

                        for (int i = 0; i < dem; i++) {
                            String contentIDhot = arr.getJSONObject(i).getInt("contentID") + "";
                            String imagehot = arr.getJSONObject(i).getString("image");
                            if (imagehot.endsWith("images/") || imagehot.equals("")) {
                                imagehot = "images/noimage.jpg";
                            }
                            String titlehot = arr.getJSONObject(i).getString("title");
                            String subjectCodehot = arr.getJSONObject(i).getString("subjectCode");
                            String videoLink = arr.getJSONObject(i).getString("videoLink");
                            String linkhot = cf.UrlVn(titlehot) + "-n" + contentIDhot + ".html";

                            strValues += "<div>\n"
                                    + "    <div class=\"uk-card uk-card-default\">\n"
                                    + "        <div class=\"uk-card-media-top\">\n"
                                    + "            <a href=\"" + linkhot + "\"_target='_blank'><img class=\"custom-image\" src=\"" + imagehot + "\" width=\"1800\" height=\"1200\" alt=\"\"> </a>\n"
                                    + "        </div>\n"
                                    + "        <div class=\"uk-card-body uk-padding-small\">\n"
                                    + "            <p class=\"uk-text-truncate uk-margin-remove\">" + titlehot + "</p>\n"
                                    + "        </div>\n"
                                    + "    </div>\n"
                                    + "</div>\n";

                        }

                        strValues += "</div>"
                                + "</div> \n"
                                + "    </div>\n"
                                + " <div class=\"uk-width-1-3@m\"><div class=\"uk-padding-small\"> ";

                        strValues += "   </div></div> ";

                        strValues += " </div> </div>";

                        //============== tin hot ======================
                    } catch (Exception e) {
                        System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                    }
                }

                //============== tin tiep ====================
                strValues += "  ";
                strValues += "  ";
            }

            //strValues = strJsonContent;
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }
}
