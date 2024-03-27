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
import cgt.resource.ConfigInfoCache;

/**
 *
 * @author Ho Sy Minh
 */
public class api_user {

    private String userName;
    private String createDate;
    private String msisdn;
    private String facebookId;
    private String gmailId;
    private String image;
    private String name;
    private String nickName;
    private String gender;
    private String birthday;
    private String fbName;
    private String ggName;
    private String email;
    private String isRevNoti;
    private String packageDto;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGmailId() {
        return gmailId;
    }

    public void setGmailId(String gmailId) {
        this.gmailId = gmailId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFbName() {
        return fbName;
    }

    public void setFbName(String fbName) {
        this.fbName = fbName;
    }

    public String getGgName() {
        return ggName;
    }

    public void setGgName(String ggName) {
        this.ggName = ggName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsRevNoti() {
        return isRevNoti;
    }

    public void setIsRevNoti(String isRevNoti) {
        this.isRevNoti = isRevNoti;
    }

    public String getPackageDto() {
        return packageDto;
    }

    public void setPackageDto(String packageDto) {
        this.packageDto = packageDto;
    }

    public log_save getLs() {
        return ls;
    }

    public void setLs(log_save ls) {
        this.ls = ls;
    }

    public ConfigInfoCache getConfig() {
        return config;
    }

    public void setConfig(ConfigInfoCache config) {
        this.config = config;
    }

    Function cf = new Function();
    log_save ls = new log_save();
    ConfigInfoCache config = new ConfigInfoCache();
    private String API_LINK = config.ConfigInfoCache("API_LINK");
    private String API_CMS = config.ConfigInfoCache("API_CMS");
    private String SERVICE_ID = config.ConfigInfoCache("SERVICE_ID");

    // danh sach tin da luu
    public String GetListContentSave(String msisdn, String subjectCode) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_LINK + "contents/saved";
            String pageSize = "10";
            String contentType = "0";
            String pageNumber = "0";

            String strPostData = "{"
                    + "\"msisdn\":\"" + msisdn + "\","
                    + "\"serviceCode\":" + SERVICE_ID + ","
                    + "\"subjectCode\":\"" + subjectCode + "\","
                    + "\"pageSize\":\"" + pageSize + "\","
                    + "\"contentType\":" + contentType + ","
                    + "\"pageNumber\":" + pageNumber + ""
                    + "}";
            String strJsonContent = fc.PostJson(strPostData, url_api);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            System.out.println("vnpt.data.api.api_home.GetListContentHome(=====strPostData:" + strPostData + "===strJsonContent:" + strJsonContent + "=====)");
            if (sok.equals("0")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();

                strValues += "<div class=\"home__margin1\">";

                strValues += "<div class=\"uk-grid-35-m\" uk-grid>";
                for (int i = 1; i < dem; i++) {

                    try {
                        String contentID = arr.getJSONObject(i).getInt("contentID") + "";
                        String image = cf.CheckNull(arr.getJSONObject(i).getString("image"));
                        String title = cf.CheckNull(arr.getJSONObject(i).getString("title"));
                        String description = arr.getJSONObject(i).getString("description");
                        String createDate = cf.CutNewTimeDay(arr.getJSONObject(i).getString("createDate"));
                        String subjectCodeDetail = arr.getJSONObject(i).getString("subjectCode");
                        String subjectCodeName = cf.VinaSportShowName(subjectCodeDetail);

                        String linkdetail = cf.UrlVn(title) + "-" + subjectCodeDetail.toLowerCase() + "-" + contentID + ".html";

                        strValues += "<div class=\"uk-width-1-1\">\n"
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
                                + "<span class=\"uk-label home__box3__label\">" + subjectCodeName + "</span>\n"
                                + "</div>\n"
                                + "<div>\n"
                                + "<span class=\"home__box3__time\">" + createDate + "</span>\n"
                                + "</div>\n"
                                + "</div>\n"
                                + "<p class=\"home__box3__desc\">" + description + "</p>\n"
                                + "</div>\n"
                                + "</div>\n"
                                + "</div>\n"
                                + "</div>";
                    } catch (Exception e) {
                    }

                }

                strValues += " </div>";

                strValues += " </div>";

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strValues;
    }

    // luu tin 
    public String SaveContent(String msisdn, String subjectCode, String contentId, String action) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_LINK + "save";
            //String action = "1"; //=1 la unsave =0 la save          

            String strPostData = "{"
                    + "\"msisdn\":\"" + msisdn + "\","
                    + "\"serviceCode\":" + SERVICE_ID + ","
                    + "\"categoryCode\":\"" + subjectCode + "\","
                    + "\"contentId\":" + contentId + ","
                    + "\"action\":" + action + ""
                    + "}";
            String strJsonContent = fc.PostJson(strPostData, url_api);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            System.out.println("vnpt.data.api.api_home.SaveContent(=====strPostData:" + strPostData + "===strJsonContent:" + strJsonContent + "=====)");
            strValues = sok;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strValues;
    }

    public String CheckPack(String msisdn, String service_id) {
        String strValues = "";
        Function fc = new Function();
        String sokuser = "";
        String strJsonContentPack = "";
        // System.out.println("vnpt.data.api.api_user.GetListPack(----msisdn:" + msisdn + "---------------)");
        //============== lay goi cuoc ===============
        if (msisdn != null && !msisdn.equals("")) {
            try {
                String url_api = API_LINK + "user/info";
                String strPostData = "{"
                        + "\"msisdn\":\"" + msisdn + "\","
                        + "\"serviceCode\":" + service_id + ""
                        + "}";
                strJsonContentPack = fc.PostJson(strPostData, url_api);
                sokuser = cf.getJsonINT1(strJsonContentPack, "errorCode");
                if (sokuser.equals("0")) {
                    JSONObject objpack = new JSONObject(strJsonContentPack);
                    JSONArray arrpack = objpack.getJSONObject("result").getJSONArray("packageDto");
                    if (arrpack != null) {
                        for (int x = 0; x < arrpack.length(); x++) {
                            // String codepack = cf.CheckNull(arrpack.getJSONObject(x).getString("code"));
                            // System.out.println("vnpt.data.api.api_home.GetListContentHome(=====xx:" + codepack + "====code:"+code+"====)");
                            strValues = "1";
                        }
                    }

                }
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }

        //============== lay goi cuoc ===============
        return strValues;
    }

    public String CheckPackV2(String msisdn, String service_id, String content_id) {
        String strValues = "";
        Function fc = new Function();
        String sokuser = "";
        // System.out.println("vnpt.data.api.api_user.GetListPack(----msisdn:" + msisdn + "---------------)");
        //============== lay goi cuoc ===============
        try {
            String url_api = API_CMS;
            String strPostData = "api_package?action=check_package&service_id=" + service_id + "&msisdn=" + msisdn + "&content_id=" + content_id;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 0);
            sokuser = cf.getJsonINT1(strJsonContent, "errorCode");
            if (sokuser.equals("1")) {
                // String codepack = cf.CheckNull(arrpack.getJSONObject(x).getString("code"));
                // System.out.println("vnpt.data.api.api_home.GetListContentHome(=====xx:" + codepack + "====code:"+code+"====)");
                strValues = "1";
            }
        } catch (Exception e) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        //============== lay goi cuoc ===============
        return strValues;
    }

    public String GetListPack(String msisdn) {
        String strValues = "";
        Function fc = new Function();
        String sokuser = "";
        String strJsonContentPack = "";
        // System.out.println("vnpt.data.api.api_user.GetListPack(----msisdn:" + msisdn + "---------------)");
        //============== lay goi cuoc ===============
        if (msisdn != null && !msisdn.equals("")) {
            try {
                String url_api = API_LINK + "user/info";
                String strPostData = "{"
                        + "\"msisdn\":\"" + msisdn + "\","
                        + "\"serviceCode\":" + SERVICE_ID + ""
                        + "}";
                strJsonContentPack = fc.PostJson(strPostData, url_api);
                sokuser = cf.getJsonINT1(strJsonContentPack, "errorCode");

            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("vnpt.data.api.api_user.GetListPack(----user/info error---)");
            }
        }

        //============== lay goi cuoc ===============
        try {

            String url_api = API_LINK + "package/list";
            String strPostData = "{"
                    + "\"msisdn\":\"" + msisdn + "\","
                    + "\"serviceCode\":" + SERVICE_ID + ""
                    + "}";
            String strJsonContent = fc.PostJson(strPostData, url_api);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            // System.out.println("vnpt.data.api.api_home.GetListContentHome(=====222:" + strJsonContent + "========)");
            if (sok.equals("0")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();

                for (int i = 0; i < dem; i++) {

                    try {
                        String price = arr.getJSONObject(i).getInt("price") + "";
                        String code = cf.CheckNull(arr.getJSONObject(i).getString("code"));
                        String info = cf.CheckNull(arr.getJSONObject(i).getString("info"));
                        String name = arr.getJSONObject(i).getString("name");
                        String syntax = arr.getJSONObject(i).getString("syntax");
                        String imageLink = arr.getJSONObject(i).getString("imageLink");
                        String cmd = arr.getJSONObject(i).getString("cmd");
                        String ds = arr.getJSONObject(i).getString("ds");
                        String cancel = arr.getJSONObject(i).getString("cancel");

                        String sendCuPhap = cf.getSplitCP(cmd);
                        String strDangKy = "Đăng ký";
                        if (sokuser.equals("0")) {
                            try {
                                JSONObject objpack = new JSONObject(strJsonContentPack);
                                JSONArray arrpack = objpack.getJSONObject("result").getJSONArray("packageDto");
                                for (int x = 0; x < arrpack.length(); x++) {
                                    String codepack = cf.CheckNull(arrpack.getJSONObject(x).getString("code"));
                                    // System.out.println("vnpt.data.api.api_home.GetListContentHome(=====xx:" + codepack + "====code:"+code+"====)");
                                    if (codepack.equals(code)) {
                                        strDangKy = "Hủy";
                                        sendCuPhap = cf.getSplitCP(cancel);
                                    }
                                }
                            } catch (Exception e) {
                            }

                        }

                        strValues += " <div>\n"
                                + "    <div class=\"uk-card uk-card-default uk-card-body uk-margin-remove uk-padding-remove\">\n"
                                + "        <img src=\"" + imageLink + "\">\n"
                                + "        <div class=\"pack_box_des\">\n"
                                + "            <div uk-navbar>\n"
                                + "                <div class=\"uk-navbar-left\" > Cú pháp:  <span class=\"pack_box_des2\"> " + cmd + "</span> </div>\n"
                                + "                <div  class=\"uk-navbar-right\" > <a href=\"#\" onclick=\"CallSMS('" + ds + "','" + sendCuPhap + "');\" ><span class=\"uk-label pack_box_btn uk-border-rounded \" >  " + strDangKy + " </span></a>  </div>\n"
                                + "            </div>\n"
                                + "        </div>\n"
                                + "    </div>\n"
                                + "\n"
                                + "</div>";

                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println("vnpt.data.api.api_user.GetListPack(----user/info error 2---)");
                    }

                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strValues;
    }

    public String GetListPackV2(String cat) {
        String strValues = "";
        Function fc = new Function();

        try {

            String url_api_2 = API_CMS + "content_vlive_cat?act=package&id=44&code=" + cat + "";
            String strJsonContent = fc.readStrContentLink(url_api_2);

            System.out.println("vnpt.data.api.api_home.GetListContentHome(=====strJsonContent:" + strJsonContent + "========)");

            if (!strJsonContent.equals("")) {

                System.out.println("vnpt.data.api.api_user.GetListPackV2()");

                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr_2 = (JSONArray) obj.get("data");
                int dem = arr_2.length();

                strValues += "<div  class=\"uk-child-width-1-2@s uk-child-width-1-2@m uk-text-center\" uk-grid>";

                for (int j = 0; j < dem; j++) {
                    String IDp = cf.CheckNull(arr_2.getJSONObject(j).getString("ID"));
                    String NAME_PACK = cf.CheckNull(arr_2.getJSONObject(j).getString("NAME"));
                    String CATEGORY_ID = cf.CheckNull(arr_2.getJSONObject(j).getString("CATEGORY_ID"));
                    String INFO = cf.CheckNull(arr_2.getJSONObject(j).getString("INFO"));
                    String CMD = cf.CheckNull(arr_2.getJSONObject(j).getString("CMD"));
                    String DS = cf.CheckNull(arr_2.getJSONObject(j).getString("DS"));
                    String IMG_LINK = cf.CheckNull(arr_2.getJSONObject(j).getString("IMG_LINK"));
                    String CMD_UNREG = cf.CheckNull(arr_2.getJSONObject(j).getString("CMD_UNREG"));

                    String strLinkImg = API_CMS + "uploads/images/" + IMG_LINK;

                    strValues += "         <li>\n"
                            + "                <div class=\"uk-card uk-card-default uk-card-body uk-margin-small uk-border-rounded \" style=\"padding: 20px\" >\n"
                            + "                    <div class=\"uk-child-width-1-2 uk-text-center \" uk-grid>\n"
                            + "                        <div class=\"uk-text-left\">\n"
                            + "                            <span style=\"font-weight: bold; color:#039ED7; \">" + NAME_PACK + "</span>    <br>\n"
                            + "                            Soạn tin: <span  style=\"font-weight: bold; color:#1B9F02; \"> " + CMD + " </span> <br>\n"
                            + "                            Gửi : <span  style=\"font-weight: bold; color:#1B9F02; \"> " + DS + " </span> <br>                                    \n"
                            + "                        </div>\n"
                            + "                        <div>\n"
                            + "                            <div class=\"\">\n"
                            + "                                <img src=\"" + strLinkImg + "\">                                        \n"
                            + "                            </div>\n"
                            + "                        </div>\n"
                            + "                    </div>                            \n"
                            + "                     <div class=\"uk-child-width-1-2  uk-text-center\" uk-grid>\n"
                            + "                        <div class=\"uk-text-left\">\n"
                            //+ "                            <div  class=\"uk-margin-small\" > <a   onclick=\"CallSMS('" + DS + "','" + CMD + "');\" class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded\" style=\"background: #039ED7; color: #ffffff\" >   Đăng ký  </a>  </div>\n"
                            + "                            <div  class=\"uk-margin-small uk-visible@m\" > <a   onclick=\"CallInfo('" + NAME_PACK + "','" + CMD + "','" + DS + "','" + IDp + "');\"  href=\"#modal-package\" uk-toggle  class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded\" style=\"background: #039ED7; color: #ffffff\" >   Đăng ký </a>  </div> "
                            + "                            <div  class=\"uk-margin-small uk-hidden@m\" > <a   onclick=\"CallSMS('" + DS + "','" + CMD + "');\" class=\"uk-button uk-button-primary  uk-button-small uk-border-rounded\" style=\"background: #039ED7; color: #ffffff\" >   Đăng ký  </a>  </div> "
                            + "                        </div>\n"
                            + "\n"
                            + "                        <div>\n"
                            + "                            <div class=\"\">\n"
                            + "                                <div class=\"uk-margin-small\" >  <a onclick=\"CallInfo('" + NAME_PACK + "','" + CMD + "','" + DS + "','" + IDp + "');\"  href=\"#modal-package\" uk-toggle  class=\"uk-button  uk-button-small uk-border-rounded\" style=\"border: 1px solid #039ED7\" >  Chi tiết <span uk-icon=\"icon: triangle-right\"></span>  </a>  </div>\n"
                            + "                            </div>\n"
                            + "                        </div>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </li>\n"
                            + "           ";

                }

                strValues += "</div>";

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strValues;
    }

    public String UserGetInfo(String msisdn) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_LINK + "user/info";
            String strPostData = "{"
                    + "\"msisdn\":\"" + msisdn + "\","
                    + "\"serviceCode\":" + SERVICE_ID + ""
                    + "}";
            String strJsonContent = fc.PostJson(strPostData, url_api);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            // System.out.println("vnpt.data.api.api_home.UserGetInfo(==strPostData:" + strPostData + "===strJsonContent:" + strJsonContent + "========)");
            if (sok.equals("0")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONObject employeeObject = (JSONObject) obj.get("result");

                String userName = "";
                String createDate = "";
                String facebookId = "";
                String gmailId = "";
                String image = "";
                String name = "";
                String nickName = "";
                String gender = "";
                String birthday = "";
                String fbName = "";
                String ggName = "";
                String email = "";
                String isRevNoti = "";
                String packageDto = "";

                try {

                    userName = (String) employeeObject.get("userName");
                    createDate = (String) employeeObject.get("createDate");
                    facebookId = (String) employeeObject.get("facebookId");
                    gmailId = (String) employeeObject.get("gmailId");
                    image = (String) employeeObject.get("image");
                    name = (String) employeeObject.get("name");
                    birthday = (String) employeeObject.get("birthday");
                    nickName = (String) employeeObject.get("nickName");
                    gender = (String) employeeObject.get("gender");
                    fbName = (String) employeeObject.get("fbName");
                    ggName = (String) employeeObject.get("ggName");
                    email = (String) employeeObject.get("email");
                    isRevNoti = (String) employeeObject.get("isRevNoti");
                    packageDto = (String) employeeObject.get("packageDto");

                } catch (Exception e) {
                }

                // System.out.println("vnpt.data.api.api_user.UserGetInfo(----image:" + image + "-----userName:" + userName + "--------------)");
                strValues += "<div class=\"user_info_list\">\n"
                        + "    Số điện thoại : " + msisdn + "                                     \n"
                        + "</div>\n"
                        + "<div class=\"user_info_list\">\n"
                        + "    Tên hiện thị : " + name + "                                  \n"
                        + "</div>\n"
                        + "<div class=\"user_info_list\">\n"
                        + "    Ngày sinh : " + birthday + "            \n"
                        + "</div>\n"
                        + "<div class=\"user_info_list\">\n"
                        + "    Giới tính : Nam                                     \n"
                        + "</div>\n"
                        + "\n"
                        + "<div class=\"user_info_list\">\n"
                        + "    Liên hết tài khoản                              \n"
                        + "</div>\n"
                        + "\n"
                        + "\n"
                        + "\n"
                        + "<div class=\"user_info_list\">\n"
                        + "    <span uk-icon=\"icon: google; ratio: 1\"></span> \n"
                        + "    /minhhsvnpt@gmail.com\n"
                        + "</div>\n"
                        + "\n"
                        + "<div class=\"user_info_list\">\n"
                        + "    <span uk-icon=\"icon: facebook; ratio: 1\"></span>    \n"
                        + "    /minhhs.2334\n"
                        + "</div>";

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strValues;
    }

    public String UserUpdateInfo(String msisdn, String service_id, String name, String birthday, String gender, String email) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_CMS + "api_update_user";

//            msisdn = fc.msisdn84(msisdn);
            String strPostData = "{"
                    + "\"phone\":\"" + msisdn + "\","
                    + "\"service_id\":\"" + service_id + "\","
                    + "\"name\":\"" + name + "\","
                    + "\"birthday\":\"" + birthday + "\","
                    + "\"email\":\"" + email + "\","
                    + "\"gender\":\"" + gender + "\""
                    + "}";

            String strJsonContent = fc.PostJson2(url_api, strPostData);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            System.out.println("vnpt.data.api.api_home.UserGetInfo(===sok:" + sok + "===url_api:" + url_api + "======strPostData:" + strPostData + "===strJsonContent:" + strJsonContent + "========)");
            strValues = sok;

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return strValues;
    }

    public String UserUpdateInfoOld(String msisdn, String name, String birthday, String genre, String email) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_LINK + "user/update_info";

            msisdn = fc.msisdn84(msisdn);
            String strPostData = "{"
                    + "\"msisdn\":\"" + msisdn + "\","
                    + "\"serviceCode\":" + SERVICE_ID + ","
                    + "\"name\":\"" + name + "\","
                    + "\"nickName\":\"" + name + "\","
                    + "\"birthday\":\"" + birthday + "\","
                    + "\"email\":\"" + email + "\","
                    + "\"genre\":" + genre + ""
                    + "}";
            String strJsonContent = fc.PostJson(strPostData, url_api);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            System.out.println("vnpt.data.api.api_home.UserGetInfo(===sok:" + sok + "===url_api:" + url_api + "======strPostData:" + strPostData + "===strJsonContent:" + strJsonContent + "========)");
            strValues = sok;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strValues;
    }

    public void UserGetInfoDetail(String msisdn, String service_id) {

        try {
            Function fc = new Function();
            String url_api = API_CMS;
            String strPostData = "api_user?username=" + msisdn + "&service_id=" + service_id + "&action=list_by_username";
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 0);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            //System.out.println("alert(======msisdn:" + msisdn + "=======url_api:" + url_api + "====strPostData:" + strPostData + "===strJsonContent:" + strJsonContent + "========)");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();
                for (int i = 0; i < dem; i++) {
                    try {
                        String phoneNumber = cf.CheckNull(arr.getJSONObject(i).getString("phoneNumber"));
                        String fullName = cf.CheckNull(arr.getJSONObject(i).getString("fullName"));
                        String birthDay = cf.CheckNull(arr.getJSONObject(i).getString("birthDay"));
                        String gender = cf.CheckNull(arr.getJSONObject(i).getString("gender"));
                        String email = cf.CheckNull(arr.getJSONObject(i).getString("email"));

                        this.setBirthday(birthDay);
                        this.setEmail(email);
                        this.setGender(gender);
                        this.setName(fullName);
                        this.setMsisdn(phoneNumber);
                    } catch (Exception e) {
                        System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                    }
                }

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }
    }

    public void UserGetInfoDetailByUser(String username, String service_id) {

//        api_user tmp = null;
        try {
            Function fc = new Function();
            String url_api = API_CMS;
            String strPostData = "api_user?username=" + username + "&service_id=" + service_id + "&action=list_by_username";
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 0);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
//            System.out.println("alert(======msisdn:" + msisdn + "=======url_api:" + url_api + "====strPostData:" + strPostData + "===strJsonContent:" + strJsonContent + "========)");
            if (sok.equals("1")) {
                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");
                int dem = arr.length();
                for (int i = 0; i < dem; i++) {
                    try {
                        String phoneNumber = cf.CheckNull(arr.getJSONObject(i).getString("phoneNumber"));
                        String fullName = cf.CheckNull(arr.getJSONObject(i).getString("fullName"));
                        String birthDay = cf.CheckNull(arr.getJSONObject(i).getString("birthDay"));
                        String gender = cf.CheckNull(arr.getJSONObject(i).getString("gender"));
                        String email = cf.CheckNull(arr.getJSONObject(i).getString("email"));

                        this.setBirthday(birthDay);
                        this.setEmail(email);
                        this.setGender(gender);
                        this.setName(fullName);
                        this.setMsisdn(phoneNumber);
                    } catch (Exception e) {
                        System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
                    }
                }

            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }
    }
}
