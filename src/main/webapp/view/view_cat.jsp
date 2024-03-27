<%@include file="/inc/inc_get_config.jsp" %>
<%@page import="cgt.data.api_cat"%>
<%--<%@page import="cgt.data.api_page"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../inc/inc_check.jsp" %>
<%    Function cf = new Function();
//    api_page ap = new api_page();
    api_cat ac = new api_cat();
    api_home ahc = new api_home();
    String strService4 = (String)session.getAttribute("service_id");
    String cat = cf.formatInput((request.getParameter("cat") == null) ? "" : request.getParameter("cat").trim());
    String strpage = cf.formatInputInt((request.getParameter("page") == null) ? "0" : request.getParameter("page").trim());
    String strpagesize = "20";

    String strTitle = "";
    String msisdn = "";
    String strCatCode = "";
    String strCatName = "";
    String strCatNameCha = "";
    String strContentPage = "";
    String strContentType = "1";
    String strHideRight = "0";

    String strContent = "";
    String strContentList = "";
    String strContentLinkShare = "";
    String strContentCatNameSub = "";
    String strContentImage = "images/default.png";

    strCatName = ahc.GetCatName(cat, strService4);

    if (cat.startsWith("tin-tuc")) {
        strContent = ac.GetListContentCatV2(msisdn, cat, strContentType, cat, strpage, strpagesize, strService4);
    }else if (cat.startsWith("phim")){
        strContent = ac.GetListContentCatV3(msisdn, cat, strContentType, cat, strpage, strpagesize, strService4);
    }

    strTitle = strCatName;

    System.out.println("className.methodName(----cat:" + cat + "-----strpage:"+strpage+"------)");

    strContentLinkShare = "view-" + cat + ".html";
    strContentCatNameSub = strCatName;
    //=============SEO website =================
    String seo_title = strTitle;
    String seo_site_name = "vlive";
    String seo_description = "vlive " + strTitle + "  - Website vlive.com.vn chuyên cung cấp thông tin giải trí tổng họp cập nhật thường xuyên 24/7  ";
    String seo_canonical = strContentLinkShare; // link bai viet
    String seo_url = strContentLinkShare;
    String seo_section = strContentCatNameSub; // ten chuyen muc
    String seo_image = strContentImage;
    //
    //<meta property="og:updated_time" content="2021-03-17T11:15:34+07:00">
    //<meta property="article:published_time" content="2021-02-05GMT+070014:20:49+07:00">
    //<meta property="article:modified_time" content="2021-03-17GMT+070011:15:34+07:00">
    //=============SEO website =================
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
        <meta property="article:tag" content="thể thao 247">
        <meta property="article:tag" content="vlive">
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
        <link rel="stylesheet" href="css/sport.css">
        <!-- Web SEO plugin -->
        <%@include file="../inc/inc_header.jsp" %>
    </head>
    <body>    
        <%@include file="../inc/inc_top.jsp" %>

 <div class=" uk-visible@m" style="background-image: linear-gradient(to bottom,#d9f2f9,#ffffff,#ffffff); height: 40px"> </div>
      
<!--        <div class="uk-animation-slide-top-medium uk-padding-small  uk-visible@m" style="font-size: 40px ; color: #000000; font-weight: 600; text-align: center; padding-bottom: 30px; margin-top: 0px">
           <span style="border-bottom: solid 4px #1B9F02"><%=strCatName%></span> 
        </div>-->


        <section id="app" class="uk-height-viewport uk-offcanvas-content uk-overflow-hidden uk-position-relative">
            <div class="uk-section-small home__conTent" >
                <div class="uk-container">
                    <div class="home__margin1 uk-padding-top-small uk-hidden" style="padding-top: 20px">
                        <div class="uk-child-width-auto uk-grid-small uk-flex-middle uk-grid" uk-grid="">
                            <div class="uk-first-column" ><em>
                                    <a href="./" class="detail_menu_top"> Trang chủ </a>
                                    <span class="detail_menu_top"> > </span> 
                                    <a href="<%=strContentLinkShare%>" class="detail_menu_top"><%=strContentCatNameSub%></a>
                                </em>
                            </div>                         
                        </div>
                    </div>

                    <div class="uk-grid-small uk-grid-20-m" uk-grid>
                        <div class="uk-width-expand">
                            <%=strContent%>
                            <%if (strContentPage != null && !strContentPage.equals("")) {%>                            
                            <div class="uk-card uk-card-default uk-card-body">                                
                                <div class="uk-text-center uk-animation-slide-top" style="font-weight: bold">
                                    <h1><%=strCatName%></h1>
                                </div>

                                <div class="uk-animation-slide-bottom">

                                    <%=strContentPage%>
                                </div> 
                            </div>
                            <%}%>

                        </div>

                    </div>


                </div>
            </div>


            <%@include file="../inc/inc_footer.jsp" %>
        </section>
    </body>  
</html>