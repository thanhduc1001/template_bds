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

public class api_cat {

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

//    view tin tức phim cat
    public String GetListContentCatV2(String msisdn, String subjectCode, String contentType, String cat, String strpage, String strpagesize, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();

            String url_api = API_CMS;
            String pageSize = strpagesize;
            String pageNumber = strpage;

            String max = strpagesize;
            String page = strpage;

            String strPostData = "api_content?action=list&service_id=" + service_id + "&page=" + page + "&limit=" + max + "&cat=" + cat;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 30);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");

            // System.out.println("vnpt.data.api.api_cat.GetListContentCat(-strPostData:" + strPostData + "---strJsonContent:" + strJsonContent + "---------)");
            if (sok.equals("1")) {

                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");

                int dem = arr.length();
                if (dem > 0) {
                    try {
                        String contentIDhot = arr.getJSONObject(0).getInt("contentID") + "";
                        String imagehot = arr.getJSONObject(0).getString("image");
                        String titlehot = arr.getJSONObject(0).getString("title");
                        String subjectCodehot = arr.getJSONObject(0).getString("subjectCode");
                        if (imagehot.endsWith("images/")) {
                            imagehot = "images/noimage.jpg";
                        }
                        String linkhot = cf.UrlVn(titlehot) + "-n" + contentIDhot + ".html";

                        //============== tin hot ======================
                        // strValues += " tieude: " + titlehot + "  <br> ";
                        strValues += "<div class=\"home__margin1\"><div class=\"uk-grid-collapse home__box2__box\" uk-grid> ";
                        strValues += "<div class=\"uk-width-expand\">\n"
                                + "<div class=\"uk-cover-container home__box1\">\n"
                                + "<a href=\"" + linkhot + "\">"
                                + "<img src=\"" + imagehot + "\" alt=\"\" uk-cover>\n"
                                + "<canvas width=\"700\" height=\"513\"></canvas>\n"
                                + "</a>"
                                + "<div class=\"uk-padding-small uk-flex uk-flex-bottom uk-position-bottom home__box1__content\">\n"
                                + "<h4 class=\"uk-h4 uk-margin-remove home__box1__content__title\"><a href=\"" + linkhot + "\">" + titlehot + "</a></h4>\n"
                                + "</div>\n"
                                + "</div>\n"
                                + "</div> \n";

                        strValues += " <div class=\"uk-width-1-3@m\"><div class=\"uk-padding-small right-cards\"> ";

                        for (int i = 1; i < 5; i++) {
                            try {
                                if (i < dem) {
                                    String contentID = arr.getJSONObject(i).getInt("contentID") + "";
                                    String image = cf.CheckNull(arr.getJSONObject(i).getString("image"));
                                    String title = cf.CheckNull(arr.getJSONObject(i).getString("title"));
                                    String description = cf.CheckNull(arr.getJSONObject(i).getString("description"));
                                    String createDate = cf.CheckNull(arr.getJSONObject(i).getString("createDate"));
                                    String subjectCodeDetail = cf.CheckNull(arr.getJSONObject(i).getString("subjectCode"));
                                    if (image.endsWith("images/")) {
                                        image = "images/noimage.jpg";
                                    }
                                    String linkdetail = cf.UrlVn(title) + "-n" + contentID + ".html";

                                    strValues += "<div class=\"home__box2__item\">\n"
                                            + "<div class=\"uk-grid-small\" uk-grid>\n"
                                            + "<div class=\"uk-width-2-5\">\n"
                                            + "<div class=\"uk-cover-container home__box2__img\">\n"
                                            + "<a href=\"" + linkdetail + "\">"
                                            + "<img src=\"" + image + "\" alt=\"\" uk-cover>\n"
                                            + "<canvas width=\"248\" height=\"178\"></canvas>\n"
                                            + "</a>"
                                            + "</div>\n"
                                            + "</div>\n"
                                            + "<div class=\"uk-width-expand\">\n"
                                            + "<h5 class=\"uk-h5 home__box2__title uk-margin-remove\">"
                                            + "<a href=\"" + linkdetail + "\">" + title + "</a></h5>\n"
                                            + "<span class=\"home__box3__time\">" + createDate + "</span>"
                                            + "</div>\n"
                                            + "</div>\n"
                                            + "</div>";
                                }
                            } catch (Exception e) {
                                System.out.println("Loi doc du lieu tu API(api_content?action=list&service_id=" + service_id + "&page=" + page + "&limit=" + max + "&cat=" + cat + "). Vui long kiem tra lai!");
                            }

                        }

                        strValues += "   </div></div> ";

                        strValues += " </div> </div>";

                        //============== tin hot ======================
                    } catch (Exception e) {
                        System.out.println("Loi doc du lieu tu API(api_content?action=list&service_id=" + service_id + "&page=" + page + "&limit=" + max + "&cat=" + cat + "). Vui long kiem tra lai!");
                    }
                }
                //============== tin tiep ====================
                if (dem > 5) {
                    strValues += "  ";
                    strValues += " <div class=\"home__margin\">\n"
                            + "                                <div class=\"uk-grid-35-m\" uk-grid> ";
                    for (int i = 5; i < dem; i++) {
                        try {
                            String contentID = arr.getJSONObject(i).getInt("contentID") + "";
                            String image = cf.CheckNull(arr.getJSONObject(i).getString("image"));
                            String title = cf.CheckNull(arr.getJSONObject(i).getString("title"));
                            String description = arr.getJSONObject(i).getString("description");
                            String createDate = arr.getJSONObject(i).getString("createDate");
                            String subjectCodeDetail = arr.getJSONObject(i).getString("subjectCode");
                            String subjectCodeName = arr.getJSONObject(i).getString("subjectName");
                            if (image.endsWith("images/")) {
                                image = "images/noimage.jpg";
                            }
                            String linkdetail = cf.UrlVn(title) + "-n" + contentID + ".html";
                            String linkcat = "view-" + subjectCodeDetail.toLowerCase() + ".html";
                            //  createDate = createDate.substring(0, createDate.length() - 3).replaceAll("-", "/");
                            String strDateAgo = createDate;
                            description = cf.getIntro(description, 170, "...");

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
                                    + "<a href=\"" + linkcat + "\" class=\"uk-button uk-button-default uk-button-small  uk-border-rounded \">" + subjectCodeName + "</a>\n"
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
                            System.out.println("Loi doc du lieu tu API(api_content?action=list&service_id=" + service_id + "&page=" + page + "&limit=" + max + "&cat=" + cat + "). Vui long kiem tra lai!");
                        }
                    }
                    strValues += "  </div> </div>";
                    strValues += "  ";
                }
                //============== tin tiep ====================
                strValues += "  ";
                strValues += "  ";

                //=============== phan trang ==================
                if (dem > 0) {
                    String totalPage = cf.getJsonINT1(strJsonContent, "totalPage");
                    //totalPage = "20";
                    if (!totalPage.equals("1")) {

                        int itotalPage = 0;
                        int icurpage = 0;
                        try {
                            itotalPage = Integer.parseInt(totalPage);
                            icurpage = Integer.parseInt(strpage);
                            if (icurpage < 1) {
                                icurpage = 1;
                            }
                        } catch (Exception e) {
                        }

                        strValues += "  <div class=\"home__margin\"><ul class=\"uk-pagination uk-flex-center\" uk-margin>";
                        String strLinkPagePre = "view-" + cat + "-p" + (icurpage - 1) + ".html";
                        if (icurpage > 1) {
                            strValues += " <li><a href=\"" + strLinkPagePre + "\"><span uk-pagination-previous></span></a></li> ";
                        }

                        if (icurpage - 3 > 0) {
                            strValues += "  <li class=\"uk-disabled\"><span>...</span></li>  ";
                        }

                        int minpage = 0;
                        int maxpage = 0;
                        if ((icurpage - 3) > 0) {
                            minpage = icurpage - 3;
                        } else {
                            minpage = 1;
                        }

                        if ((icurpage + 3) > itotalPage) {
                            maxpage = itotalPage;
                        } else {
                            maxpage = icurpage + 3;
                        }

                        int totalPageMax = 5;
                        int totalPageMin = 1;
                        if (icurpage > 3) {
                            totalPageMax = icurpage + 2;
                            totalPageMin = icurpage - 2;
                        }
                        if (totalPageMax > itotalPage) {
                            totalPageMax = itotalPage;
                        }

                        //  System.out.println("vnpt.data.api.api_cat.GetListContentCat(----minpage:" + minpage + "----maxpage:" + maxpage + "---totalPage:" + totalPage + "-----)");
                        for (int i = totalPageMin; i <= totalPageMax; i++) {
                            String strLinkPage = "view-" + cat + "-p" + i + ".html";

                            if (icurpage == i) {
                                strValues += " <li><a href=\"" + strLinkPage + "\" class=\"\" > <span class=\"uk-label uk-border-rounded detail_page_active\">" + i + "</span></a></li> ";

                            } else {
                                strValues += " <li><a href=\"" + strLinkPage + "\" class=\"\" > <span class=\"uk-label uk-border-rounded detail_page_default\">" + i + "</span></a></li> ";
                            }

                        }

                        if (icurpage + 3 < itotalPage + 1) {
                            strValues += "  <li class=\"uk-disabled\"><span>...</span></li>  ";
                        }

                        String strLinkPageNext = "view-" + cat + "-p" + (icurpage + 1) + ".html";
                        if (icurpage < itotalPage) {
                            strValues += "   <li><a href=\"" + strLinkPageNext + "\"><span uk-pagination-next></span></a></li> ";
                            strValues += " </ul></div> ";
                        } else {
                            strValues += " </ul></div> ";
                        }

                    }
                }
                //=============== phan trang ==================
            }

