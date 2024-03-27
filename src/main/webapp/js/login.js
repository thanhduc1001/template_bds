//document.addEventListener('DOMContentLoaded', function () {
//    // Lấy các phần tử cần thao tác
//    var tabAccount = document.getElementById('tabAccount');
//    var tabOtp = document.getElementById('tabOtp');
//    var contentLoginAccount = document.getElementById('contentLoginAccount');
//    var contentPass = document.getElementById('contentPass');
//    var contentLoginOTP = document.getElementById('contentLoginOTP');
//    var forgetPass = document.getElementById('forget');
//    var nextBtn = document.getElementById('next');
//    var loginBtn = document.getElementById('signin');
//    var formLogin = document.querySelector('.form-login');
//    var bottomForm = document.querySelector('.bottom-form');
//    tabAccount.classList.add('current-tab');
//    
//    // Xử lý sự kiện click cho tab Đăng nhập bằng tài khoản
//    tabAccount.addEventListener('click', function () {
//        contentLoginAccount.style.display = 'block';
//        contentPass.style.display = 'block';
//        forgetPass.style.display = 'block';
//        loginBtn.style.display = 'block';
//        contentLoginOTP.style.display = 'none';
//        nextBtn.style.display = 'none';
//
//        tabAccount.classList.add('current-tab');
//        tabOtp.classList.remove('current-tab');
//        formLogin.style.height = '610px';
//        bottomForm.style.height = '500px';
//    });
//
//    // Xử lý sự kiện click cho tab Đăng nhập bằng OTP
//    tabOtp.addEventListener('click', function () {
//        tabAccount.classList.remove('current-tab');
//        tabOtp.classList.add('current-tab');
//        formLogin.style.height = '548px';
//        bottomForm.style.height = '400px';
//        contentLoginOTP.style.display = 'block';
//        forgetPass.style.display = 'none';
//        contentPass.style.display = 'none';
//        contentLoginAccount.style.display = 'none';
//        loginBtn.style.display = 'none';
//        nextBtn.style.display = 'block';
//    });
//    
//});