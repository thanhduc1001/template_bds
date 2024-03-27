<%-- 
    Document   : inc_top
    Created on : Dec 19, 2023, 3:51:56 PM
    Author     : Admin
--%>


<%@page import="cgt.data.api_page"%>
<%@page import="cgt.data.api_web_config"%>
<%@page import="java.util.List"%>
<%@page import="cgt.data.api_home"%>
<%@page import="cgt.resource.ConfigInfoCache"%>
<%@page import="cgt.functions.Function"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Function cft = new Function();
    api_page aptop = new api_page();
    api_home aht = new api_home();
    String loginkey = (String) session.getAttribute("loginkey");
    String smsisdn = (String) session.getAttribute("msisdn");
    String slogin = (String) session.getAttribute("msisdn");
//    String namelogin = (String) session.getAttribute("user");
    String scookieid = (String) session.getAttribute("cookieid");
    String strService2 = (String) session.getAttribute("service_id");
    String webLogo = "";
    String webColorT = "";

    api_web_config awctop = (api_web_config) session.getAttribute("web_config");
    if (awctop != null) {
        webLogo = awctop.getLOGO();
        webColorT = awctop.getBASE_COLOR();
    }

    //=======active menu ===========
    String menuTop1a = "";
    String menuTop4a = "";
    String menuTop7a = "";
    String strLinkCat = cft.formatInput((request.getParameter("cat") == null) ? "" : request.getParameter("cat").trim());
    String strCat = cft.formatInputIntAz((request.getParameter("cat") == null) ? "" : request.getParameter("cat").trim());
    System.out.println("className.methodName(----strLinkCat:" + strLinkCat + "----)");

    if (strCat.equals("user")) {
        menuTop7a = "style=\"background-color:#DAF1F9\"";
    }

    if (strLinkCat.startsWith("phim")) {
        menuTop1a = "";
    } else if (strLinkCat.startsWith("tin-tuc")) {
        menuTop1a = "";
    }else if (strLinkCat.startsWith("goi-cuoc")) {
        menuTop1a = "";
    } else {
        menuTop1a = "_active";
    }

    //=======active menu ===========
    String menuTop1 = "Trang chủ";
    String menuTop4 = "Đăng ký gói cước";

    String strLinkmenuTop1 = "index.html";
    String strLinkmenuTop2 = "view-tin-tuc-phim.html";
    String strLinkmenuTop3 = "view-phim-ngan.html";
    String strLinkmenuTop4 = "goi-cuoc.html";
    String strDomainSite = "http://vsport.com.vn/oauth2callback";
    String strUrlLogin = "https://accounts.google.com/o/oauth2/auth?"
            + "scope=email"
            + "&redirect_uri=" + strDomainSite + ""
            + "&response_type=code"
            + "&client_id=195040612098-qja3f7dlgh0hdl4ph077qocjgt0uvt8c.apps.googleusercontent.com"
            + "&approval_prompt=force";

    ConfigInfoCache configlogin = new ConfigInfoCache();
    String LOGIN_LINK = configlogin.ConfigInfoCache("LINK_LOGIN3G4G");
    LOGIN_LINK += "?code=" + loginkey + "";

    String strContentMenuTopNews = "";
    String strContentMenuTopPack = "";
    String strMenuPage = "";

    strContentMenuTopNews = aht.GetListTopMenu("view", strService2, strLinkCat);
    strMenuPage = aptop.GetPageCode("", strService2);
    strContentMenuTopPack = aht.GetListTopMenu2("package", strService2);
%> 

