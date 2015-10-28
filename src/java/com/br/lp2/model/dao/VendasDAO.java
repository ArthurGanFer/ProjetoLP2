/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model.dao;

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
        String sql = "SELECT * FROM vendas";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                int id = rs.getInt("id_venda");
                int id_carro = rs.getInt("id_carro");
                int id_usuario = rs.getInt("id_usuario");
                Date data_venda = rs.getDate("data_venda");
                Vendas v = new Vendas();
                vendas.add(v);
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
        String sql = "INSERT INTO vendas(id_carro,id_usuario,data_venda) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vendas.getId_carro());
            ps.setInt(2, vendas.getId_usuario());
            java.sql.Date sqlDate = new java.sql.Date(vendas.getData_venda().getTime());
            ps.setDate(3, sqlDate);
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
        String sql = "UPDATE vendas SET id_carro=?,id_usuario=?,data_venda=? WHERE id_vendas=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vendas.getId_carro());
            ps.setInt(2, vendas.getId_usuario());
            java.sql.Date sqlDate = new java.sql.Date(vendas.getData_venda().getTime());
            ps.setDate(3, sqlDate);
            ps.setInt(4, vendas.getId_vendas());
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

}
