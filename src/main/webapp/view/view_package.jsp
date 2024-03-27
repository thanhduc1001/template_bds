<%@include file="/inc/inc_get_config.jsp" %>
<%@page import="cgt.data.api_user"%>
<%@page import="cgt.data.api_package"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../inc/inc_check.jsp" %>
<%    Function cf = new Function();
//    api_user au = new api_user();
    api_package ap = new api_package();
    api_home ahc = new api_home();

    String ssmsisdn = (String) session.getAttribute("msisdn");
    String sgmail = (String) session.getAttribute("email");
    String semail_id = (String) session.getAttribute("gmail_id");
    String strService6 = (String) session.getAttribute("service_id");
    String cat = cf.formatInput((request.getParameter("cid") == null) ? "" : request.getParameter("cid").trim());
    String registedPack = cf.formatInput((request.getParameter("rpack") == null) ? "" : request.getParameter("rpack").trim());

    String strTitle = "";

//    String msisdn = ssmsisdn;
    String strContent = "";
    String strContentList = "";
    String strCatName = "";
    String strContentLinkShare = "";

    if (!registedPack.equals("") && !ssmsisdn.equals("")) {
        strCatName = "Gói cước đã đăng ký";

        //trả về giá trị danh sách gói cước đã đăng ký (service, msisdn)
        strContentList = ap.ListPackByUser(strService6, ssmsisdn);
    } else {
        strCatName = ahc.GetCatName(cat, strService6);
        System.out.println("className.methodName(------cat:" + cat + "----------)");
        strContentList = ap.GetListPackV2(cat, strService6);
        strTitle = "Gói cước " + strCatName;
        strContentLinkShare = "package-" + cat + ".html";
    }

%>
<!DOCTYPE html>
<html lang="en"> 
    <head>
        <title><%=strTitle%></title>
        <%@include file="../inc/inc_header.jsp" %>
        <link rel="stylesheet" href="css/sport.css">
    </head>
    <body class="isHome">  
        <%@include file="../inc/inc_top.jsp" %>
        <div class=" uk-visible@m" style="background-image: linear-gradient(to bottom,#d9f2f9,#ffffff,#ffffff); height: 40px"> </div>
        <div class="uk-animation-slide-top-medium uk-padding-small  uk-visible@m" style="font-size: 40px ; color: #000000;  font-weight: 600; text-align: center; padding-bottom: 30px;">
            <span style="border-bottom: solid 4px #1B9F02"><%=strCatName%></span> 
            <span style="border-bottom: solid 4px #1B9F02"><%=strTitle%></span> 
        </div>

        <section id="app" class=" uk-offcanvas-content uk-overflow-hidden uk-position-relative">
            <div class="uk-section-small home__conTent" >
                <div class="uk-container">


                    <div class="home__margin1 uk-padding-top-small uk-hidden@m" style="margin-top: 10px">
                        <div class="uk-child-width-auto uk-grid-small uk-flex-middle uk-grid" uk-grid="">
                            <div class="uk-first-column" ><em>
                                    <a href="./" class="detail_menu_top"> Trang chủ </a>
                                     <span class="detail_menu_top"> > </span>
                                   <a href="goi-cuoc.html" class="detail_menu_top">Gói cước</a>
                                   <%if(!strCatName.equals("")){%>
                                    <span class="detail_menu_top"> > </span> 
                                    <a href="<%=strContentLinkShare%>" class="detail_menu_top"><%=strCatName%></a>
                                    <%}%>
                                </em>
                            </div>                         
                        </div>
                    </div>

                    <div class="uk-grid-small uk-grid-30-m" uk-grid>



                        <div class="uk-width-expand">


                            <div class="pack_box">

                                <%=strContentList%>

                            </div>

                        </div>

                    </div>
                </div>
            </div>

            <!--            <script>
                            function CallSMS(shortcode, confirmCmd) {
                                var userAgent = navigator.userAgent || navigator.vendor || window.opera;
                                if (userAgent.match(/iPad/i) || userAgent.match(/iPhone/i) || userAgent.match(/iPod/i)) {
                                    window.location.href = "sms:" + shortcode + "&body=" + confirmCmd;
                                } else {
                                    window.location.href = "sms:" + shortcode + "?body=" + confirmCmd;
            
                                }
                            }
                        </script>-->

            <%@include file="../inc/inc_footer.jsp" %>
        </section>
    </body>  
</html>