<header   >
    <!--    menu top1 pc-->
    <div class="uk-container  uk-visible@m">
        <nav class="uk-navbar-container uk-navbar-transparent uk-navbar" uk-navbar="">
            <div class="uk-navbar-left">
                <div class="logotop">
                    <a href="./" class="uk-navbar-item uk-logo uk-padding-remove-left"><img src="<%=webLogo%>" alt="logo"></a>
                </div>

            </div>



            <div class="uk-navbar-right uk-visible@m" id="menutopvlive_bottom">
                <a style="color: #007bb9;font-weight: bold;" href="chinh-sach-bao-ve.html"><div class="menu_top_text uk-border-rounded txt-color">Chính sách bảo vệ dữ liệu cá nhân</div></a>
            </div>
        </nav>
        <!--    menu top2 pc-->    
        <nav class="uk-navbar-container uk-navbar-transparent uk-navbar" uk-navbar="">
            <div class="uk-navbar-left">
                <ul class="uk-navbar-nav uk-visible@m menu2-bottom" id="menutopvlive">
                    <li><a href="<%=strLinkmenuTop1%>"><div class="menu_top_text<%=menuTop1a%> uk-border-rounded"><%=menuTop1%></div></a></li>
                            <%=strContentMenuTopNews%>
                    <li><a href="<%=strLinkmenuTop4%>"><div class="menu_top_text<%=menuTop4a%> uk-border-rounded" ><%=menuTop4%></div></a></li>
                </ul>
            </div>



            <div class="uk-navbar-right uk-visible@m" id="menutopvlive_bottom">
                <ul class="uk-navbar-nav uk-visible@m" id="menutopvlive">
                    <%if (slogin != null || smsisdn != null) {%>
                    <a href="thong-tin-ca-nhan.html"  class="uk-button uk-button-default uk-border-pill" <%=menuTop7a%> >
                        <span>Trang cá nhân</span>
                    </a>
                    <%}
                        if (slogin == null && smsisdn == null) {%>
                    <a href="#modal-example" uk-toggle class="uk-button uk-button-default uk-border-pill">
                        <span>Đăng nhập</span>
                    </a>
                    <%}%>
                </ul>
            </div>
        </nav>
    </div>
    <!--    menu top pc-->

    <!--    menu top mobile-->
    <div class="uk-hidden@m">
        <div class="" style="background: #E9E9E9">
            <nav class="" uk-navbar="dropbar: true" >
                <div class="uk-navbar-left  uk-padding-remove uk-margin-remove">
                    <ul class="uk-navbar-nav uk-padding-remove uk-margin-remove" >
                        <li>
                            <a class="uk-navbar-item uk-logo"  href="./"><img src="<%=webLogo%>"  style="height: 45px;"   alt=""></a>
                        </li>
                    </ul>
                </div>

                <div class="uk-navbar-right">
                    <ul class="uk-navbar-nav" >
                        <%if (slogin != null || smsisdn != null) {%>
                        <%}
                            if (slogin == null && smsisdn == null) {%>
                        <li> 
                            <a href="#modal-example" uk-toggle  style="text-transform: none; font-weight: 600; font-size: 16px" > 
                                <img src="images/vlive/icon_dangnhap.png"  style="padding-right:8px"   alt="">  
                                Đăng nhập 
                            </a> 
                        </li>
                        <%}%>
                        <li>
                            <a href="#">
                                <span uk-icon="icon: menu; ratio: 2" style="color: #0571BC; font-weight: bold"></span>

                            </a>
                            <div class="uk-navbar-dropdown uk-width-expand"  style="background: #365486; color: #ffffff; padding: 13px;">


                                <%if (slogin != null || smsisdn != null) {%>
                                <div style="padding: 10px; color: #ffffff"> 
                                    <img src="images/Layer 2@3x.png" style="height: 26px; margin-right: 5px">
                                    <a style="text-transform: none; color: #ffffff" >Xin chào ! <%= smsisdn != null ? smsisdn : slogin%> </a>
                                    <ul class="uk-nav uk-navbar-dropdown-nav" style="padding-left: 20px">
                                        <li><a href="thong-tin-ca-nhan.html" style="color: #ffffff"><span class="tai_khoan_box_icon" uk-icon="icon: user; ratio: 1"></span> Thông tin cá nhân</a></li>                                                                      
                                        <li><a href="goi-cuoc-p-1.html" style="color: #ffffff"><span class="tai_khoan_box_icon" uk-icon="icon: phone; ratio: 1"></span> Gói cước đã đăng ký</a></li>
                                    </ul>
                                </div>
                                <%} else {%>


                                <%}%>

                                <ul class="uk-nav m_menu" uk-nav="multiple: true">                                  
                                    <li><a href="<%=strLinkmenuTop1%>" style="color: #ffffff"><%=menuTop1%></a></li>
                                        <%=strContentMenuTopNews%>
                                    <li><a href="<%=strLinkmenuTop4%>" style="color: #ffffff"><%=menuTop4%></a> </li>
                                    <%=strMenuPage%>
                                    <li><a href="chinh-sach-bao-ve.html"><div class="menu_top_text uk-border-rounded txt-color">Chính sách bảo vệ dữ liệu cá nhân</div></a></li>
                                </ul>

                                <%if (slogin != null || smsisdn != null) {%>
                                <ul class="uk-nav uk-navbar-dropdown-nav">
                                    <li class="uk-nav-divider"></li>
                                    <li><a href="logout.html" style="color: #ffffff"> <span class="tai_khoan_box_icon" uk-icon="icon:  sign-out; ratio: 1"></span> Đăng xuất </a></li>
                                </ul>
                                <%}%>
                            </div>
                        </li>
                    </ul>

                </div>
            </nav>

            <div class="uk-navbar-dropbar" ></div>
        </div>


    </div>
    <!--    menu top mobile-->
