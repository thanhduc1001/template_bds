/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.data;

import org.json.JSONArray;
import org.json.JSONObject;
;
import cgt.log.log_save;
import cgt.functions.Function;
import cgt.functions.Function_time;
import cgt.resource.ConfigInfoCache;

/**
 *
 * @author Ho Sy Minh
 */


public class api_right {

    Function cf = new Function();
    Function_time cft = new Function_time();
    log_save ls = new log_save();
    ConfigInfoCache config = new ConfigInfoCache();
    private String API_LINK = config.ConfigInfoCache("API_LINK");
    private String API_CMS = config.ConfigInfoCache("API_CMS");
    private String SERVICE_ID = config.ConfigInfoCache("SERVICE_ID");

    public String GetListContentRight1v2(String msisdn, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS;
            String max = "5";
            String page = "1";
            String strPostData = "api_content?action=list&limit=" + max + "&page=" + page + "&service_id=" + service_id;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 30);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            //System.out.println("vnpt.data.api.api_home.GetListContentHome(=====strPostData:"+strPostData+"========)");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();
                if (dem > 0) {
                    strValues += " <div class=\"uk-padding-small\" style=\"background-image: linear-gradient(to bottom,#ffffff,#ffffff);\"> ";

                    for (int i = 0; i < dem; i++) {
                        try {
                            String contentID = arr.getJSONObject(i).getInt("contentID") + "";
                            String image = cf.CheckNull(arr.getJSONObject(i).getString("image"));
                            if (image.endsWith("images/") || image.equals("")) {
                                image = "images/noimage.jpg";
                            }
                            String title = cf.CheckNull(arr.getJSONObject(i).getString("title"));
                            String description = arr.getJSONObject(i).getString("description");
                            String createDate = arr.getJSONObject(i).getString("createDate");
                            String subjectCodeDetail = arr.getJSONObject(i).getString("subjectCode");
                            String subjectCodeName = cf.VinaSportShowName(subjectCodeDetail);

                            String linkdetail = cf.UrlVn(title) + "-n" + contentID + ".html";
                            strValues += "  <div class=\"home__box2__item\">\n"
                                    + "                    <div class=\"uk-grid-small\" uk-grid>\n"
                                    + "                        <div class=\"uk-width-1-4\">\n"
                                    + "                            <div class=\"uk-cover-container home__box2__img\">\n"
                                    + "                               <a href=\"" + linkdetail + "\" > "
                                    + "<img src=\"" + image + "\" alt=\"\" uk-cover>\n"
                                    + "                                <canvas width=\"248\" height=\"178\"></canvas>\n"
                                    + "</a>"
                                    + "                            </div>\n"
                                    + "                        </div>\n"
                                    + "                        <div class=\"uk-width-expand\">\n"
                                    + "                            <h5 class=\"uk-h5 home__box2__title uk-margin-remove\"><a href=\"" + linkdetail + "\" style=\"color: #000000\">" + title + "</a></h5>\n"
                                    + "         </div>\n"
                                    + "    </div>\n"
                                    + "</div> ";
                        } catch (Exception e) {
                            System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                        }

                    }
                    strValues += " </div> ";

                }
            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

    public String GetListContentRight2v2(String msisdn, String subjectCode, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS;
            String max = "5";
            String page = "1";
            String strPostData = "api_content?action=list&limit=" + max + "&page=" + page + "&cat=" + subjectCode + "&service_id=" + service_id;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 30);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            //System.out.println("vnpt.data.api.api_home.GetListContentHome(=====strPostData:"+strPostData+"========)");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();

                try {
                    String contentIDhot = arr.getJSONObject(0).getInt("contentID") + "";
                    String imagehot = arr.getJSONObject(0).getString("image");
                    if (imagehot.endsWith("images/")) {
                        imagehot = "images/noimage.jpg";
                    }
                    String titlehot = arr.getJSONObject(0).getString("title");
                    String subjectCodehot = arr.getJSONObject(0).getString("subjectCode");
                    String linkhot = cf.UrlVn(titlehot) + "-n" + contentIDhot + ".html";

                    strValues += " ";
                    strValues += " <div class=\"\">\n"
                            + "            <div class=\"uk-cover-container home__box1\">\n"
                            + "                <a href=\"" + linkhot + "\"><img src=\"" + imagehot + "\" alt=\"\" uk-cover>\n"
                            + "                <canvas width=\"600\" height=\"370\"></canvas></a>\n"
                            + "                <div class=\"uk-padding-small uk-flex uk-flex-bottom uk-position-bottom home__box1__content\">\n"
                            + "                    <h4 class=\"uk-h4 uk-margin-remove home__box1__content__title\">"
                            + "<a href=\"" + linkhot + "\">" + titlehot + "</a></h4>\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </div> ";
                } catch (Exception e) {
                    System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                }

                strValues += "  <div class=\"uk-padding-small\">";
                for (int i = 1; i < dem; i++) {

                    try {
                        String contentID = arr.getJSONObject(i).getInt("contentID") + "";
                        String image = cf.CheckNull(arr.getJSONObject(i).getString("image"));
                        if (image.endsWith("images/")) {
                            image = "images/noimage.jpg";
                        }
                        String title = cf.CheckNull(arr.getJSONObject(i).getString("title"));
                        String description = arr.getJSONObject(i).getString("description");
                        String createDate = arr.getJSONObject(i).getString("createDate");
                        String subjectCodeDetail = arr.getJSONObject(i).getString("subjectCode");
                        String subjectCodeName = cf.VinaSportShowName(subjectCodeDetail);

                        String linkdetail = cf.UrlVn(title) + "-n" + contentID + ".html";

                        strValues += "<div class=\"home__box2__item\">\n"
                                + "                <div class=\"uk-grid-small\" uk-grid>\n"
                                + "                    <div class=\"uk-width-1-4\">\n"
                                + "                        <div class=\"uk-cover-container home__box2__img\">\n"
                                + "                            <a href=\"" + linkdetail + "\">"
                                + "<img src=\"" + image + "\" alt=\"\" uk-cover>\n"
                                + "                            <canvas width=\"248\" height=\"178\"></canvas>"
                                + "</a>"
                                + "                        </div>\n"
                                + "                    </div>\n"
                                + "                    <div class=\"uk-width-expand\">\n"
                                + "                        <h5 class=\"uk-h5 home__box2__title uk-margin-remove\">"
                                + "<a href=\"" + linkdetail + "\" style=\"color: #FFFFFF\">" + title + "</a></h5>\n"
                                + "                    </div>\n"
                                + "                </div>\n"
                                + "            </div> ";
                    } catch (Exception e) {
                        System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                    }

                }
                strValues += " </div>";

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }
}
