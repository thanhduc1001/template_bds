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
public class api_detail {

    Function cf = new Function();
    Function_time cft = new Function_time();
    log_save ls = new log_save();
    ConfigInfoCache config = new ConfigInfoCache();
    private String API_LINK = config.ConfigInfoCache("API_LINK");
    private String API_CMS = config.ConfigInfoCache("API_CMS");
    private String SERVICE_ID = config.ConfigInfoCache("SERVICE_ID");

    public String GetListRelateV2(String msisdn, String subjectCode, String contentType, String contentId, String strpage, String strpagesize, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();

            String url_api = API_CMS;
            String max = strpagesize;
            String page = strpage;
            String strPostData = "api_content?action=list&service_id=" + service_id + "&page=" + page + "&limit=" + max + "&cat=" + subjectCode;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 30);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");

            //System.out.println("vnpt.data.api.api_detail.GetListRelate(-strPostData:" + strPostData + "---strJsonContent:" + strJsonContent + "---------)");
            if (sok.equals("1")) {

                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");

                int dem = arr.length();
                //============== tin tiep ====================
                if (dem > 0) {
                    strValues += "  ";
                    strValues += " <div class=\"home__margin\">\n"
                            + "                                <div class=\"uk-grid-35-m\" uk-grid> ";
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
                            String subjectName = arr.getJSONObject(i).getString("subjectName");

                            String linkdetail = cf.UrlVn(title) + "-n" + contentID + ".html";
                            createDate = createDate.substring(0, createDate.length() - 3).replaceAll("-", "/");
                            String strDateAgo = createDate;
                            description = cf.getIntro(description, 170, "...");
                            if (image.endsWith("images/") || image.equals("")) {
                                image = "images/noimage.jpg";
                            }
                            strValues += " <div class=\"uk-width-1-1\">\n"
                                    + "<div class=\"uk-grid-collapse uk-background-default uk-box-shadow-medium uk-grid-match home__box3\" uk-grid>\n"
                                    + "<div class=\"uk-width-1-3@m\">\n"
                                    + "<div class=\"uk-cover-container\">\n"
                                    + "<a href=\"" + linkdetail + "\">"
                                    + "<img src=\"" + image + "\" alt=\"\" uk-cover>\n"
                                    + "<canvas width=\"776\" height=\"436\"></canvas>\n"
                                    + "</a>"
                                    + "</div>\n"
                                    + "</div>\n"
                                    + "<div class=\"uk-width-expand\">\n"
                                    + "<div class=\"home__box3__box uk-padding-small\">\n"
                                    + "<h4 class=\"uk-h4 home__box3__title\"><a href=\"" + linkdetail + "\">" + title + "</a></h4>\n"
                                    + "<div class=\"uk-grid-10 uk-child-width-auto uk-flex-middle\" uk-grid>\n"
                                    + "<div>\n"
                                    + "<span class=\"uk-label content_card\">" + subjectName + "</span>\n"
                                    + "</div>\n"
                                    + "<div>\n"
                                    + "<span class=\"home__box3__time\">" + strDateAgo + "</span>\n"
                                    + "</div>\n"
                                    + "</div>\n"
                                    + "<p class=\"home__box3__desc\">" + description + "</p>\n"
                                    + "</div>\n"
                                    + "</div>\n"
                                    + "</div>\n"
                                    + "</div> ";
                        } catch (Exception e) {
                            System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                        }

                    }
                    strValues += "  </div> </div> ";
                    strValues += "  ";
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
