/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cgt.data.api_login;
import cgt.functions.Function;


@WebServlet(name = "get_otp", urlPatterns = {"/get_otp"})
public class get_otp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Function cf = new Function();

            HttpSession session = request.getSession();
            String loginkey = (String) session.getAttribute("loginkey");

            String txtID = cf.formatInputInt((request.getParameter("id") == null) ? "" : request.getParameter("id").trim());
            String key = cf.formatInput((request.getParameter("key") == null) ? "" : request.getParameter("key").trim());
            String otp = cf.formatInputInt((request.getParameter("otp") == null) ? "" : request.getParameter("otp").trim());
            String service_id = cf.formatInputInt((request.getParameter("service_id") == null) ? "" : request.getParameter("service_id").trim());
            
//            String act = cf.formatInput((request.getParameter("act") == null) ? "" : request.getParameter("act").trim());

            // ===check key =======
            if (!loginkey.equals(key)) {
                System.out.println("===key login error==== keysession:" + loginkey);
                System.out.println("key login truyền vào: " + key);
                return;
            }
            // ===check key =======
            
            
            String strValues = "";
            api_login ao = new api_login();
            
            String msisdn_format = cf.msisdn84(txtID);

            if (!otp.equals("")) {
                String calllogin = ao.GetLoginOTP(msisdn_format, otp,service_id);
                if (calllogin.equals("1")) {
                    session.setAttribute("msisdn", msisdn_format);
                 //   session.setAttribute("login", msisdn_format);
//                    strValues = "Đăng nhập thành công ! ";
                    strValues = "1";
                } else {
                    strValues = "Có lỗi ! OTP không hợp lệ (Mã lỗi: " + calllogin + ") ";
                   // strValues = "0";
                }
            } else {
                String callgetotp = ao.GetOTP(msisdn_format, service_id);
                if (callgetotp.equals("1")) {
                     System.out.println("Da gui");
                }
            }
            out.println(strValues);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
