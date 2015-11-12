package com.br.lp2.business;

import com.br.lp2.exceptions.UserException;
import com.br.lp2.model.Usuario;
import com.br.lp2.model.dao.UsuarioDAO;
import javax.servlet.http.Cookie;

/**
 *
 * @author 1147106
 */
public class UserManager {

    private static Usuario user = null;

    /**
     *
     * @param username USERNAME
     * @param password PASSWORD
     * @return -1 for "User not found" -2 for "Wrong Password" 1 for "User
     * authorized"
     */
    public static int authorize(String username, String password) {
        int auth = -1;
        UsuarioDAO dao = new UsuarioDAO();
        user = new Usuario();
        try {
            user = dao.readByUsername(username);
            auth = 1;
        } catch (UserException ex) {
            if (user.getId_user() == -1) {
                System.out.println("Error: User not found");
                auth = -1;
            } else if (!user.getPassword().equals(password)) {
                System.out.println("Error: Wrong passsword");
                auth = -2;
            }
        }
        return auth;
    }

    public static int insert(Usuario user, String pwd2) {
        UsuarioDAO dao = new UsuarioDAO();

        if (dao.readByUsername(user.getUsername()).getId_user() != -1) {
            System.out.println("Error: username already used");
            return -3;
        }
        if (user.getPassword().equals(pwd2)) {
            boolean inserted = dao.insert(user);
            if (inserted) {
                System.out.println("User " + user.getUsername() + " inserted successfully");
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

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario user) {
        UserManager.user = user;
    }

}
