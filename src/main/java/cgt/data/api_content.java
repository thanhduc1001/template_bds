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
public class api_content {

    private String viewCount;
    private String likeCount;
    private String commentCount;
    private String shareCount;
    private String title;
    private String image;
    private String audioLink;
    private String videoLink;
    private String description;
    private String content;
    private String author;
    private String liveLink;
    private String createDate;
    private String emote;
    private String cost;
    private String totalChapter;
    private String currentChapter;
    private String hot;
    private String type;
    private String subjectCode;
    private String subjectName;
    private String linkShare;
    private String duration;
    private String typeLive;
    private String isSaved;
    private String rate;
    private String totalRate;
    private String totalUserRate;
    private String hashTags;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getHashTags() {
        return hashTags;
    }

    public void setHashTags(String hashTags) {
        this.hashTags = hashTags;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLiveLink() {
        return liveLink;
    }

    public void setLiveLink(String liveLink) {
        this.liveLink = liveLink;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEmote() {
        return emote;
    }

    public void setEmote(String emote) {
        this.emote = emote;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTotalChapter() {
        return totalChapter;
    }

    public void setTotalChapter(String totalChapter) {
        this.totalChapter = totalChapter;
    }

    public String getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(String currentChapter) {
        this.currentChapter = currentChapter;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getLinkShare() {
        return linkShare;
    }

    public void setLinkShare(String linkShare) {
        this.linkShare = linkShare;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTypeLive() {
        return typeLive;
    }

    public void setTypeLive(String typeLive) {
        this.typeLive = typeLive;
    }

    public String getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(String isSaved) {
        this.isSaved = isSaved;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(String totalRate) {
        this.totalRate = totalRate;
    }

    public String getTotalUserRate() {
        return totalUserRate;
    }

    public void setTotalUserRate(String totalUserRate) {
        this.totalUserRate = totalUserRate;
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
    private String SERVICE_ID = config.ConfigInfoCache("SERVICE_ID");
    private String API_CMS = config.ConfigInfoCache("API_CMS");

    public String GetListContent(String msisdn, String subjectCode, String pageSize, String contentType, String pageNumber, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_LINK + "contents";
            String strPostData = "{"
                    + "\"msisdn\":\"" + msisdn + "\","
                    + "\"serviceCode\":" + service_id + ","
                    + "\"subjectCode\":\"" + subjectCode + "\","
                    + "\"pageSize\":\"" + pageSize + "\","
                    + "\"contentType\":" + contentType + ","
                    + "\"pageNumber\":" + pageNumber + ""
                    + "}";
            String strJsonContent = fc.PostJson(strPostData, url_api);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");

            if (sok.equals("1")) {
                strValues = strJsonContent;
            }

        } catch (Exception e) {
            System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
        }

        return strValues;
    }

    // danh sach tin da luu
    public String GetListContentSavexxxx(String msisdn, String subjectCode, String pageSize, String contentType, String pageNumber, String service_id) {
        String strValues = "";
        try {
            Function fc = new Function();
            String url_api = API_LINK + "contents";
            String strPostData = "{"
                    + "\"msisdn\":\"" + msisdn + "\","
                    + "\"serviceCode\":" + service_id + ","
                    + "\"subjectCode\":\"" + subjectCode + "\","
                    + "\"pageSize\":\"" + pageSize + "\","
                    + "\"contentType\":" + contentType + ","
                    + "\"pageNumber\":" + pageNumber + ""
                    + "}";
            String strJsonContent = fc.PostJson(strPostData, url_api);
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");

            if (sok.equals("1")) {
                strValues = strJsonContent;
            }

        } catch (Exception e) {
            System.out.println("Loi doc du lieu tu API. Vui long kiem tra lai!");
        }

        return strValues;
    }

    public api_content getDetailv2(String msisdn, String contentId, String subjectCode, String service_id) {
        api_content tmp = null;
        msisdn = cf.CheckNull(msisdn);
        try {
            Function fc = new Function();
            String url_api = API_CMS + "";

            String max = "1";
            String page = "1";
            String strPostData = "api_content?action=list&service_id=" + service_id + "&id=" + contentId;
            String strJsonContent = fc.PostJsonAuth(strPostData, url_api, 60);

//            System.out.println("vnpt.data.api.api_content.getDetail(----strPostData:" + strPostData + "---strJsonContent:" + strJsonContent + "------------------)");
            String sok = cf.getJsonINT1(strJsonContent, "errorCode");
            if (sok.equals("1")) {

                JSONObject obj = new JSONObject(strJsonContent);
                JSONArray arr = (JSONArray) obj.get("result");

                String contentID = arr.getJSONObject(0).getInt("contentID") + "";
                String image = cf.CheckNull(arr.getJSONObject(0).getString("image"));
                if (image.endsWith("images/")) {
                    image = "images/noimage.jpg";
                }
                String title = cf.CheckNull(arr.getJSONObject(0).getString("title"));
                String description = arr.getJSONObject(0).getString("description");
                String createDate = (arr.getJSONObject(0).getString("createDate"));
                String subjectCodeDetail = arr.getJSONObject(0).getString("subjectCode");

                String viewCount = "0";

                String audioLink = arr.getJSONObject(0).getString("audioLink");
                String videoLink = arr.getJSONObject(0).getString("videoLink");

                String content = arr.getJSONObject(0).getString("content");
                String author = arr.getJSONObject(0).getString("author");
                subjectCode = arr.getJSONObject(0).getString("subjectCode");
                String subjectName = arr.getJSONObject(0).getString("subjectName");

                String commentCount = "0";;
                String isSaved = "0";;
                String hashTags = "";

                tmp = new api_content();
                tmp.setViewCount(viewCount);
                tmp.setTitle(title);
                tmp.setDescription(description);
                tmp.setImage(image);
                tmp.setAudioLink(audioLink);
                tmp.setVideoLink(videoLink);
                tmp.setContent(content);
                tmp.setCommentCount(commentCount);
                tmp.setCreateDate(createDate);
                tmp.setAuthor(author);
                tmp.setIsSaved(isSaved);
                tmp.setSubjectCode(subjectCode);
                tmp.setSubjectName(subjectName);
                tmp.setHashTags(hashTags);
            }

        } catch (Exception ex) {
            System.out.println("Chua lay duoc du lieu tu API. Vui long kiem tra lai trong file config!");
        }

        return tmp;
    }
}
