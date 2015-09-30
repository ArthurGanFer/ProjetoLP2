/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ber.lp2.controller;

import com.br.lp2.model.dao.UsuarioDAO;
import com.br.lp2.model.User;

/**
 *
 * @author 31338283
 */
public class LoginManager {
    
    /**
     * 
     * @param username USERNAME
     * @param password PASSWORD
     * @return -1 for User not found -2 for Invalid password 1 for User authorized
     */
    

    public static int authorize(String username, String password) {
        int auth = -1;
        UsuarioDAO dao = new UsuarioDAO();
        User usuario = dao.readByUsername(username);
        if (usuario.getId_usuario() == -1) {
            System.out.println("Error: User not fount");
            auth = -1;
        } else if (!usuario.getPassword().equals(password)) {
            System.out.println("Error: Invalid password");
            auth = -2;
        } else {
            auth = 1;
        }

        return auth;
    }

}
