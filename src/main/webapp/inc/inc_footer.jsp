<%-- 
    Document   : inc_footer
    Created on : Dec 19, 2023, 3:51:25 PM
    Author     : Admin
--%>

<%@page import="cgt.data.api_page"%>
<%@page import="cgt.data.api_web_config"%>
<%
    api_web_config awcF = (api_web_config) session.getAttribute("web_config");
    String strServiceFooter = (String) session.getAttribute("service_id");
    api_page apfooter = new api_page();
    String footer = "";
    String logoWebSite = "";
    String webColor = "";
    if (awcF != null) {
        footer = awcF.getFOOTER();
        logoWebSite = awcF.getLOGO();
        webColor = awcF.getBASE_COLOR();
    }
    String strMenuFooter = "";
    strMenuFooter = apfooter.GetPageCodeFooter("", strServiceFooter);

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<footer id="footer" class="footer uk-section-small uk-background-01 uk-light" style="background: <%=webColor%>; padding: 20px 0;">
    <div class="uk-container">
        <%=footer%>
        <ul class="uk-navbar-nav">
            <%=strMenuFooter%>
        </ul>
    </div>
</footer>
<!-- package detail -->
<div id="modal-package" style="z-index: 1021" uk-modal>
    <div class="uk-modal-dialog uk-modal-body uk-border-rounded uk-text-center uk-padding" id="ShowPackageInfo" style="border-top: solid 6px  <%=webColor%>;">
        <div class="uk-child-width-expand@s uk-grid uk-grid-stack" uk-grid="">
            <div class="uk-width-1-4@m uk-text-center uk-first-column">
                <h4><span style="border-bottom: solid 3px #82cb16;">Chi tiết gói</span></h4>
                <p>
                    <a class="uk-navbar-item uk-logo" href="./"><img src="<%=logoWebSite%>" style="height: 45px;" alt="" /></a>
                </p>
            </div>

            <div class="uk-grid-item-match uk-grid-margin uk-first-column">
                <h4 id="pname" style="color: #039ed7;"></h4>
                <div id="cmdText" class="uk-card uk-card-default uk-card-body uk-border-rounded" style="background: #ccecf7; padding: 10px 10px 10px 10px;">
                    Soạn tin
                    <b id="psoantin" style="font-weight: bold; color: #1b9f02; font-size: 18px; padding: 10px;">DK DEMO</b> gửi
                    <b id="pdauso" style="font-weight: bold; color: #1b9f02; font-size: 18px; padding: 10px;">1001</b>
                </div>
                <div id="regis" class="uk-margin-small uk-hidden@m" style="padding-top: 10px;">
                    <a id="callSMS" class="uk-button uk-button-primary uk-button-small uk-border-rounded" style="background: <%=webColor%>; color: #ffffff;"> Đăng ký </a>
                </div>
            </div>
        </div>
        <br />
        <div class="uk-margin" style="border-top: solid 2px #039ed7;"></div>
        <div class="uk-text-left">
            <h3>Giới thiệu</h3>
            <p>
                <span id="pgioithieu">
                </span>
            </p>
        </div>
    </div>

</div>
<!-- package detail -->

<!--Cấu hình chung cho các thành phần có màu chung trên trang web-->
<style>
    .package_name, .package_cat{
        color: <%=webColor%> !important;
    }
    .main_web_btn{
        background: <%=webColor%> !important;
        color: #ffffff;
    }
    .content_card{
        background: <%=webColor%> !important;
    }
</style>
<!--Cấu hình chung cho các thành phần có màu chung trên trang web-->

<script>
    function CallSMS(shortcode, confirmCmd) {
        var userAgent = navigator.userAgent || navigator.vendor || window.opera;
        var firstCommand = confirmCmd.split('|')[0].trim(); // Lấy phần tử đầu tiên sau khi tách chuỗi theo dấu "|"

        if (userAgent.match(/iPad/i) || userAgent.match(/iPhone/i) || userAgent.match(/iPod/i)) {
            window.location.href = "sms:" + shortcode + "&body=" + firstCommand;
        } else {
            window.location.href = "sms:" + shortcode + "?body=" + firstCommand;
        }
    }

    function CallUnregSMS(shortcode, confirmCmd, name) {
        var userAgent = navigator.userAgent || navigator.vendor || window.opera;
        var firstCommand = confirmCmd.split('|')[0].trim(); // Lấy phần tử đầu tiên sau khi tách chuỗi theo dấu "|"

        var confirmMessage = "Bạn muốn hủy gói cước " + name + " không?";
        if (window.confirm(confirmMessage)) {
            if (userAgent.match(/iPad/i) || userAgent.match(/iPhone/i) || userAgent.match(/iPod/i)) {
                window.location.href = "sms:" + shortcode + "&body=" + firstCommand;
            } else {
                window.location.href = "sms:" + shortcode + "?body=" + firstCommand;
            }
        }
    }

    function ShowContent(name, des, cmd, ds, check) {
        document.getElementById('pname').innerText = name;
        document.getElementById('pgioithieu').innerText = des;
        var deleteDiv = document.getElementById('regis');
        var deleteDiv1 = document.getElementById('cmdText');
        if (check == 1 && deleteDiv !== null && deleteDiv1 !== null) {
            deleteDiv.remove();
            deleteDiv1.remove();
        }
        if (check != 1 && deleteDiv !== null && deleteDiv1 !== null) {
            document.getElementById('psoantin').innerText = cmd;
            document.getElementById('pdauso').innerText = ds;
            document.getElementById('callSMS').onclick = function () {

                // Gọi hàm CallSMS với giá trị từ pdauso và psoantin
                CallSMS(ds, cmd); // Cập nhật nội dung cho các thành phần khác



            };
        }

        //ajaxshow('content_text?act=packinfo&id=' + id + '&key=' + key + '', 'txtInfo');
        // Gán xử lý sự kiện cho thẻ a

    }
</script>
<script type="text/javascript">
    $('.custom-css').showMore({
        minheight: 0,
        buttontxtmore: 'Xem thêm',
        buttontxtless: 'Ẩn bớt',
        buttoncss: 'my-button-css',
        animationspeed: 250
    });
    setInterval(function () {
        ajaxshow('user_check', '');
    }, 10000);
</script>
