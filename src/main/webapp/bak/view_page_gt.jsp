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

    strCatCode = "";
    strCatName = "Giới thiệu";
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
                                    <br><b>1.	Mô tả dịch vụ:</b>
                                    <br>-	Dịch vụ Vlive là cổng thông tin chuyên cung cấp các gói nội dung tổng hợp về tất cả các lĩnh vực thời sự, kinh tế, văn hóa, xã hội, pháp luật, giáo dục, giải trí; Tư vấn chuyên sâu về làm đẹp, chăm sóc sức khỏe, kỹ năng sống, mẹo vặt; Thông tin thời tiết, vạn sự, xổ số,... hàng ngày theo dạng gói thông tin dành cho thuê bao VinaPhone.
                                    <br>-	Dịch vụ được triển khai trên các nền tảng: SMS/Website/Wapsite
                                    <br>-	Đầu số đăng ký dịch vụ: 9990, 9916 và 9958
                                    <br>-	Địa chỉ Website/Wapsite: http://vlive.com.vn
                                    <br>-	Phạm vi áp dụng: Toàn quốc
                                    <br>-	Thương hiệu: Dịch vụ mang thương hiệu VNPT-Media
                                    <br><br><b>2.	Điều kiện sử dụng dịch vụ:</b>
                                    <br>-	Thuê bao trả trước và trả sau đang hoạt động 2 chiều trên mạng VinaPhone.
                                    <br>-	Thuê bao đang trong vùng phủ sóng 3G/4G/5G của mạng VinaPhone.
                                    <br>-	Thuê bao sử dụng máy điện thoại có hỗ trợ tính năng 3G/4G/5G, có hỗ trợ tính năng xem video clip trực tuyến.
                                    <br><br><b>3.	Đối tượng sử dụng dịch vụ:</b>
                                    <br>-	Dịch vụ được cung cấp cho tất cả các thuê bao di động trả trước và trả sau của VinaPhone, độ tuổi chủ yếu từ 14 - 60 tuổi, không phân biệt giới tính, vùng miền.
                                    <br><br><b>4.	Tiện ích của dịch vụ:</b>
                                    <br>-	Dịch vụ cung cấp nội dung ở cả 2 hình thức là tóm tắt và chuyên sâu.
                                    <br>-	Khách hàng sau khi đăng ký thành công gói cước dịch vụ, hàng ngày/tuần KH sẽ nhận được tin nhắn bản tin gói nội dung dịch vụ theo định kỳ qua SMS bao gồm cả nội dung text và link wap. Khách hàng có thể truy cập link qua 3G/4G/wifi để xem tất cả các nội dung của gói cước dịch vụ theo các ngày.
                                    <br><br><b>5.	Hướng dẫn sử dụng dịch vụ</b>
                                    <br><br><b>5.1	Đăng ký/hủy qua kênh SMS:</b>
                                    <br>-	Khách hàng đăng ký gói cước dịch vụ soạn tin: [cú pháp đăng ký gói cước] gửi 9990, 9916 và 9958.
                                    <br>-	Khách hàng hủy gói cước dịch vụ soạn tin: [cú pháp hủy gói cước] gửi 9990, 9916 và 9958
                                    <br>-	Thông tin chi tiết cú pháp đăng ký/hủy các gói cước dịch vụ Vlive XEM TẠI ĐÂY.
                                    <br><br><b>5.2	Đăng ký/hủy qua kênh Wapsite dịch vụ:</b>
                                    <br><br><b>5.2.1 Đăng ký qua kênh wapsite dịch vụ</b>
                                    <br>-	Bước 1: Khách hàng sử dụng 3G/4G/5G hoặc wifi để truy cập vào wapsite của dịch vụ tại địa chỉ: http://vlive.com.vn;
                                    <br>-	Bước 2: Khách hàng bấm Đăng nhập, sau đó nhập số thuê bao và mã OTP  Hệ thống báo đăng nhập dịch vụ thành công.
                                    <br>-	Bước 3: Khách hàng vào danh sách gói cước trên giao diện dịch vụ sẽ hiển thị thông tin các gói cước. Khách hàng bấm “Đăng ký” cạnh gói cước muốn đăng ký;
                                    <br>-	Bước 4: Hệ thống điều hướng sang màn hình gửi SMS của thiết bị di động và được điền sẵn đầu số dịch vụ và cú pháp đăng ký.
                                    <br>-	Bước 4: Khách hàng bấm gửi tin nhắn. Hệ thống gửi MT thông báo đăng ký thành công gói cước.
                                    <br><br><b>5.2.2 Hủy qua kênh wapsite dịch vụ:</b>
                                    <br>-	Bước 1: Khách hàng vào danh sách gói cước trên giao diện dịch vụ và bấm chọn gói cước muốn hủy sẽ hiển thị thông tin của gói cước bao gồm: cú pháp hủy và đầu số gói dịch vụ;
                                    <br>-	Bước 2: Khách hàng nhắn tin SMS soạn hủy gói cước theo cú pháp hủy và đầu số dịch vụ. KH bấm gửi tin nhắn, hệ thống gửi MT thông báo hủy thành công gói cước dịch vụ về máy điện thoại KH.
                                    <br><br><b>5.3	Kịch bản đăng ký/hủy qua kênh website dịch vụ:</b>
                                    <br>-	Bước 1: Khách hàng vào danh sách gói cước trên giao diện dịch vụ và bấm chọn gói cước muốn đăng ký/hủy sẽ hiển thị thông tin của gói cước bao gồm: cú pháp đăng ký, cú pháp hủy và đầu số gói dịch vụ;
                                    <br>-	Bước 2: Khách hàng nhắn tin SMS soạn đăng ký/hủy gói cước theo cú pháp và đầu số dịch vụ. KH bấm gửi tin nhắn, hệ thống gửi MT thông báo đăng ký/hủy thành công gói cước dịch vụ về máy điện thoại KH. 

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