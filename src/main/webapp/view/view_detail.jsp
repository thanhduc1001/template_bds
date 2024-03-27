<%-- 
    Document   : view_detail
    Created on : Dec 20, 2023, 4:38:27 PM
    Author     : Admin
--%>
<%@include file="/inc/inc_get_config.jsp" %>
<%@page import="cgt.data.api_user"%>
<%@page import="cgt.data.api_detail" %>
<%@page import="cgt.data.api_content" %>
<%@include file="../inc/inc_check.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    Function cf = new Function();
    api_content ac = new api_content();
    api_user au = new api_user();
    String ssmsisdn = (String) session.getAttribute("msisdn");
    String loginkeydetail = (String) session.getAttribute("loginkey");
    String strService5 = (String) session.getAttribute("service_id");

    String contentId = cf.formatInputIntAz((request.getParameter("id") == null) ? "" : request.getParameter("id").trim());
    String subjectCode = cf.formatInput((request.getParameter("cat") == null) ? "" : request.getParameter("cat").trim());

    String strTitle = "";
    String msisdn = ssmsisdn;
    String strMyLinkDomain = request.getRequestURL().toString();
    strMyLinkDomain = strMyLinkDomain.replaceAll("view/view_detail.jsp", "");

    String strContent = "";
    String strContentList = "";

    String strContentTitle = "";
    String strContentDes = "";
    String strContentDetail = "";
    String strContentTime = "";
    String strContentAuthor = "";
    String strContentCat = "";
    String strContentCatNameSub = "";
    String strContentCatLink = "";
    String strContentView = "";
    String strContentDate = "";
    String strContentSave = "";
    String strContentLinkShare = "";
    String strContentImage = "";
    String strContentTag = "";
    String strContentVideo = "";
    String strContentAudio = "";

    ac = ac.getDetailv2(msisdn, contentId, subjectCode, strService5);
    if (ac != null) {
        strContentTitle = ac.getTitle();
        strContentDes = ac.getDescription();
        strContentDetail = ac.getContent();
        strContentTime = ac.getCreateDate();
        strContentAuthor = ac.getAuthor();
        strContentCat = ac.getSubjectCode();
        strContentCatNameSub = ac.getSubjectName();
        strContentView = ac.getViewCount();
        strContentDate = ac.getCreateDate();
        strContentSave = ac.getIsSaved();
        strContentImage = ac.getImage();
        strContentTag = ac.getHashTags();
        subjectCode = ac.getSubjectCode();
        strContentVideo = ac.getVideoLink();
        strContentAudio = ac.getAudioLink();

        strContentCatLink = "view-" + subjectCode + ".html";
        strContentLinkShare = strMyLinkDomain + cf.UrlVn(strContentTitle) + "-n" + contentId + ".html";

        //==============SEO===============
        strTitle = strContentTitle + "";
        //==============SEO===============
        // strContentAudio = "http://113.185.0.70:81/voicd/content/VNP/D/42/2021/9/292763977/292763977@Bu%E1%BB%93n_L%C3%A0m_Chi_Em_%C6%A0i_REMIX_(%C4%90%E1%BA%A1i_M%C3%A8o_Remix)_Ho%C3%A0i_L%C3%A2m.wav";
    }

    //===============show noi dung lien quan =================
    String strListRelate = "";
    api_detail adetail = new api_detail();
    strListRelate = adetail.GetListRelateV2(msisdn, subjectCode, "1", contentId, "0", "10", strService5);
    //===============show noi dung lien quan =================

    System.out.println("xxxxxxxx--strContentAudio:" + strContentAudio + "-strContentVideo:" + strContentVideo + "---------strMyLinkDomain:" + strMyLinkDomain + "-------subjectCode:" + subjectCode + "---contentId:" + contentId + "---strContentTag:" + strContentTag + "---------)");
    //=============SEO website =================
    String seo_title = strTitle;
    String seo_site_name = "vlive";
    String seo_description = strContentDes;
    String seo_canonical = strContentLinkShare; // link bai viet
    String seo_url = strContentLinkShare;
    String seo_section = strContentCatNameSub; // ten chuyen muc
    String seo_image = strContentImage;
    //
    //<meta property="og:updated_time" content="2021-03-17T11:15:34+07:00">
    //<meta property="article:published_time" content="2021-02-05GMT+070014:20:49+07:00">
    //<meta property="article:modified_time" content="2021-03-17GMT+070011:15:34+07:00">
    //=============SEO website =================

    if (ssmsisdn != null && !ssmsisdn.equals("")) {
        String strCheckPack = au.CheckPackV2(ssmsisdn, strService5, contentId);
//        System.out.println("className.methodName(----strCheckPack:" + strCheckPack + "------------)");
        if (strCheckPack.equals("1")) {
        } else {
            strContentDes = "";
            strContentVideo = "";
            strContentAudio = "";
            strContentDetail = "<div class=\"uk-alert-warning\" uk-alert>\n"
                    + "  <a class=\"uk-alert-close\" uk-close></a>\n"
                    + "  <p>Bạn không thể xem nội dung này ! Vui lòng đăng ký gói cước: <a href=\"goi-cuoc.html\">tại đây</a>  </p>\n"
                    + "</div>";
            strContentAuthor = "";
        }
    }
    else {
        String strCheckPack = au.CheckPackV2(ssmsisdn, strService5, contentId);
//        System.out.println("className.methodName(----strCheckPack:" + strCheckPack + "------------)");
        if (strCheckPack.equals("1")) {
        } else {
            strContentDes = "";
            strContentVideo = "";
            strContentAudio = "";
            strContentDetail = "<div class=\"uk-alert-warning\" uk-alert>\n"
                    + "  <a class=\"uk-alert-close\" uk-close></a>\n"
                    + "  <p>Bạn không thể xem nội dung này ! Vui lòng đăng ký gói cước: <a href=\"goi-cuoc.html\">tại đây</a>  </p>\n"
                    + "</div>";
            strContentAuthor = "";
        }
    }
