/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model;

import java.io.Serializable;

/**
 *
 * @author 31338283
 */
public class Usuario implements Serializable {

    private int id_usuario;
    private String username, password;
    private Usuario_info usuario_info;

    public Usuario(int id_usuario, String username, String password, Usuario_info usuario_info) {
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.usuario_info = usuario_info;
    }

    public Usuario() {
        this.id_usuario = -1;
        this.username = "anonimo";
        this.password = "1234567";
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public Usuario_info getUsuario_info() {
        return usuario_info;
    }

    public void setUsuario_info(Usuario_info usuario_info) {
        this.usuario_info = usuario_info;
    }

    @Override
    public String toString() {
        return "user{" + "id_usuario=" + id_usuario + ", username=" + username + ", password=" + password + '}';
    }

}
