package com.br.lp2.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 1147106
 */
public class Usuario implements Serializable{
    private int id_user, user_type;
    private String username, password, photo;
    private Usuario_info usuario_info;
    private Date birthday;

    public Usuario() {
        this.id_user = -1;
        this.user_type = 1;
        this.username = "anonimo";
        this.password = "123";
        this.birthday = new Date();
        this.photo = "";
    }

    public Usuario(int id_user, int user_type, String username, String password, String photo, Usuario_info usuario_info, Date birthday) {
        this.id_user = id_user;
        this.user_type = user_type;
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.usuario_info = usuario_info;
        this.birthday = birthday;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Usuario_info getUsuario_info() {
        return usuario_info;
    }

    public void setUsuario_info(Usuario_info usuario_info) {
        this.usuario_info = usuario_info;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", user_type=" + user_type + ", username=" + username + ", password=" + password + ", photo=" + photo + ", usuario_info=" + usuario_info + ", birthday=" + birthday + '}';
    }

   
    
}
