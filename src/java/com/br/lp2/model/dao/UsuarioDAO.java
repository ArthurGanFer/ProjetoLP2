/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model.dao;

import com.br.lp2.exceptions.UserException;
import com.br.lp2.model.Usuario;
import com.br.lp2.model.Usuario_info;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1147106
 */
public class UsuarioDAO implements GenericDAO<Usuario> {

    private Connection conn;

    public UsuarioDAO() {
        //1. Realizar a conexão
        conn = ConnectionDB.getInstance();
    }

    @Override
    public boolean insert(Usuario usuario) {
        boolean resp = false;
        String sql = "INSERT INTO usuario(username,password,user_type,birthday,photo) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getUser_type());
            java.sql.Date datesql = new java.sql.Date(usuario.getBirthday().getTime());
            ps.setDate(4, datesql);
            ps.setString(5, usuario.getPhoto());
            ps.executeUpdate();
            ResultSet rs2 = ps.getGeneratedKeys();
            int chave = -1;
            while (rs2.next()) {
                chave = rs2.getInt(1);
            }
            boolean resposta = this.insertInfo(usuario.getUsuario_info(), chave);
            if (resposta == false) {
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
    public List<Usuario> read() {
        List<Usuario> users = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM usuario u "
                + "INNER JOIN usuario_info i ON u.id_usuario = i.id_usuario";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                Usuario_info info = new Usuario_info();
                info.setId_usuario_info(rs.getInt("id_usuario_info"));
                info.setId_usuario(rs.getInt("id_usuario"));
                info.setNome(rs.getString("nome"));
                info.setEmail(rs.getString("email"));

                Usuario user = new Usuario();
                user.setUsuario_info(info);
                user.setId_user(rs.getInt("id_usuario"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUser_type(rs.getInt("user_type"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhoto(rs.getString("photo"));
                users.add(user);
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public Usuario readByUsername(String username) {
        Usuario user = new Usuario();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM usuario WHERE username=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            int cont = 0;
            while (rs.next()) {
                cont++;
                user.setId_user(rs.getInt("id_usuario"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUser_type(rs.getInt("user_type"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhoto(rs.getString("photo"));
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    private boolean insertInfo(Usuario_info usuario_info, int chave) {
        boolean resp = false;
        String sql = "INSERT INTO usuario_info (id_usuario, nome, email) VALUES (?,?,?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, chave);
            ps.setString(2, usuario_info.getNome());
            ps.setString(3, usuario_info.getEmail());
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
    public boolean update(Usuario user) {
        boolean resp = false;
        String sql = "UPDATE usuario SET username=?,password=?,user_type=? WHERE id_usuario=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUser_type());
            ps.setInt(4, user.getId_user());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro: Usuário não pode ser atualizado !");
            } else {
                System.out.println("Usuário atualizado com sucesso !");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean delete(Usuario user) {
        boolean resp = false;
        int resposta = 0;
        String sql = "DELETE FROM usuario WHERE id_usuario=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId_user());

            if (this.deleteInfo(user.getId_user())) {
                resposta = ps.executeUpdate();
            }

            if (resposta == 0) {
                System.out.println("Erro ao remover usuário !");
            } else {
                System.out.println("Usuário removido com sucesso !");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    private boolean deleteInfo(int id_usuario) {
        boolean resp = false;
        String sql = "DELETE FROM usuario_info WHERE id_usuario=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao remover usuário !");
            } else {
                System.out.println("Usuário removido com sucesso !");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;

    }

}
