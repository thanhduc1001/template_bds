<%@include file="inc/inc_get_config.jsp" %>
<%@page import="cgt.data.api_web_config"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="inc/inc_check.jsp" %>
<%@page import="cgt.functions.Function"%>
<%    api_web_config awcid = (api_web_config) session.getAttribute("web_config");

//=============SEO website =================
    String seo_title = "";
    if (awcid != null) {
        seo_title = awcid.getTITLE();
    }
    String seo_site_name = "";
    String seo_description = "";
    String seo_canonical = "";
    String seo_url = "";
    String seo_section = ""; // ten chuyen muc
    String seo_image = "";
    //
    //<meta property="og:updated_time" content="2021-03-17T11:15:34+07:00">
    //<meta property="article:published_time" content="2021-02-05GMT+070014:20:49+07:00">
    //<meta property="article:modified_time" content="2021-03-17GMT+070011:15:34+07:00">
    //=============SEO website =================


%>
<!DOCTYPE html>
<html lang="en"> 
    <head>
        <title><%=seo_title%></title>

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
        <!-- Web SEO plugin -->

        <%@include file="inc/inc_header.jsp" %>
    </head>
    <body class="isHome">

        <%@include file="inc/inc_top.jsp" %>

        <section id="app"  class="uk-height-viewport uk-offcanvas-content uk-overflow-hidden uk-position-relative">

            <%@include file="inc/inc_home.jsp" %>
            <%@include file="inc/inc_footer.jsp" %>
        </section>
        <script>


        </script>
    </body>  
</html>
