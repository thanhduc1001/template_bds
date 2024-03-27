/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.web;

import cgt.data.api_login;
import cgt.functions.Function;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "user_login", urlPatterns = {"/user_login"})
public class user_login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            String service_id = cf.formatInputInt((request.getParameter("service_id") == null) ? "" : request.getParameter("service_id").trim());
            String password = cf.formatInput((request.getParameter("password") == null) ? "" : request.getParameter("password").trim());
            String username = cf.formatInput((request.getParameter("username") == null) ? "" : request.getParameter("username").trim());
            String key = cf.formatInput((request.getParameter("key") == null) ? "" : request.getParameter("key").trim());
            String strValues = "";
            api_login ao = new api_login();
            System.out.println("cgt.web.user_login.processRequest(xxxxxxxxxxxxx)");

            // ===check key =======
            if (!loginkey.equals(key)) {
                System.out.println("===key login error==== keysession:" + loginkey);
                System.out.println("key login truyền vào: " + key);
                return;
            }
            // ===check key =======
            
            
            String calllogin = ao.GetLogin(username, password,service_id);
            if (calllogin.equals("1")) {
                session.setAttribute("msisdn", username);
                strValues = "1";
            } else {
                strValues = "Có lỗi! Vui lòng kiểm tra lại tài khoản và mật khẩu";
               // strValues = "0";
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
