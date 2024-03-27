<%@page import="cgt.data.api_cat"%>
<%@page import="cgt.data.api_page"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../inc/inc_check.jsp" %>
<%    Function cf = new Function();
    api_page ap = new api_page();
    api_cat ac = new api_cat();

    String cid = cf.formatInputIntAz((request.getParameter("cid") == null) ? "" : request.getParameter("cid").trim());
    String cat = cf.formatInput((request.getParameter("cid") == null) ? "" : request.getParameter("cid").trim());
    String strpage = cf.formatInputInt((request.getParameter("page") == null) ? "0" : request.getParameter("page").trim());
    String strpagesize = "20";

    String strTitle = "";
    String msisdn = "";
    String strCatCode = "";
    String strCatName = "";
    String strCatNameCha = "";
    String strContentPage = "";
    String strContentType = "1";
    String strHideRight = "0";

    String strContent = "";
    String strContentList = "";
    String strContentLinkShare = "";
    String strContentCatNameSub = "";
    String strContentImage = "images/default.png";

    strCatName = " Quy trình thanh toán và đảm bảo an toàn giao dịch";
    strTitle = strCatName ;

    System.out.println("className.methodName(----cat:" + cat + "---cid:" + cid + "---------)");

    strContentLinkShare = "" + cat + ".html";
    strContentCatNameSub = strCatName;
    //=============SEO website =================
    String seo_title = strTitle;
    String seo_site_name = "vlive";
    String seo_description = "vlive " + strTitle + "  - Website vlive.com.vn chuyên cung cấp thông tin giải trí tổng họp cập nhật thường xuyên 24/7  ";
    String seo_canonical = strContentLinkShare; // link bai viet
    String seo_url = strContentLinkShare;
    String seo_section = strContentCatNameSub; // ten chuyen muc
    String seo_image = strContentImage;
    //
    //<meta property="og:updated_time" content="2021-03-17T11:15:34+07:00">
    //<meta property="article:published_time" content="2021-02-05GMT+070014:20:49+07:00">
    //<meta property="article:modified_time" content="2021-03-17GMT+070011:15:34+07:00">
    //=============SEO website =================
%>
<!DOCTYPE html>
<html lang="en"> 
    <head>
        <title><%=strTitle%></title>
        <!-- Web SEO plugin -->
        <meta name="description" content="<%=seo_description%>"/>
        <meta name="robots" content="follow, index, max-snippet:-1, max-video-preview:-1, max-image-preview:large"/>
        <link rel="canonical" href="<%=seo_canonical%>" />
        <meta property="og:locale" content="vi_VN">
        <meta property="og:type" content="article">
        <meta property="og:title" content="<%=seo_title%>">
        <meta property="og:description" content="<%=seo_description%>">
        <meta property="og:url" content="<%=seo_url%>">
        <meta property="og:site_name" content="<%=seo_site_name%>">        
        <meta property="article:section" content="<%=seo_section%>">
        <meta property="og:image" content="<%=seo_image%>">
        <meta property="og:image:secure_url" content="<%=seo_image%>">
        <meta property="og:image:width" content="1335">
        <meta property="og:image:height" content="1093">
        <meta property="og:image:alt" content="<%=seo_title%>">
        <meta property="og:image:type" content="image/jpeg">
        <meta name="twitter:card" content="summary_large_image">
        <meta name="twitter:title" content="<%=seo_title%>">
        <meta name="twitter:description" content="<%=seo_description%>">
        <meta name="twitter:image" content="<%=seo_image%>">
        <!-- Web SEO plugin -->
        <%@include file="../inc/inc_header.jsp" %>
    </head>
    <body>    
        <%@include file="../inc/inc_top.jsp" %>

        <div class=" uk-visible@m" style="background-image: linear-gradient(to bottom,#d9f2f9,#ffffff,#ffffff); height: 40px"> </div>

        <section id="app" class="uk-height-viewport uk-offcanvas-content uk-overflow-hidden uk-position-relative">


            <div class="uk-section-small home__conTent" >
                <div class="uk-container">

                    <div class="uk-grid-small uk-grid-20-m" uk-grid>
                        <div class="uk-width-expand">
                            <div class="uk-card uk-card-default uk-card-body">                                
                                <div class="uk-text-center uk-animation-slide-top" style="font-weight: bold">
                                    <h1><%=strCatName%> </h1>
                                </div>

                                <div class="uk-animation-slide-bottom uk-text-justify">

                                    <br><b>1. Quy trình thanh toán</b>
                                    <br>- Thông qua đầu số dịch vụ, hệ thống thực hiện trừ tiền theo các giao dịch được Khách hàng lựa chọn.
                                    <br>- Khi thực hiện lệnh “Đăng ký gói cước” hệ thống sẽ căn cứ vào các cú pháp được nhập để tiến hành trừ tiền đối với từng giao dịch. Đối với thuê bao trả trước, đầu số dịch vụ sẽ kết nối với nhà mạng để xác định khoản tiền cần trừ trực tiếp ngay tại thời điểm Khách hàng yêu cầu lệnh. Đối với thuê bao trả sau, hệ thống sẽ ghi nhận số tiền phải trả trên tài khoản điện thoại và được ghi vào hóa đơn cước của tháng tương ứng. 
                                    <br>- Số tiền bị trừ tương ứng với số lần giao dịch và giá của từng nội dung niêm yết công khai trên website thương mại điện tử http://vlive.com.vn.
                                    <br>- Khoản tiền này sau khi nhà mạng thực hiện trừ sẽ được hoàn trả lại cho http://vlive.com.vn.
                                    <br><br><b>2. Đảm bảo an toàn giao dịch</b>
                                    <br>- Đảm bảo an toàn thanh toán: http://vlive.com.vn sử dụng các dịch vụ, hệ thống thanh toán tích hợp nhiều tính năng thông minh, chế độ bảo mật cao để đảm bảo thông tin cá nhân, đảm bảo an toàn thanh toán của Khách hàng http://vlive.com.vn sẽ không tính tiền cho đến khi giao dịch được hoàn tất.
                                    <br>Việc thanh toán dựa trên cơ chế trừ tiền của mạng viễn thông nên luôn đảm bảo độ chính xác cao nhất.

                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <%@include file="../inc/inc_footer.jsp" %>
        </section>
    </body>  
</html>