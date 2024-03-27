<%@include file="/inc/inc_get_config.jsp" %>
<%@page import="cgt.data.api_cat"%>
<%@page import="cgt.data.api_page"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../inc/inc_check.jsp" %>
<%    Function cf = new Function();
    api_page ap = new api_page();
    api_cat ac = new api_cat();

    String cid = cf.formatInputIntAz((request.getParameter("cid") == null) ? "" : request.getParameter("cid").trim());
    String cat = cf.formatInput((request.getParameter("cid") == null) ? "" : request.getParameter("cid").trim());
    String strService7 = (String) session.getAttribute("service_id");
    String strpage = cf.formatInputInt((request.getParameter("page") == null) ? "0" : request.getParameter("page").trim());
    String pageCode = cf.formatInput((request.getParameter("pageCode") == null) ? "" : request.getParameter("pageCode").trim());
    String pageId = cf.formatInput((request.getParameter("pageId") == null) ? "" : request.getParameter("pageId").trim());
    String strpagesize = "20";

    String msisdn = "";
    String strCatCode = "";
    String strCatName = "";
    String strContentPage = "";

    String strContent = "";
    String strContentList = "";
    String strContentLinkShare = "";
    String strContentCatNameSub = "";
    String strContentImage = "images/default.png";

    System.out.println("className.methodName(----cat:" + cat + "---cid:" + cid + "---------)");

    strContentLinkShare = "p-" + pageCode + ".html";
    strContentCatNameSub = strCatName;
    strContent = ap.GetPageInfo(msisdn, pageCode, strService7, pageId);
    String strTitle = ap.GetPageTitle(msisdn, pageCode, strService7, pageId);
    //=============SEO website =================
//    String seo_title = ap.GetPageTitle(msisdn, pageCode, strService7, pageId);
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
        <meta property="og:title" content="<%=strTitle%>">
        <meta property="og:description" content="<%=seo_description%>">
        <meta property="og:url" content="<%=seo_url%>">
        <meta property="og:site_name" content="<%=seo_site_name%>">       
        <meta property="article:section" content="<%=seo_section%>">
        <meta property="og:image" content="<%=seo_image%>">
        <meta property="og:image:secure_url" content="<%=seo_image%>">
        <meta property="og:image:width" content="1335">
        <meta property="og:image:height" content="1093">
        <meta property="og:image:alt" content="<%=strTitle%>">
        <meta property="og:image:type" content="image/jpeg">
        <meta name="twitter:card" content="summary_large_image">
        <meta name="twitter:title" content="<%=strTitle%>">
        <meta name="twitter:description" content="<%=seo_description%>">
        <meta name="twitter:image" content="<%=seo_image%>">
        <!-- Web SEO plugin -->
        <%@include file="../inc/inc_header.jsp" %>
    </head>
    <body>    
        <%@include file="../inc/inc_top.jsp" %>
        <div class=" uk-visible@m" style="background-image: linear-gradient(to bottom,#d9f2f9,#ffffff,#ffffff); height: 40px"> </div>


        <section id="app" class=" uk-offcanvas-content uk-overflow-hidden uk-position-relative">


            <div class="uk-section-small home__conTent" >
                <div class="uk-container">

                    <div class="uk-grid-small uk-grid-20-m" uk-grid>
                        <div class="uk-width-expand">
                            <%=strContent%>
                        </div>
                    </div>
                </div>
            </div>


            <%@include file="../inc/inc_footer.jsp" %>
        </section>
    </body>  
</html>