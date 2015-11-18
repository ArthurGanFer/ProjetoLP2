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
        String sql = "SELECT * FROM usuario u"
                + "INNER JOIN usuario_info i ON u.id_usuario = i.id_usuario";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId_user(rs.getInt("id_user"));
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

            if (cont == 0) {
                throw new UserException(UserException.USER_NOT_FOUND);
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
        String sql = "UPDATE user_lp2 SET username=?,password=?,user_type=?,birthday=?,photo=? WHERE id_user=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUser_type());
            ps.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
            ps.setString(5, user.getPhoto());
            ps.setInt(6, user.getId_user());
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
        String sql = "DELETE FROM usuario WHERE id_usuario=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId_user());
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