</header>



<!-- login -->
<!--<div id="modal-example" style="z-index: 1021" uk-modal>
    <div class="uk-modal-dialog form-login">
        <div class="top-form">
            <div class="logo-login">
                <img src="images/logo-signin.png" alt="logo"/>
                <p class="title-login" style="margin-top: unset !important">Đăng nhập</p>
            </div>
                        <div class="close-btn">
                            <a href="#" id="closeModal"><img src="images/Close_round.png" alt="close"/></a>
                        </div>
        </div>
        <div class="bottom-form">
            <div class="tab-form">
                <div class="login-account" id="tabAccount">
                    <p>Đăng nhập bằng tài khoản</p>
                </div>
                <div class="login-otp" id="tabOtp">
                    <p>Đăng nhập bằng OTP</p>
                </div>
            </div>
            <div class="txt-form">
                <div class="txt-account" id="contentLoginAccount">
                    <label for="txtUserName">Tài khoản</label>
                    <input
                        type="text"
                        id="txtUserName"
                        name="txtUserName"
                        placeholder="Tài khoản"
                        />
                </div>
                <div class="txt-password" id="contentPass">
                    <label for="txtPassword">Mật khẩu</label>
                    <input
                        type="password"
                        id="txtPassword"
                        name="txtPassword"
                        placeholder="Mật khẩu"
                        />
                </div>
                <div class="txt-phonenumber" id="contentLoginOTP">
                    <label for="txtMobile">Nhập số điện thoại</label>
                    <input
                        type="text"
                        id="txtMobile"
                        name="txtMobile"
                        placeholder="Số điện thoại"
                        />
                </div>
            </div>
            <div class="button-function">
                <div style="color: red" id="txtStatus1"></div>
                <div class="top-function">
                    <div class="rememberPass">
                        <input type="checkbox" id="remainPass" name="remainPass" />
                        <label for="remainPass"> Duy trì đăng nhập</label>
                    </div>
                    <div class="forgetPass" id="forget">
                        <a href="#"  onclick="showForgotPass()">Quên mật khẩu</a>
                    </div>
                </div>
                <div class="submitBtn-function">
                    <div class="btnNext" id="next">
                        <button type="submit" id="btnNext" onclick="SendOTP()">
                            Tiếp theo
                        </button>
                    </div>
                    <div class="btnSignin" id="signin">
                        <button type="submit" id="loginAccount" onclick="loginAccount()">
                            Đăng nhập
                        </button>
                    </div>
                    <div class="btnRegister" style="display: none">
                        <button type="button">Đăng ký</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>-->
<!-- login -->

