<%--<%@page import="cgt.data.api_search"%>
<%@page import="vnpt.data.api.api_user"%>
<%@page import="vnpt.data.api.api_cat"%>
<%@page import="vnpt.data.api.api_page"%>--%>
<%@include file="/inc/inc_get_config.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../inc/inc_check.jsp" %>
<%    
    Function cf = new Function();

    String strTitle = "";
    String strTitleSub = " - Vina Sport";
    String strCatName = "Đăng nhập OTP ";

    strTitle = strCatName + strTitleSub;
%>
<!DOCTYPE html>
<html lang="en"> 
    <head>
        <title><%=strTitle%></title>
        <%@include file="../inc/inc_header.jsp" %>
    </head>
    <body>    
        <%@include file="../inc/inc_top.jsp" %>

        <section id="app" class="uk-height-viewport uk-offcanvas-content uk-overflow-hidden uk-position-relative">


            <div class="uk-section-small home__conTent" >
                <div class="uk-container">

                    <div class="uk-grid-small uk-grid-32-m" uk-grid>
                        <div class="uk-width-expand">


                            <div class="home__margin1">
                                <div class="uk-child-width-auto uk-grid-small uk-flex-middle" uk-grid>
                                    <div>
                                        <h3 class="uk-h3 uk-margin-remove uk-text-uppercase home__box4__title"> Xác thực thuê bao </h3>
                                    </div>
                                    <div>
                                        <img class="home__box4__img" src="images/Group12.png" alt="">
                                    </div>
                                    <div> </div>
                                </div>
                            </div>



                            <div class="uk-card uk-card-default uk-card-body uk-text-center">
                                Bạn vui lòng đăng nhập Số điện thoại để xác thực thuê bao để tiếp tục sử dụng dịch vụ 
                                <br> <br>  
                                <a href="#modal-example" class="uk-button uk-button-default uk-border-rounded" uk-toggle>Đăng nhập OTP </a>
                            </div>


                        </div>

                    </div>


                </div>
            </div>


            <%@include file="../inc/inc_footer.jsp" %>
        </section>
    </body>  
</html>