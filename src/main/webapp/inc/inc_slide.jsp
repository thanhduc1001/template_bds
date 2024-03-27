<%@page import="cgt.functions.Function"%>
<%@page import="cgt.data.api_web_config"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    api_web_config awc_slide = (api_web_config) session.getAttribute("web_config");
    String slide_text = "";
    String bannerUrl = "images/vlive/bg-topbaner.png";
    String imgTopBanner = "";
    String webColorS = "";
    if (awc_slide != null) {
        slide_text = awc_slide.getSLIDE_TEXT();
        slide_text = slide_text.replaceAll("\r\n", "<br />");
        bannerUrl = "images/vlive/bg-topbaner.png";
        imgTopBanner = awc_slide.getIMG_BANNER();
        webColorS = awc_slide.getBASE_COLOR();
    }
%>

<!--slide pc-->
<div class=" uk-visible@m" style="background-image: linear-gradient(to bottom,#d9f2f9,#ffffff,#ffffff); height: 40px"> </div>

<div class=" uk-visible@m" style="background: url('<%=bannerUrl%>') bottom repeat-x" >
    <div class="uk-width-1-1" style="padding-bottom: 130px" >
        <div class="uk-container" style="">
            <div class="uk-child-width-1-2 uk-flex-middle" uk-grid>


                <div class=" " >
                    <div class="uk-text-left">                      
                        <div class="uk-margin-medium uk-animation-slide-left">
                            <span class="uk-child-width-1-1">
                                <div class="txt_slide" >
                                    <span style="font-size: 40px ; color: #000000;  text-decoration: underline #d1eccd; font-weight: 600"> <%=slide_text%></span>  <br>  
                                </div>
                            </span>                          
                        </div>
                        <a href="" class="uk-button uk-button-default uk-button-large uk-border-pill uk-animation-slide-bottom"  style="background: <%=webColorS%>">

                            <div class="uk-background-default uk-border-pill"  style="background: <%=webColorS%>; color: #ffffff; text-transform: none; font-size: 18px; font-weight: 400">
                                Đăng ký ngay 
                            </div>
                        </a>
                    </div>
                </div>

                <div class="uk-animation-slide-right" >
                    <img src="<%=imgTopBanner%>" alt="">
                </div>

            </div>


        </div>
    </div>
</div> 
<!--slide pc-->