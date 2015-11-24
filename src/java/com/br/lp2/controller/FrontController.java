/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.controller;

import com.br.lp2.business.PhotoUploader;
import com.br.lp2.business.UserManager;
import com.br.lp2.model.Usuario;
import com.br.lp2.model.Usuario_info;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.MailDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 1147106
 */
@MultipartConfig
public class FrontController extends HttpServlet {

    private String command;

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

            String page = "home.jsp";
            String msg = "";
            RequestDispatcher rd;
            //---------- OPERAÇÕES DO USUÁRIO ----------
            if (command.startsWith("user")) {
                int code = 0;

                if (command.endsWith("login")) {
                    //---------- LOGIN ----------
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    code = UserManager.authorize(username, password);

                    request.getSession().setAttribute("user", UserManager.getUser());

                } else if (command.endsWith("insert")) {
                    //---------- INSERT USER ----------
                    String username2 = request.getParameter("username");
                    String pwd = request.getParameter("password");
                    String pwd2 = request.getParameter("password2");
                    String nome = request.getParameter("nome");
                    String email = request.getParameter("email");
                    String datestr = request.getParameter("birthday");

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date birthday = null;
                    try {
                        birthday = formatter.parse(datestr);
                    } catch (ParseException ex) {
                        Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Usuario_info info = new Usuario_info();
                    info.setNome(nome);
                    info.setEmail(email);

                    Usuario user = new Usuario();

                    user.setUsername(username2);
                    user.setPassword(pwd);
                    user.setBirthday(birthday);
                    user.setUser_type(1);
                    user.setUsuario_info(info);

                    PhotoUploader pu = new PhotoUploader(request.getPart("file"), getServletContext());
                    String path = getServletContext().getRealPath(File.separator);
                    String path2 = "/img/" + username2;

                    if (pu.upload(path + path2)) {

                        String imageName = pu.getName();
                        user.setPhoto(imageName);
                        code = UserManager.insert(user, pwd2);
                        request.getSession().setAttribute("user", UserManager.getUser());
                    }
                    request.getSession().invalidate();

                } else if (command.endsWith("logout")) {
                    //---------- LOGOUT ----------
                    request.getSession().invalidate();
                    code = 10;
                }

                // TRATAMENTO DO CÓDIGO DA OPERAÇÃO
                if (code == 1) {
                    String username = "";
                    String pwd = "";
                    if (request.getParameter("lembrar") != null) {
                        username = UserManager.getUser().getUsername();
                        pwd = UserManager.getUser().getPassword();
                    }
                    Cookie cookie = new Cookie("name", username);
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cookie);
                    Cookie cookie2 = new Cookie("pwd", pwd);
                    cookie2.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cookie2);

                } else {
                    page = "error.jsp";
                    switch (code) {
                        case -1:
                            msg = "User not found!";
                            break;
                        case -2:
                            msg = "Wrong Password!";
                            break;
                        case -3:
                            msg = "User already exist!";
                            break;
                        case -4:
                            msg = "Password doesn't match!";
                            break;
                        case -5:
                            msg = "Error on database. Try again!";
                            break;
                        default:
                            msg = "OK";
                            page = "index.jsp";
                            break;
                    }

                }

            } // FIM DO IF PARA USUÁRIO

            // coloca a mensagem na sessão e encaminha para a página correta
            request.getSession().setAttribute("code", msg);
            response.sendRedirect(page);

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
        command = request.getParameter("command");
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
        command = request.getParameter("command");
        System.out.println(command);
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
