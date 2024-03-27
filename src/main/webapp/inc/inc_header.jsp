<%-- 
    Document   : inc_header
    Created on : Dec 19, 2023, 3:45:21 PM
    Author     : Admin
--%>

<%@page import="cgt.data.api_web_config"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    api_web_config awcheader = (api_web_config) session.getAttribute("web_config");
    String iconTemplate = "";
    if(awcheader!=null){
        iconTemplate = awcheader.getLOGO();
    }
%>

<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
<!--CSS-->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- UIkit CSS -->
<link rel="stylesheet" href="css/uikit.min.css" />
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="css/sport.css">
<!--JS-->
<script src="js/defer_plus.min.js"></script>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/jquery-aj.js"></script>
<!-- UIkit JS -->
<script src="js/uikit.min.js"></script>
<script src="js/uikit-icons.min.js"></script>

<link rel="shortcut icon" href="<%=iconTemplate%>">
 
 
<script>
    function detectMob() {
        const toMatch = [
            /Android/i,
            /webOS/i,
            /iPhone/i,
            /iPad/i,
            /iPod/i,
            /BlackBerry/i,
            /Windows Phone/i
        ];

        return toMatch.some((toMatchItem) => {
            return navigator.userAgent.match(toMatchItem);
        });
    }

    var checkm = detectMob();
    if (checkm === true) {
        var link = window.location.href;
        console.log('xxxx=>' + link + '<==xxxxxx');
        if (link.startsWith("https")) {
            //window.location.href = "http://nhanthuc.vsport.com.vn/login3g4g?code=" + link + "&url=https://vsport.com.vn/";
        }
    }
    ajaxshow('user_check', '');
</script>

