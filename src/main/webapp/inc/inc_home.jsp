
<%@page import="cgt.data.api_package"%>
<%@page import="cgt.functions.Function"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="cgt.data.api_web_config"%>
<%@page import="cgt.data.api_content"%>
<%@page import="cgt.data.api_home"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Function cf = new Function();
    api_home ah = new api_home();
    api_package ap = new api_package();
    api_web_config awcH = (api_web_config) session.getAttribute("web_config");
    String strService = (String) session.getAttribute("service_id");
    String cat = cf.formatInput((request.getParameter("cid") == null) ? "" : request.getParameter("cid").trim());
    String msisdn = "";
    String leagueId = "7";

    String strContent1 = "";
    String strContent2 = "";
    String strContent2m = "";
    String strContent3 = "";
    String strContent4 = "";
    String strContentIcon = "";
    String hPackColor = "";

    if (awcH != null) {
        hPackColor = awcH.getH_PACKAGE_COLOR();
    }

    strContentIcon = ah.GetListHomeIcon(strService);

    strContent1 = ah.GetListHome1v2(msisdn, "", "1", "1", strService);
    strContent2 = ah.GetListHome2v2(msisdn, "", "1", "10", strService);
    strContent3 = ah.GetListHome3v2(msisdn, "", strService);
    strContent4 = ap.GetListPackV2(cat, strService);
    int totalCat = ah.GetTotalCat(strService);
%>

<div class=" uk-visible@m" style="background-image: linear-gradient(to bottom,#d9f2f9,#ffffff,#ffffff); height: 40px"> </div>




<div class="uk-section-small home__conTent" >
    <div class="uk-container">
        <!-- News 1 HOT -->
        <div class="uk-grid-small uk-grid-32-m" style="margin-top: 20px" uk-grid>
            <div class="uk-width-expand">
                <%=strContent1%>
            </div>
        </div>
        <!-- News 1 HOT -->

        <!-- Slide PC -->
        <div class="home__margin uk-visible@m">
            <div uk-slider>
                <div class="uk-position-relative">
                    <div class="uk-slider-container uk-light">
                        <ul class="uk-grid-20-m uk-slider-items uk-child-width-1-2 uk-child-width-1-3@s uk-child-width-1-4@m">
                            <%=strContent2%>
                        </ul>
                    </div>

                    <div class="uk-hidden@s uk-light">
                        <a class="uk-position-center-left uk-position-small" href="#" uk-slidenav-previous uk-slider-item="previous"></a>
                        <a class="uk-position-center-right uk-position-small" href="#" uk-slidenav-next uk-slider-item="next"></a>
                    </div>

                    <div class="uk-visible@s">
                        <a class="uk-position-center-left-out uk-position-small" href="#"   uk-slider-item="previous"><img src="images/vlive/back.png" style="height: 40px"></a>
                        <a class="uk-position-center-right-out uk-position-small" href="#"  uk-slider-item="next"><img src="images/vlive/next.png"  style="height: 40px"></a>
                    </div>

                </div>
                <ul class="uk-slider-nav uk-dotnav uk-flex-center uk-margin"></ul>
            </div>
        </div>   
        <!-- Slide PC -->

        <!-- grid phim ngan -->
        <div class="uk-grid-small uk-grid-20-m" uk-grid>
            <div class="uk-width-expand">
                <%=strContent3%>
            </div>

        </div>
    </div>
</div>