<!-- old login-->
<div id="modal-example" style="z-index: 1021;" uk-modal>
    <div class="uk-modal-dialog uk-border-rounded uk-modal-body uk-section-muted uk-flex uk-flex-middle uk-box-shadow-large">

        <div class="uk-width-1-1">
            <div class="uk-grid-margin uk-grid uk-grid-stack" uk-grid>
                <div class="uk-width-1-1@m">
                    <div class=" uk-margin-auto uk-card uk-card-body">
                        <ul class="uk-tab uk-flex-center" uk-grid uk-switcher="animation: uk-animation-fade">
                            <li><a href="#!">Đăng nhập bằng tài khoản</a></li>
                            <li><a href="#!">Đăng nhập bằng OTP</a></li>
                            <li class="uk-hidden">Quên mật khẩu?</li>
                        </ul>
                        <ul class="uk-switcher uk-margin">
                            <li id="account_login">
                                <form >
                                    <div class="uk-margin">
                                        <div class="uk-inline uk-width-1-1">
                                            <span class="uk-form-icon" uk-icon="icon: user"></span>
                                            <input class="uk-input uk-border-rounded" id="txtUserName" required  type="text" placeholder="Tài khoản">
                                        </div>
                                    </div>
                                    <div class="uk-margin ">
                                        <div class="uk-inline uk-width-1-1">
                                            <span class="uk-form-icon" uk-icon="icon: lock"></span>
                                            <input class="uk-input uk-border-rounded" id="txtPassword" aria-label="Clickable icon" type="password" required placeholder="Mật khẩu" />
                                            <a toggle="#txtPassword" uk-tooltip="title: Ẩn/Hiện mật khẩu" class="uk-form-icon uk-form-icon-flip" id="toggle-password"  uk-icon="icon: eye-slash" ></a>
                                        </div>
                                    </div>
                                    <div class="uk-margin uk-flex uk-align-center uk-flex-between uk-flex-wrap">
                                        <div class=" uk-grid-small uk-child-width-auto uk-grid">
                                            <label><input class="uk-checkbox" type="checkbox" /> Duy trì đăng nhập</label>

                                        </div>
                                        <a href="#" onclick="showForgotPass()">Quên mật khẩu?</a>
                                    </div>

                                    <div style="color: red" id="txtStatus1"></div>

                                    <div class="uk-margin ">
                                        <p class="uk-text-center">
                                            <button  id="loginAccount" class="uk-button uk-button-default uk-border-rounded login_btn_top main_web_btn"> <span uk-icon="sign-in" style="margin-right: 5px; color: #ffffff"></span> Đăng nhập </button>
                                        </p>
                                    </div>

                                    <div class="uk-text-small uk-text-center uk-hidden">Chưa có tài khoản? <a href="#">Đăng ký</a></div>
                                </form>
                            </li>
                            <li>
                                <form id="otp_login">
                                    <div class="uk-margin uk-flex uk-flex-between uk-flex-wrap mobile-otp">
                                        <div class="uk-inline mobile-txt">
                                            <span class="uk-form-icon" uk-icon="icon: phone"></span>
                                            <input class="uk-input uk-border-rounded" id="txtMobile"  maxlength="12" type="number" placeholder="Số điện thoại">
                                        </div>
                                        <button class="uk-button uk-button-default uk-border-rounded" type="button" id="btnSendOTP" onclick="SendOTP();"> <span uk-icon="forward" style="margin-right: 5px"></span> Gửi OTP <span id="timecountdow"></span> </button>
                                    </div>

                                    <div style="color: red; text-align: center;" id="txtStatus"></div>

                                    <div  id="ShowInputOtp" style="display: none; text-align: center">

                                        <input class="uk-input uk-border-rounded" style="width: 150px ; text-align: center" id="txtOTP"  maxlength="6" type="text" placeholder="Mã xác nhận">

                                    </div>

                                    <p class="uk-text-center">
                                        <button type="button" class="uk-button uk-button-default uk-border-rounded login_btn_top main_web_btn" id="btnLogin" onclick="LoginOTP();" > <span uk-icon="sign-in" style="margin-right: 5px; color: #ffffff"></span> Đăng nhập </button>
                                    </p>
                                </form>
                            </li>
                            <li id="forgotPass">
                                <p class="uk-text-center uk-width-medium@s uk-margin-auto">Nhập địa chỉ email của bạn.<br/> Chúng tôi sẽ gửi cho bạn một liên kết để đặt lại mật khẩu.</p>
                                <form>
                                    <div class="uk-margin">
                                        <div class="uk-inline uk-width-1-1">
                                            <span class="uk-form-icon" uk-icon="icon: mail"></span>
                                            <input class="uk-input uk-border-rounded"  id="txtEmail"  type="email" required placeholder="Email">
                                        </div>
                                    </div>
                                    <div class="uk-margin">
                                        <p class="uk-text-center">
                                            <button type="button" class="uk-button uk-button-default uk-border-rounded login_btn_top main_web_btn"> <span uk-icon="forward" style="margin-right: 5px; color: #ffffff"></span> Gửi </button>
                                        </p>
                                    </div>
                                    <div class="uk-text-small uk-text-center">
                                        <a href="#" onclick="hideForgotPass()">Quay lại đăng nhập</a>
                                    </div>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
