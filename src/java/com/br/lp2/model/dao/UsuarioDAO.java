/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model.dao;

import com.br.lp2.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 31338283
 */
public class UsuarioDAO implements GenericDAO<Usuario> {

    private Connection conn;

    public UsuarioDAO() {
        //1. Realizar a conexão
        conn = ConnectionDB.getInstance();
    }

    @Override
    public List<Usuario> read() {
        List<Usuario> usuarios = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM usuario";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("username");
                String senha = rs.getString("password");
                Usuario u = new Usuario();
                usuarios.add(u);
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public boolean insert(Usuario usuario) {
        boolean resp = false;
        String sql = "INSERT INTO usuario(username,password) VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao inserir o Usuário");
            } else {
                System.out.println("Usuário inserido com sucesso!");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean update(Usuario usuario) {
        boolean resp = false;
        String sql = "UPDATE usuario SET username=?,password=? WHERE id_usuario=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getId_usuario());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao atualizar o usuário");
            } else {
                System.out.println("Usuário atualizado com sucesso!");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean delete(Usuario usuario) {
        boolean resp = false;
        String sql = "DELETE FROM usuario WHERE id_usuario=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getId_usuario());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao remover o Usuário");
            } else {
                System.out.println("Usuário removido com sucesso!");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Usuario readByUsername(String username) {
        Usuario usuario = new Usuario();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM usuario WHERE username=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usuario;
    }

}
