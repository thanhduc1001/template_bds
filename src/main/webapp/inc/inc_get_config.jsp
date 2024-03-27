<%-- 
    Document   : inc_get_config
    Created on : Jan 10, 2024, 11:00:12 AM
    Author     : Admin
--%>

<%@page import="cgt.data.api_web_config"%>
<%@page import="cgt.functions.Function"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Function fcget1 = new Function();
    fcget1.initAccessToken();
    String stoken = fcget1.Encypt_MD5(fcget1.getTimeID());
    session.setAttribute("loginkey", stoken);
    //Lấy url hiện tại
    String currentUrl1 = request.getRequestURL().toString();
    
    //Lấy ra domain từ  url
    String domaincf = fcget1.getDomain(currentUrl1);

    
    //lấy cấu hình website
    if(session.getAttribute("web_config")==null) {
        api_web_config awc_cf = new api_web_config();
        awc_cf.SetConfig(domaincf);
        session.setAttribute("web_config", awc_cf);
        String service_id = awc_cf.getSERVICE_ID();
        session.setAttribute("service_id", service_id);
    }
   
%>

