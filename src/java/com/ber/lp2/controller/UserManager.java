package com.ber.lp2.controller;

import com.br.lp2.model.User;
import com.br.lp2.model.dao.UsuarioDAO;

/**
 *
 * @author 41218541
 */
public class UserManager {

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

    public static int insert(User user, String pwd2) {
        UsuarioDAO dao = new UsuarioDAO();

        if (dao.readByUsername(user.getUsername()).getId_usuario() != -1) {
            System.out.println("Error: user already used");
            return -3;
        }

        if (user.getPassword().equals(pwd2)) {
            boolean inserted = dao.insert(user);
            if (inserted) {
                System.out.println("User " + user.getUsername() + " inserted");
                return 1;
            } else {
                System.out.println("Error: user not inserted");
                return -5;
            }
        } else {
            System.out.println("Error: Password doesn't match");
            return -4;
        }
    }

}
