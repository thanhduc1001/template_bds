<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%   
     String msisdn_check = (String) session.getAttribute("msisdn");
     String user_check = (String) session.getAttribute("msisdn");
     if((msisdn_check!= null && !msisdn_check.equals("")) || (user_check!=null && !user_check.equals("")) ){}else{  
%> 
<script>location = './login.html';</script>
<%return; }%>