//    function showForgotPass() {
//        $('#forgotPass').addClass('uk-active');
//        $('#account_login').removeClass('uk-active');
//    }
//
//    function hideForgotPass() {
//        $('#forgotPass').removeClass('uk-active');
//        $('#account_login').addClass('uk-active');
//    }

    function SendOTP() {
        document.getElementById("btnLogin").disabled = false;
        var msisdn = document.getElementById("txtMobile").value;

        if (msisdn.length < 10 || msisdn.length > 11) {
            document.getElementById("txtStatus").innerHTML = "Có lỗi ! Số điện thoại không hợp lệ";
        } else {
            document.getElementById("txtStatus").innerHTML = "";
            document.getElementById("btnSendOTP").disabled = true;
            document.getElementById("txtMobile").disabled = true;
            timer(59, 'timecountdow', 'btnSendOTP');
            document.getElementById("ShowInputOtp").style.display = "block";
            ajaxshow('get_otp?id=' + msisdn + '&key=<%=loginkey%>&service_id=<%=strService2%>', 'txtStatus');
        }
    }

    function LoginOTP() {
        var msisdn = document.getElementById("txtMobile").value;
        var otp = document.getElementById("txtOTP").value;
        if (msisdn.length < 10 || msisdn.length > 11) {
            document.getElementById("txtStatus").innerHTML = "Có lỗi ! Chưa nhập số điện thoại";
        } else if (otp == null || otp == "") {
            document.getElementById("txtStatus").innerHTML = "Chưa nhập OTP!";
        } else {
            $.ajax({
                type: "GET",
                url: "get_otp?id=" + msisdn + "&otp=" + otp + "&service_id=<%=strService2%>&key=<%=loginkey%>",
                cache: false,
                success: function (data) {
                    console.log('data==>' + data);
                    if (data.trim() === 1 || data.trim() === "1") {
                        location = 'thong-tin-ca-nhan.html';
                    } else {
                        document.getElementById("txtStatus").innerHTML = data;
                    }
                }
            });
        }

    }
    ;

//    $('#loginAccount').on('click', function (e) {
//        var userName = document.getElementById("txtUserName").value;
//        var password = document.getElementById("txtPassword").value;
//        var service_id = "<%=strService2%>";
//        if (userName == "" || userName == null || password == "" || password == null) {
//            document.getElementById("txtStatus1").innerHTML = "Vui lòng nhập tài khoản và mật khẩu !";
//        } else {
//            e.preventDefault();
//            $.ajax({
//                type: "POST",
//                url: "user_login",
//                data: {
//                    username: userName,
//                    password: password,
//                    service_id: service_id
//                },
//                success: function (data) {
//                    console.log(data);
//                    if (data.trim() === 1 || data.trim() === "1") {
//                        document.getElementById("txtStatus1").innerHTML = "Đăng nhập thành công !";
//                        location = 'thong-tin-ca-nhan.html';
//                    } else {
//                        document.getElementById("txtStatus1").innerHTML = data;
//                    }
//                }
//            });
//        }
//    });

    $('#loginAccount').on('click', function (e) {
        var userName = document.getElementById("txtUserName").value;
        var password = document.getElementById("txtPassword").value;
        var service_id = "<%=strService2%>";
        var key = "<%=loginkey%>";
        if (userName == "" || userName == null || password == "" || password == null) {
            document.getElementById("txtStatus1").innerHTML = "Vui lòng nhập tài khoản và mật khẩu !";
        } else {
            e.preventDefault();
            $.ajax({
                type: "POST",
                url: "user_login",
                data: {
                    username: userName,
                    password: password,
                    service_id: service_id,
                    key: key
                },
                success: function (data) {
                    console.log(data);
                    if (data.trim() === 1 || data.trim() === "1") {
                        location = 'thong-tin-ca-nhan.html';
                    } else {
                        document.getElementById("txtStatus1").innerHTML = data;
                    }
                }
            });
        }
    });

    function showForgotPass() {
        $('#forgotPass').addClass('uk-active');
        $('#account_login').removeClass('uk-active');
    }

    function hideForgotPass() {
        $('#forgotPass').removeClass('uk-active');
        $('#account_login').addClass('uk-active');
    }

    $("#toggle-password").click(function () {



        var input = $($(this).attr("toggle"));
        if (input.attr("type") == "password") {
            input.attr("type", "text");
            $(this).attr("uk-icon", "icon: eye");
        } else {
            input.attr("type", "password");
            $(this).attr("uk-icon", "icon: eye-slash");
        }
    });
</script>
