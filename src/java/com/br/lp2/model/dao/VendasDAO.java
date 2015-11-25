/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model.dao;

import com.br.lp2.model.Carro;
import com.br.lp2.model.Usuario_info;
import com.br.lp2.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 31338283
 */
public class VendasDAO implements GenericDAO<Vendas> {

    private Connection conn;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public VendasDAO() {
        //1. Realizar a conexão
        conn = ConnectionDB.getInstance();
    }

    @Override
    public List<Vendas> read() {
        List<Vendas> vendas = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM vendas v "
                + "INNER JOIN usuario_info inf ON inf.id_usuario = v.id_usuario "
                + "INNER JOIN carro c ON c.id_carro = v.id_carro";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId_carro(rs.getInt("id_carro"));
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                carro.setAno(rs.getInt("ano"));
                carro.setQuantidade(rs.getInt("quantidade"));
                carro.setPreco(rs.getInt("preco"));

                Usuario_info usuario = new Usuario_info();
                usuario.setId_usuario_info(rs.getInt("id_usuario_info"));
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));

                Vendas venda = new Vendas();
                venda.setCarro(carro);
                venda.setUsuario_info(usuario);
                venda.setId_vendas(rs.getInt("id_venda"));
                venda.setId_carro(rs.getInt("id_carro"));
                venda.setId_usuario(rs.getInt("id_usuario"));
                venda.setData_venda(rs.getDate("data_venda"));
                venda.setVenda_status(rs.getString("venda_status"));
                vendas.add(venda);
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vendas;
    }

    @Override
    public boolean insert(Vendas vendas) {
        boolean resp = false;
        String sql = "INSERT INTO vendas(id_carro,id_usuario) VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vendas.getId_carro());
            ps.setInt(2, vendas.getId_usuario());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao inserir a Venda");
            } else {
                System.out.println("Venda inserida com sucesso!");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean update(Vendas vendas) {
        boolean resp = false;
        String sql = "UPDATE vendas SET id_carro=?,id_usuario=?,data_venda=?,venda_status=? WHERE id_venda=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vendas.getId_carro());
            ps.setInt(2, vendas.getId_usuario());
            java.sql.Date sqlDate = new java.sql.Date(vendas.getData_venda().getTime());
            ps.setDate(3, sqlDate);
            ps.setString(4, vendas.getVenda_status());
            ps.setInt(5, vendas.getId_vendas());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao atualizar a venda");
            } else {
                System.out.println("Venda atualizada com sucesso!");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean delete(Vendas vendas) {
        boolean resp = false;
        String sql = "DELETE FROM vendas WHERE id_vendas=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vendas.getId_vendas());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao remover Venda");
            } else {
                System.out.println("Venda removida com sucesso!");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public Vendas readById(int id_venda) {
        Vendas venda = new Vendas();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM vendas WHERE id_venda=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_venda);
            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            int cont = 0;
            while (rs.next()) {
                cont++;
                venda.setId_vendas(rs.getInt("id_venda"));
                venda.setId_carro(rs.getInt("id_carro"));
                venda.setId_usuario(rs.getInt("id_usuario"));
                venda.setData_venda(rs.getDate("data_venda"));
                venda.setVenda_status(rs.getString("venda_status"));
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return venda;
    }

    public List<Vendas> readByStatus(String where) {
        List<Vendas> vendas = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM vendas v "
                + "INNER JOIN usuario_info inf ON inf.id_usuario = v.id_usuario "
                + "INNER JOIN carro c ON c.id_carro = v.id_carro "
                + "WHERE venda_status=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, where);

            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId_carro(rs.getInt("id_carro"));
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                carro.setAno(rs.getInt("ano"));
                carro.setQuantidade(rs.getInt("quantidade"));
                carro.setPreco(rs.getInt("preco"));

                Usuario_info usuario = new Usuario_info();
                usuario.setId_usuario_info(rs.getInt("id_usuario_info"));
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));

                Vendas venda = new Vendas();
                venda.setCarro(carro);
                venda.setUsuario_info(usuario);
                venda.setId_vendas(rs.getInt("id_venda"));
                venda.setId_carro(rs.getInt("id_carro"));
                venda.setId_usuario(rs.getInt("id_usuario"));
                venda.setData_venda(rs.getDate("data_venda"));
                venda.setVenda_status(rs.getString("venda_status"));
                vendas.add(venda);
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vendas;
    }
    
    public List<Vendas> readByUsuario(int where) {
        List<Vendas> vendas = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM vendas v "
                + "INNER JOIN carro c ON c.id_carro = v.id_carro "
                + "WHERE id_usuario=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, where);

            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId_carro(rs.getInt("id_carro"));
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                carro.setAno(rs.getInt("ano"));
                carro.setQuantidade(rs.getInt("quantidade"));
                carro.setPreco(rs.getInt("preco"));

                Vendas venda = new Vendas();
                venda.setCarro(carro);
                venda.setId_vendas(rs.getInt("id_venda"));
                venda.setId_carro(rs.getInt("id_carro"));
                venda.setId_usuario(rs.getInt("id_usuario"));
                venda.setData_venda(rs.getDate("data_venda"));
                venda.setVenda_status(rs.getString("venda_status"));
                vendas.add(venda);
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vendas;
    }

}
