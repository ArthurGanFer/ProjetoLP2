/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model;

/**
 *
 * @author 31338283
 */
public class Usuario_info {

    private int id_usuario_info; //chave primaria
    private int id_usuario; //chave estrangeira de usuario
    private String nome;
    private String email;
    private int idade;

    public Usuario_info() {
    }

    public Usuario_info(int id_usuario_info, int id_usuario, String nome, String email, int idade) {
        this.id_usuario_info = id_usuario_info;
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public int getId_usuario_info() {
        return id_usuario_info;
    }

    public void setId_usuario_info(int id_usuario_info) {
        this.id_usuario_info = id_usuario_info;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Usuario_info{" + "id_usuario_info=" + id_usuario_info + ", id_usuario=" + id_usuario + ", nome=" + nome + ", email=" + email + ", idade=" + idade + '}';
    }

}
