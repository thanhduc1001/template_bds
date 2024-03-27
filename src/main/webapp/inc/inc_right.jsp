<%@page import="cgt.data.api_web_config"%>
<%@page import="cgt.functions.Function"%>
<%@page import="cgt.data.api_right"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Function cfr = new Function();
    api_right ahr = new api_right();

    String strService3 = (String) session.getAttribute("service_id");
    api_web_config awc_r = (api_web_config) session.getAttribute("web_config");
    String web_color_r = "";
    if(awc_r!=null) {
        web_color_r = awc_r.getBASE_COLOR();
    }
    String msisdnr = "";
    String strContentRigt1 = "";
    String strContentRigt2 = "";

    strContentRigt1 = ahr.GetListContentRight1v2(msisdnr, strService3);
    strContentRigt2 = ahr.GetListContentRight2v2(msisdnr, "giai-tri", strService3);


%>




<div uk-sticky="bottom: true" class="uk-visible@m" >


    <div class="home__margin" >
        <div class="uk-grid-collapse home__box2__box" uk-grid>

            <div class="uk-width-1-1@m" >
                <div class="" style="background-image: linear-gradient(to bottom,#039ED7,#039ED7);">                 
                    <div class="" style="padding: 10px; background: <%=web_color_r%>" >
                        <p style="color: #ffffff; text-transform: uppercase">nội dung mới</p>
                    </div>
                </div>
            </div>
            <div class="">

                <%=strContentRigt1%>

            </div>

        </div>
    </div> 

<!--
    Tin hậu trường
    <div class="home__margin" >
        <div class="uk-grid-collapse home__box2__box" style="background-image: linear-gradient(to bottom,#039ED7,#039ED7);" uk-grid>

            <div class="uk-width-1-1@m" >
                <div class="" style="background-image: linear-gradient(to bottom,#039ED7,#039ED7);">                 
                    <div class="" style="padding: 10px">
                        <a href="news-giai-tri.html" style="color: #ffffff; text-transform: uppercase">Giải trí</a>
                    </div>
                </div>
            </div>

            <%=strContentRigt2%>

        </div>
    </div> 
    Tin hậu trường-->
</div>

