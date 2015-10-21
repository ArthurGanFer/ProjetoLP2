/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ber.lp2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.br.lp2.model.Usuario;

/**
 *
 * @author 31338283
 */
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
            //todas as operações user
            if (command.startsWith("user")) {
                RequestDispatcher rd;
                int code = 0;

                //LOGIN
                if (command.endsWith("login")) {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    code = UserManager.authorize(username, password);
                    request.getSession().setAttribute("username", username);
                } else if (command.endsWith("insert")) {
                    //INSERT
                    String username2 = request.getParameter("username");
                    String pwd = request.getParameter("password");
                    String pwd2 = request.getParameter("password2");
                    Usuario user = new Usuario();
                    user.setUsername(username2);
                    user.setPassword(pwd);
                    code = UserManager.insert(user, pwd2);
                    request.getSession().setAttribute("username", username2);
                }

                if (code == 1) {
                    rd = request.getRequestDispatcher("/home.jsp");
                    rd.forward(request, response);
                } else {
                    rd = request.getRequestDispatcher("/error.jsp");
                    String msg = "";
                    switch (code) {
                        case -1:
                            msg = "User not found!";
                            break;
                        case -2:
                            msg = "Invalid Password!";
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
                    }
                    request.getSession().setAttribute("code", msg);
                    rd.forward(request, response);
                }

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
        command = request.getParameter("command");
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
