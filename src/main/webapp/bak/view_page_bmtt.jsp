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

    strCatName = "Bảo mật thông tin";
    strTitle = strCatName;

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
                                    <br>   Chính sách bảo vệ thông tin cá nhân khách hàng Mọi sự thu thập thông tin và sử dụng thông tin của Khách hàng đều được thông qua ý kiến của chính Khách hàng đó trước khi Khách hàng tiến hành các giao dịch cụ thể. Việc bảo vệ thông tin cá nhân Khách hàng được thực hiện theo đúng nguyên tắc sau:
                                    <br><br><b>1. Mục đích thu thập</b>
                                    <br>Thực hiện các giao dịch theo các đơn đặt hàng của Khách hàng, giới thiệu về dịch vụ, tính năng mới của website http://vlive.com.vn, chăm sóc Khách hàng.
                                    <br>                                    Tính giá, cước sử dụng
                                    <br>Quản lý việc đăng tải, bình luận của khách hàng về giao dịch yêu cầu thực hiện
                                    <br>Giải quyết các thắc mắc hay khiếu nại phát sinh khi Khách hàng sử dụng dịch vụ http://vlive.com.vn.
                                    <br>Ngăn chặn những hoạt động vi phạm pháp luật tại website http://vlive.com.vn.
                                    <br><br><b>2. Phạm vi sử dụng thông tin</b>
                                    <br>Chỉ sử dụng các thông tin được khách hàng đăng ký hoặc cập nhật trên website http://vlive.com.vn., không sử dụng các thông tin do khách hàng đưa trên các phương tiện, công cụ khác. Khi cần thiết, chúng tôi có thể sử dụng những thông tin này để liên hệ trực tiếp với khách hàng để thông báo về: thông tin về khuyến mãi và dịch vụ mới, liên lạc và giải quyết với khách hàng những trường hợp đặc biệt… Không sử dụng số điện thoại của khách hàng ngoài mục đích xác nhận và liên hệ có liên quan đến dịch vụ mà khách hàng đăng ký sử dụng của công ty.
                                    <br><br><b>3. Thời gian lưu trữ: 05 năm.</b>
                                    <br><br><b>4. Những người, tổ chức có thể tiếp cận thông tin đã thu thập bao gồm:</b>
                                    <br>Đơn vị chủ quản website http://vlive.com.vn.
                                    <br>Đơn vị viễn thông khi thực hiện trừ giá cước sử dụng
                                    <br>Cơ quan nhà nước có thẩm quyền khi có yêu cầu cụ thể
                                    <br><br> <b>5. Địa chỉ của đơn vị thu thập và quản lý thông tin</b>
                                    <br>Công ty: TỔNG CÔNG TY TRUYỀN THÔNG
                                    <br>Địa chỉ: Số 57, Huỳnh Thúc Kháng, P.Láng Hạ, Q. Đống Đa, TP. Hà Nội, Việt Nam
                                    <br>Điện thoại: 0243.7722728
                                    <br>Email: vnptmedia@vnpt.vn
                                    <br> <br> <b>6. Phương thức và công cụ để người tiêu dùng tiếp cận và chỉnh sửa dữ liệu cá nhân của mình trên hệ thống thương mại điện tử của đơn vị thu thập thông tin</b> 
                                    <br>Người dùng có thể tiếp cận thông tin cá nhân (gói cước sử dụng, nội dung sử dụng…) bằng cách truy cập vào website dịch vụ, vào mục tra cứu, nhập số điện thoại để biết được khách hàng có đăng ký dịch vụ hay không.
                                    <br>Về phần chỉnh sử dữ liệu cá nhân: khách hàng không chính sử được số điện thoại vì khi khách hàng đăng ký dịch vụ thì hệ thống sẽ nhận biết số điện thoại của khách hàng.
                                    <br><br><b>7. Cơ chế tiếp nhận và giải quyết khiếu nại của người tiêu dùng liên quan đến việc thông tin cá nhân bị sử dụng sai mục đích hoặc phạm vi đã thông báo</b>
                                    <br>Khi phát hiện thông tin cá nhân (cụ thể số điện thoại) của mình bị sử dụng sai mục đích hoặc phạm vi, người dùng có quyền gọi đến Tổng đài chăm sóc khách hàng: 18001091 hoặc gửi email khiếu nại đến gqkn1mb@vnpt.vn với các thông tin, chứng cứ liên quan tới việc này. Công ty cam kết sẽ phản hồi ngay lập tức trong vòng 24 tiếng để cùng khách hàng thống nhất phương án giải quyết.
                                    <br><br><b>8. Cam kết bảo mật thông tin cá nhân của Khách hàng</b>
                                    <br>Thông tin cá nhân của khách hàng trên http://vlive.com.vn. được http://vlive.com.vn. cam kết bảo mật tuyệt đối theo chính sách bảo vệ thông tin cá nhân của http://vlive.com.vn.Việc thu thập và sử dụng thông tin của mỗi khách hàng chỉ được thực hiện khi có sự đồng ý của khách hàng đó trừ những trường hợp pháp luật có quy định khác;
                                    <br>Không sử dụng, không chuyển giao, cung cấp hay tiết lộ cho bên thứ 3 nào về thông tin cá nhân của Khách hàng khi không có sự cho phép đồng ý từ khách hàng;
                                    <br>Trong trường hợp máy chủ lưu trữ thông tin bị hacker tấn công dẫn đến mất mát dữ liệu cá nhân khách hàng, http://vlive.com.vn sẽ có trách nhiệm thông báo vụ việc cho cơ quan chức năng điều tra xử lý kịp thời và thông báo cho Khách hàng được biết;
                                    <br>Tuy nhiên do hạn chế về mặt kỹ thuật, không một dữ liệu nào có thể được truyền trên đường truyền internet mà có thể được bảo mật 100%. Do vậy, http://vlive.com.vn. không thể đưa ra một cam kết chắc chắn rằng thông tin Khách hàng cung cấp cho http://vlive.com.vn. sẽ được bảo mật một cách tuyệt đối an toàn, và http://vlive.com.vn. không thể chịu trách nhiệm trong trường hợp có sự truy cập trái phép thông tin cá nhân của Khách hàng, như các trường hợp khách hàng tự ý chia sẻ thông tin với người khác.

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