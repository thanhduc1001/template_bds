/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgt.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ho Sy Minh
 */
@WebServlet(name = "user_check", urlPatterns = {"/user_check"})
public class user_check extends HttpServlet {

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
            //System.out.println("vnpt.web.user_check.processRequest()");
            HttpSession session = request.getSession();
            String smsisdn = (String) session.getAttribute("msisdn");

            if (smsisdn != null && !smsisdn.equals("")) {
            } else {
//                HttpServletRequest httpRequest = (HttpServletRequest) request;
//                Enumeration<String> headerNames = httpRequest.getHeaderNames();
//
//                InetAddress iplocal;
//                iplocal = InetAddress.getLocalHost();
//                String clientIp = request.getRemoteAddr();
//
//                String ip_publish = "";
//                try {
//                    URL whatismyip = new URL("http://checkip.amazonaws.com");
//                    BufferedReader in = new BufferedReader(new InputStreamReader(
//                            whatismyip.openStream()));
//                    ip_publish = in.readLine();
//                } catch (Exception e) {
//                }
//
//                System.out.println("vnpt.web.user_check.processRequest(----------check start----------------)");
//                System.out.println("vnpt.web.user_check.processRequest(--ip_publish:" + ip_publish + "-----getHostAddress:" + iplocal.getHostAddress() + "------iplocal:" + iplocal + "-----clientIp:" + clientIp + "-----------)");
//                System.out.println("vnpt.web.user_check.processRequest(-----headerNames:" + headerNames + "----------)");
//                if (headerNames != null) {
//                    while (headerNames.hasMoreElements()) {
//                        String keyname = headerNames.nextElement();
//
//                        System.out.println("Header: --keyname:" + keyname + "--" + httpRequest.getHeader(headerNames.nextElement()));
//                    }
//                }
//                System.out.println("vnpt.web.user_check.processRequest(----------check end----------------)");
            }

            if (smsisdn != null && !smsisdn.equals("")) {
            } else {
                //========================== get thue bao dung 3g 4g ===================              
                String sIP = request.getRemoteAddr();
                String x_wap_MSISDN = request.getHeader("x-wap-msisdn");
                String msisdnss = request.getHeader("MSISDN");
                String user_ip = request.getHeader("USER-IP");
                String xIP = request.getHeader("x-real-ip");

                String userAgent = request.getHeader("user-agent");
                // if (sIP != null && (sIP.startsWith("113.185.") || sIP.startsWith("10.144."))) {
                if (sIP != null && xIP.startsWith("113.185.")) {
                    if (msisdnss != null && !msisdnss.equals("")) {
                        session.setAttribute("msisdn", msisdnss);
                        session.setAttribute("login", msisdnss);
                    }
                    System.out.println("====user check session 4G to msisdn:" + msisdnss + "====userAgent:" + userAgent + "====user_ip:" + user_ip + "===x_wap_MSISDN:" + x_wap_MSISDN + "==sIP:" + sIP + "=====)");
                } else {
                    // System.out.println("====user check session 4G to msisdn not OK IP :" + msisdnss + "====userAgent:" + userAgent + "====user_ip:" + user_ip + "===x_wap_MSISDN:" + x_wap_MSISDN + "==sIP:" + sIP + "=====)");
                }

                //========================== get thue bao dung 3g 4g ===================
            }
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