%>


<!DOCTYPE html>
<html lang="en"> 
    <head>
        <title><%=strTitle%></title>

        <!-- Web SEO plugin -->
        <meta name="description" content="<%=seo_description%>"/>
        <meta name="robots" content="follow, index, max-snippet:-1, max-video-preview:-1, max-image-preview:large"/>
        <link rel="canonical" href="<%=seo_canonical%>" />
        <meta property="og:locale" content="vi_VN">
        <meta property="og:type" content="article">
        <meta property="og:title" content="<%=seo_title%>">
        <meta property="og:description" content="<%=seo_description%>">
        <meta property="og:url" content="<%=seo_url%>">
        <meta property="og:site_name" content="<%=seo_site_name%>">        
        <meta property="article:section" content="<%=seo_section%>">
        <meta property="og:image" content="<%=seo_image%>">
        <meta property="og:image:secure_url" content="<%=seo_image%>">
        <meta property="og:image:width" content="1335">
        <meta property="og:image:height" content="1093">
        <meta property="og:image:alt" content="<%=seo_title%>">
        <meta property="og:image:type" content="image/jpeg">
        <meta name="twitter:card" content="summary_large_image">
        <meta name="twitter:title" content="<%=seo_title%>">
        <meta name="twitter:description" content="<%=seo_description%>">
        <meta name="twitter:image" content="<%=seo_image%>">
        <!-- Web SEO plugin -->

        <%@include file="../inc/inc_header.jsp" %>
    </head>
    <body class="isHome">  
        <%@include file="../inc/inc_top.jsp" %>

        <div class=" uk-visible@m" style="background-image: linear-gradient(to bottom,#d9f2f9,#ffffff,#ffffff); height: 40px"> </div>

        <section id="app" class=" uk-offcanvas-content uk-overflow-hidden uk-position-relative">
            <div class="uk-section-small home__conTent" >
                <div class="uk-container">

                    <div class="uk-grid-small uk-grid-55-m" uk-grid>
                        <div class="uk-width-expand">


                            <div class="home__margin1" style="padding-top: 20px">
                                <div class="uk-child-width-auto uk-grid-small uk-flex-middle uk-grid" uk-grid="">
                                    <div class="uk-first-column" ><em>
                                            <a href="./" class="detail_menu_top"> Trang chủ </a>
                                            <span class="detail_menu_top"> > </span> 
                                            <!--                                            <a href="./" class="detail_menu_top"> Tin tức </a>
                                                                                        <span class="detail_menu_top"> > </span> -->
                                            <a href="<%=strContentCatLink%>" class="detail_menu_top"><%=strContentCatNameSub%></a>
                                        </em>
                                    </div>
                                    <div>

                                    </div>
                                </div>
                            </div>




                            <div class="detail_line_boder"></div>
                            <h3 class="detail_title"><b><%=strContentTitle%></b> </h3> 
                            <div class="detail_time"><%=strContentTime%></div>
                            <div class="detail_line_boder"></div>
                            <div class="detail_des"><%=strContentDes%></div>

                            <%if (strContentVideo.length() > 44) {%>
                            <%if (strContentVideo.endsWith(".m3u8")) {%>
                            <div id="videoContainer" class="videoCentered" style="width: 100%; height: 450px" ></div>  
                            <%} else {%>

                            <video poster="<%=strContentImage%>"
                                   src="<%=strContentVideo%>" type="video/mp4" autoplay="" controls="" height="415"
                                   style="width: 100%; height: 475px"  ></video>
                                <%}%>
                                <%}%>


                            <%if (strContentAudio.length() > 44) {%>
                            <div class="uk-grid-collapse uk-background-default uk-box-shadow-medium uk-grid-match home__box3 uk-grid uk-padding-small uk-margin"   >
                                <audio style="width: 100%;"  controls autoplay>
                                    <source src="<%=strContentAudio%>"   type="audio/mp3">
                                </audio>
                            </div>
                            <%}%>

                            <div class="detail_content"><%=strContentDetail%></div>
                            <div class="detail_author"><%=strContentAuthor%></div>



                            <hr>

                            <!--noi dung lien quan-->
                            <div class="home__margin">

                                <div class="uk-width-expand">
                                    <div class="home__margin1">
                                        <div class="uk-child-width-auto uk-grid-small uk-flex-middle" uk-grid>
                                            <div>
                                                <h3 class="uk-h3 uk-margin-remove uk-text-uppercase home__box4__title">Tin xem thêm</h3>
                                            </div>
                                            <div>
                                                <img class="home__box4__img" src="images/Group12.png" alt="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <%=strListRelate%>
                                </div>

                            </div>
                            <!--noi dung lien quan-->

                        </div>
                    </div>
                </div>
            </div>
            <script>
                function CopyLink() {
                    document.getElementById("txtLinkShare").style.display = "block";
                    var copyText = document.getElementById("txtLinkShare");
                    copyText.select();
                    copyText.setSelectionRange(0, 99999);
                    document.execCommand("copy");
                    document.getElementById("txtLinkShare").style.display = "none";
                }
                function  Comment() {
                    var content = document.getElementById("txtContent").value;
                    ajaxshow('user_save?act=comment&content=' + content + '&subjectCode=<%=subjectCode%>&id=<%=contentId%>&key=<%=loginkeydetail%>', 'ShowComment');
                    document.getElementById("txtContent").value = "";
                    document.getElementById("ShowCommentAlert").innerHTML = "Thành công ! Bình luận của bạn đang được chờ duyệt ";
                }
                function SaveContent() {
                    ajaxshow('user_save?act=content&status=<%=strContentSave%>&subjectCode=<%=subjectCode%>&id=<%=contentId%>&key=<%=loginkeydetail%>', 'ShowSaveContent');
                }
            </script>
            <%@include file="../inc/inc_footer.jsp" %>
        </section>
    </body>  
</html>