            //strValues = strJsonContent;
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

//    view phim ngắn cat
    public String GetListContentCatV3(String msisdn, String subjectCode, String contentType, String cat, String strpage, String strpagesize, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();

            String url_api = API_CMS;
            String pageSize = strpagesize;
            String pageNumber = strpage;

            String max = strpagesize;
            String page = strpage;

            String strPostData = "api_content?action=list&service_id=" + service_id + "&page=" + page + "&limit=" + max + "&cat=" + cat;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 30);

            String sok = cf.getJsonINT1(strJsonContent, "errorCode");

            // System.out.println("vnpt.data.api.api_cat.GetListContentCat(-strPostData:" + strPostData + "---strJsonContent:" + strJsonContent + "---------)");
            if (sok.equals("1")) {

                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");

                int dem = arr.length();
                if (dem > 0) {
                    try {
                        strValues += "<div class=\"home__margin1\" style='display: flex'><div class=\"uk-grid-collapse uk-width-2-3@m\" uk-grid> ";
                        strValues += "<div class=\"uk-width-expand\">\n"
                                + " <div class=\"uk-child-width-1-3@m card-movie-desktop\" uk-grid >\n";

                        for (int i = 0; i < dem; i++) {
                            String contentIDhot = arr.getJSONObject(i).getInt("contentID") + "";
                            String imagehot = arr.getJSONObject(i).getString("image");
                            if (imagehot.endsWith("images/")) {
                                imagehot = "images/noimage.jpg";
                            }
                            String titlehot = arr.getJSONObject(i).getString("title");
                            String subjectCodehot = arr.getJSONObject(i).getString("subjectCode");
                            String videoLink = arr.getJSONObject(i).getString("videoLink");
                            String linkhot = cf.UrlVn(titlehot) + "-n" + contentIDhot + ".html";

                            strValues += "<div>\n"
                                    + "    <div class=\"uk-card uk-card-default\">\n"
                                    + "        <div class=\"uk-card-media-top\">\n"
                                    + "            <a href=\"" + linkhot + "\"_target='_blank'><img class=\"custom-image\" src=\"" + imagehot + "\" alt=\"\"> </a>\n"
                                    + "        </div>\n"
                                    + "        <div class=\"uk-card-body uk-padding-small\">\n"
                                    + "            <p class=\"uk-text-truncate uk-margin-remove\">" + titlehot + "</p>\n"
                                    + "        </div>\n"
                                    + "    </div>\n"
                                    + "</div>\n";

                        }

                        //phim ngắn right part
                        strValues += "</div>"
                                + "</div> \n"
                                + "    </div>\n"
                                + " <div class=\"uk-width-1-3@m home__box2__box mobile-view\"><div class=\"uk-padding-small right-cards\"> ";

                        for (int i = 0; i < 10; i++) {
                            try {
                                if (i < dem) {
                                    String contentID = arr.getJSONObject(i).getInt("contentID") + "";
                                    String image = cf.CheckNull(arr.getJSONObject(i).getString("image"));
                                    String title = cf.CheckNull(arr.getJSONObject(i).getString("title"));
                                    String description = cf.CheckNull(arr.getJSONObject(i).getString("description"));
                                    String createDate = cf.CheckNull(arr.getJSONObject(i).getString("createDate"));
                                    String subjectCodeDetail = cf.CheckNull(arr.getJSONObject(i).getString("subjectCode"));
                                    if (image.endsWith("images/")) {
                                        image = "images/noimage.jpg";
                                    }
                                    String linkdetail = cf.UrlVn(title) + "-n" + contentID + ".html";

                                    strValues += "<div class=\"home__box2__item\">\n"
                                            + "<div class=\"uk-grid-small\" uk-grid>\n"
                                            + "<div class=\"uk-width-2-5\">\n"
                                            + "<div class=\"uk-cover-container home__box2__img\">\n"
                                            + "<a href=\"" + linkdetail + "\">"
                                            + "<img src=\"" + image + "\" alt=\"\" uk-cover>\n"
                                            + "<canvas width=\"248\" height=\"178\"></canvas>\n"
                                            + "</a>"
                                            + "</div>\n"
                                            + "</div>\n"
                                            + "<div class=\"uk-width-expand\">\n"
                                            + "<h5 class=\"uk-h5 home__box2__title uk-margin-remove\">"
                                            + "<a href=\"" + linkdetail + "\">" + title + "</a></h5>\n"
                                            + "<span class=\"home__box3__time\">" + createDate + "</span>"
                                            + "</div>\n"
                                            + "</div>\n"
                                            + "</div>";
                                }
                            } catch (Exception e) {
                                System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                            }

                        }

                        strValues += "   </div></div> ";

                        strValues += " </div>";

                        //============== tin hot ======================
                    } catch (Exception ex) {
                        System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                    }
                }

                //============== tin tiep ====================
                strValues += "  ";
                strValues += "  ";

                //=============== phan trang ==================
                if (dem > 0) {
                    String totalPage = cf.getJsonINT1(strJsonContent, "totalPage");
                    //totalPage = "20";
                    if (!totalPage.equals("1")) {

                        int itotalPage = 0;
                        int icurpage = 0;
                        try {
                            itotalPage = Integer.parseInt(totalPage);
                            icurpage = Integer.parseInt(strpage);
                            if (icurpage < 1) {
                                icurpage = 1;
                            }
                        } catch (Exception e) {
                        }

                        strValues += "  <div class=\"home__margin\"><ul class=\"uk-pagination uk-flex-center\" uk-margin>";
                        String strLinkPagePre = "view-" + cat + "-p" + (icurpage - 1) + ".html";
                        if (icurpage > 1) {
                            strValues += " <li><a href=\"" + strLinkPagePre + "\"><span uk-pagination-previous></span></a></li> ";
                        }

                        if (icurpage - 3 > 0) {
                            strValues += "  <li class=\"uk-disabled\"><span>...</span></li>  ";
                        }

                        int minpage = 0;
                        int maxpage = 0;
                        if ((icurpage - 3) > 0) {
                            minpage = icurpage - 3;
                        } else {
                            minpage = 1;
                        }

                        if ((icurpage + 3) > itotalPage) {
                            maxpage = itotalPage;
                        } else {
                            maxpage = icurpage + 3;
                        }

                        int totalPageMax = 5;
                        int totalPageMin = 1;
                        if (icurpage > 3) {
                            totalPageMax = icurpage + 2;
                            totalPageMin = icurpage - 2;
                        }
                        if (totalPageMax > itotalPage) {
                            totalPageMax = itotalPage;
                        }

                        //  System.out.println("vnpt.data.api.api_cat.GetListContentCat(----minpage:" + minpage + "----maxpage:" + maxpage + "---totalPage:" + totalPage + "-----)");
                        for (int i = totalPageMin; i <= totalPageMax; i++) {
                            String strLinkPage = "view-" + cat + "-p" + i + ".html";

                            if (icurpage == i) {
                                strValues += " <li><a href=\"" + strLinkPage + "\" class=\"\" > <span class=\"uk-label uk-border-rounded detail_page_active\">" + i + "</span></a></li> ";

                            } else {
                                strValues += " <li><a href=\"" + strLinkPage + "\" class=\"\" > <span class=\"uk-label uk-border-rounded detail_page_default\">" + i + "</span></a></li> ";
                            }

                        }

                        if (icurpage + 3 < itotalPage + 1) {
                            strValues += "  <li class=\"uk-disabled\"><span>...</span></li>  ";
                        }

                        String strLinkPageNext = "view-" + cat + "-p" + (icurpage + 1) + ".html";
                        if (icurpage < itotalPage) {
                            strValues += "   <li><a href=\"" + strLinkPageNext + "\"><span uk-pagination-next></span></a></li> ";
                            strValues += " </ul></div> ";
                        } else {
                            strValues += " </ul></div> ";
                        }

                    }
                }
                //=============== phan trang ==================
            }

            //strValues = strJsonContent;
        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

}
