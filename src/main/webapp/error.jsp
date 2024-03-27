<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="inc/inc_check.jsp" %>
<%    String strTitle = "vLive - Không tìm thấy  ";

    //String uqrsxx = request.getQueryString();
    //String strMyLinkxx = request.getServletPath();
    // System.out.println("className.methodName(----strMyLink:" + strMyLinkxx + "------uqrs:" + uqrsxx + "--------)");
    // System.out.println("className.methodName(-------404 1------)");
%>
<!DOCTYPE html>
<html lang="en"> 
    <head>
        <title><%=strTitle%></title>
        <%@include file="inc/inc_header.jsp" %>
    </head>
    <body class="isHome">

        <%@include file="inc/inc_top.jsp" %>
        <div class="uk-visible@m" style="background-image: linear-gradient(to bottom,#d9f2f9,#ffffff,#ffffff); height: 40px"> </div>
        <section id="app"  class="uk-height-viewport uk-offcanvas-content uk-overflow-hidden uk-position-relative">



            <div class="uk-section-small home__conTent" >
                <div class="uk-container">

                    <div class="uk-grid-small uk-grid-32-m" uk-grid>
                        <div class="uk-width-expand">


                            <div class="uk-card uk-card-default uk-card-body uk-text-center">
                                <img src="images/404_1.png">
                                <br><br>
                                Truy cập của bạn có thể bị lỗi hoặc không tìm thấy nội dung

                                <br> <br>  
                                <a href="./" class="uk-button uk-button-default uk-border-rounded" >Về trang chủ  </a>
                            </div>


                        </div>

                    </div>


                </div>
            </div>

            <%@include file="inc/inc_footer.jsp" %>
        </section>
    </body>  
</html>