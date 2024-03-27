<%@include file="/inc/inc_get_config.jsp" %>
<%@page import="cgt.data.api_login"%>
<%@page import="cgt.data.api_user"%>
<%@page import="cgt.data.api_cat"%>
<%@page import="cgt.data.api_page"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../inc/inc_check.jsp" %>
<%@include file="../inc/inc_check_login.jsp" %>
<%
    Function cf = new Function();
    api_user auser = new api_user();
    api_login alogin = new api_login();

    String ssmsisdn = (String) session.getAttribute("msisdn");
    String sgmail = (String) session.getAttribute("email");
    String semail_id = (String) session.getAttribute("gmail_id");
    String sserviceu = (String) session.getAttribute("service_id");

    String cid = cf.formatInputIntAz((request.getParameter("cid") == null) ? "" : request.getParameter("cid").trim());
    String act = cf.formatInputIntAz((request.getParameter("act") == null) ? "" : request.getParameter("act").trim());
    String strTitle = "";
    String msisdn = ssmsisdn;
    String strCatName = "Trang cá nhân";

    //===================update=======================
    String strAlert = "";
    String txtHoVaten = cf.formatInput((request.getParameter("txtHoVaten") == null) ? "" : request.getParameter("txtHoVaten").trim());
    String txtNgaySinh = cf.formatInput((request.getParameter("txtNgaySinh") == null) ? "" : request.getParameter("txtNgaySinh").trim());
    String txtGioiTinh = cf.formatInput((request.getParameter("txtGioiTinh") == null) ? "" : request.getParameter("txtGioiTinh").trim());
    String txtEmail = cf.formatInput((request.getParameter("txtEmail") == null) ? "" : request.getParameter("txtEmail").trim());
//    String msisdnUpdate = (String) session.getAttribute("phone");
    if (request.getParameter("btnUpdate") != null) {

        if (txtGioiTinh.equals("")) {
            txtGioiTinh = "1";
        }

        String supdate = auser.UserUpdateInfo(ssmsisdn, sserviceu, txtHoVaten, txtNgaySinh, txtGioiTinh, txtEmail);
        if (supdate.equals("1")) {
            strAlert = "Cập nhật thành công !";
        } else {
            strAlert = "Có lỗi ! Mã lỗi: " + supdate + " ";
        }
    }
    //===================update=======================

    String strHoVaTen = "";
    String strSoDienThoai = "";
    String strNgaySinh = "";
    String strGioiTinh = "";
    String strGioiTinhName = "";
    String strGoogle = "";
    String strFacebook = "";
    String strEmail = "";

    String userName = (String) session.getAttribute("msisdn");
    if (userName != null) {
        auser.UserGetInfoDetailByUser(userName, sserviceu);
    } else if (!ssmsisdn.equals("") && !ssmsisdn.equals(null)) {
        auser.UserGetInfoDetail(msisdn, sserviceu);
    }
    if (auser != null) {
        strHoVaTen = cf.CheckNull(auser.getName());
        strSoDienThoai = cf.CheckNull(auser.getMsisdn());
        strNgaySinh = cf.CheckNull(auser.getBirthday());
        strGioiTinh = cf.CheckNull(auser.getGender());
        strGioiTinhName = cf.StatusSEX(auser.getGender());

        session.setAttribute("phone", strSoDienThoai);

        strGoogle = cf.CheckNull(auser.getGgName());
        strFacebook = cf.CheckNull(auser.getFbName());
        strEmail = cf.CheckNull(auser.getEmail());
    }

    strTitle = "Trang cá nhân ";

//    if (act.equals("google")) {
//        String strKQLK = alogin.LienKetMXH(msisdn, "0", semail_id);
//        if (strKQLK.equals("0")) {
//            strAlert = "Thành công! Cảm ơn bạn đã liên kết";
//        }
//        System.out.println("vnpt.web.oauth2callback.processRequest(----strKQLK:" + strKQLK + "-------------)");
//    }
    // String strContentList = auser.GetListPack(msisdn);

