<%@page import="cgt.functions.Function"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Function cfhc = new Function();
    String ccsmsisdn = (String) session.getAttribute("msisdn");

    //===========show log start================
    Date dNowh = new Date();
    SimpleDateFormat fth = new SimpleDateFormat("dd/MM/yyyy HH:MM:ss.SSS");
    String strTimeStart = fth.format(dNowh);
   // System.out.println("----" + strTimeStart + " start web vinasport -----------------------------------------------");
    //===========show log start================

//    String strUrlNhanThuc = "http://nhanthuc.vsport.com.vn/login";
//    String strDT = cfhc.PostJson("", strUrlNhanThuc);
//    System.out.println("className.methodName(-----strDT:" + strDT + "---------------------)");
//    if (strDT != null & !strDT.equals("")) {
//       // session.setAttribute("msisdn", strDT);
//    }
//    session.setAttribute("msisdn", "84914871268");
//    session.setAttribute("login", "84943125150");

  //  session.setAttribute("msisdn", "84948841088");
//    session.setAttribute("login", "84948841088");
//    
    //========================== get thue bao dung 3g 4g ===================  
    if (ccsmsisdn != null && !ccsmsisdn.equals("")) {
    } else {
        String ccmsisdn = request.getHeader("MSISDN");
        String xIP = request.getHeader("x-real-ip");
        if (xIP != null && xIP.startsWith("113.185.")) {
            if (ccmsisdn != null && !ccmsisdn.equals("")) {
                session.setAttribute("msisdn", ccmsisdn);
                session.setAttribute("login", ccmsisdn);
            }
            System.out.println("====user check session 4G to msisdn:" + ccmsisdn + "====xIP" + xIP + "========)");
        }
    }
    //========================== get thue bao dung 3g 4g ===================  

%> 