%>
<!DOCTYPE html>
<html lang="en"> 
    <head>
        <title><%=strTitle%></title>
        <%@include file="../inc/inc_header.jsp" %>
    </head>
    <body>    
        <%@include file="../inc/inc_top.jsp" %>
        <div class=" uk-visible@m" style="background-image: linear-gradient(to bottom,#d9f2f9,#ffffff,#ffffff); height: 40px"> </div>
        <div class="uk-animation-slide-top-medium uk-padding-small  uk-visible@m" style="font-size: 40px ; color: #000000;  font-weight: 600; text-align: center; padding-bottom: 30px;">

            <span style="border-bottom: solid 4px #1B9F02"><%=strCatName%></span> 
        </div>



        <section id="app" class=" uk-offcanvas-content uk-overflow-hidden uk-position-relative">


            <div class="uk-section-small home__conTent" >
                <div class="uk-container">

                    <div class="uk-grid-small uk-card uk-card-default" uk-grid>
                        <div class="uk-width-expand">

                            <div class="home__margin1 uk-margin-top uk-hidden@m">
                                <div class="uk-child-width-auto uk-grid-small uk-flex-middle" uk-grid>
                                    <div>
                                        <h3 class="uk-h3 uk-margin-remove uk-text-uppercase home__box4__title"><%=strCatName%></h3>
                                    </div>
                                    <div>
                                        <img class="home__box4__img" src="images/Group12.png" alt="">
                                    </div>
                                </div>
                            </div>


                            <div class="uk-padding">




                                <%if (act.equals("edit")) {%>
                                <form action="thong-tin-ca-nhan.html" method="POST" name="fupdate">
                                    <div >


                                        <table class="uk-table uk-table-hover uk-table-divider">
                                            <thead>
                                                <tr>
                                                    <th><img src="images/vlive/icon_dangnhap.png" class="uk-border-rounded" style="height: 25px; margin-right: 10px"> 
                                                        <%=strHoVaTen%>
                                                    </th>
                                                    <th class="uk-text-right"> <a class="" href="logout.html" style="text-transform: none"> <span uk-icon="icon: sign-out"  ></span> Đăng xuất </a>
                                                    </th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Số điện thoại</td>
                                                    <td class="uk-text-right">   <div class="uk-inline">
                                                            <span class="uk-form-icon" uk-icon="icon: phone"></span>
                                                            <input type="text" name="txtDienThoai" readonly="" disabled="" value="<%=strSoDienThoai%>" placeholder="Số Điện thoại" class="uk-input uk-border-rounded">
                                                        </div>           
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td>Email </td>
                                                    <td class="uk-text-right">  <div class="uk-inline">
                                                            <span class="uk-form-icon" uk-icon="icon: mail"></span>
                                                            <input type="text" name="txtEmail" value="<%=strEmail%>" placeholder="Địa chỉ Email" class="uk-input uk-border-rounded">              
                                                        </div>    
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td>Họ và tên </td>
                                                    <td class="uk-text-right"> <div class="uk-inline">
                                                            <span class="uk-form-icon" uk-icon="icon: user"></span>
                                                            <input type="text" name="txtHoVaten" value="<%=strHoVaTen%>" placeholder="Họ và Tên" class="uk-input uk-border-rounded">              
                                                        </div>     
                                                    </td>

                                                </tr>

                                                <tr>
                                                    <td>Ngày sinh </td>
                                                    <td class="uk-text-right"> <div class="uk-inline">
                                                            <span class="uk-form-icon" uk-icon="icon: calendar"></span>                                                 
                                                            <input type="text" name="txtNgaySinh" value="<%=strNgaySinh%>" placeholder="Ngày sinh dd/mm/yyyy" class="uk-input uk-border-rounded">                              
                                                        </div>  
                                                    </td>

                                                </tr>

                                                <tr>
                                                    <td>Giới tính </td>
                                                    <td class="uk-text-right">  Giới tính : 
                                                        <input type="radio" value="0" name="txtGioiTinh" <%if (strGioiTinh.equals("0")) {%>checked="" <%}%>   class="uk-radio uk-border-rounded" style="margin-left: 15px">   
                                                        Nam

                                                        <input type="radio" value="1" name="txtGioiTinh"  <%if (strGioiTinh.equals("1")) {%>checked="" <%}%>  class="uk-radio uk-border-rounded" style="margin-left: 15px">   
                                                        Nữ  
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>



                                        <div class="user_info_list uk-text-center" style="color: #f27002"><%=strAlert%></div>
                                        <div class="user_info_list uk-text-center">
                                            <input class="uk-button uk-button-default uk-border-rounded btn_red main_web_btn" type="submit"   name="btnUpdate"  value="Cập nhật"   style="background: #039ed7; color: #ffffff; text-transform: none; font-size: 18px; font-weight: 400">     
                                        </div>

                                    </div>
                                </form>
                                <%} else {%>



                                <table class="uk-table uk-table-hover uk-table-divider">
                                    <thead>
                                        <tr>
                                            <th><img src="images/vlive/icon_dangnhap.png" class="uk-border-rounded" style="height: 25px; margin-right: 10px"> 
                                                <%=strHoVaTen%>
                                            </th>
                                            <th class="uk-text-right"> <a class="" href="logout.html" style="text-transform: none"> <span uk-icon="icon: sign-out"  ></span> Đăng xuất </a>
                                            </th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Số điện thoại</td>
                                            <td class="uk-text-right"> <%=strSoDienThoai%>               </td>

                                        </tr>
                                        <tr>
                                            <td>Email </td>
                                            <td class="uk-text-right"><%=strEmail%>     </td>

                                        </tr>
                                        <tr>
                                            <td>Họ và tên </td>
                                            <td class="uk-text-right"> <%=strHoVaTen%>      </td>

                                        </tr>

                                        <tr>
                                            <td>Ngày sinh </td>
                                            <td class="uk-text-right"> <%=strNgaySinh%>  </td>

                                        </tr>

                                        <tr>
                                            <td>Giới tính </td>
                                            <td class="uk-text-right"><%=strGioiTinhName%>  </td>
                                        </tr>
                                        <tr>
                                            <p><a href="goi-cuoc-p-1.html" style="color: #007bb9"><span class="tai_khoan_box_icon"></span> Gói cước đã đăng ký</a></p>
                                        </tr>
                                    </tbody>
                                </table>


                                <div class="user_info_list uk-text-center" style="color: #f27002"><%=strAlert%></div>

                                <div class="user_info_list uk-text-center">
                                    <a class="uk-button uk-button-default uk-border-rounded" href="?act=edit" style="text-transform: none"> <span uk-icon="icon: pencil"  ></span> Cập nhật thông tin </a>
                                </div>


                                <%}%>

                                <br><br>

                            </div>





                        </div>

                    </div>


                </div>
            </div>



            <%@include file="../inc/inc_footer.jsp" %>
        </section>
    </body>  
</html>