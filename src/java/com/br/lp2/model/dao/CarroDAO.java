/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model.dao;

import com.br.lp2.model.Carro;
import com.br.lp2.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class CarroDAO implements GenericDAO<Carro> {

    private Connection conn;

    public CarroDAO() {
        //1. Realizar a conexão
        conn = ConnectionDB.getInstance();
    }

    @Override
    public boolean insert(Carro carro) {
        boolean resp = false;
        String sql = "INSERT INTO carro(marca,modelo,ano,quantidade,preco) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getModelo());
            ps.setInt(3, carro.getAno());
            ps.setInt(4, carro.getQuantidade());
            ps.setInt(5, carro.getPreco());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao inserir o carro");
            } else {
                System.out.println("Carro inserido com sucesso!");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public List<Carro> read() {
        List<Carro> carros = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM Carro";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                int id = rs.getInt("id_carro");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                int quantidade = rs.getInt("quantidade");
                int preco = rs.getInt("preco");
                Carro c = new Carro(id, marca, modelo, ano, quantidade, preco);
                carros.add(c);
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return carros;
    }

    public Carro readById(int id_carro) {
        Carro carro = new Carro();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM carro WHERE id_carro=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_carro);
            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                carro.setId_carro(rs.getInt("id_carro"));
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                carro.setAno(rs.getInt("ano"));
                carro.setQuantidade(rs.getInt("quantidade"));
                carro.setPreco(rs.getInt("preco"));
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return carro;
    }

    @Override
    public boolean update(Carro carro) {
        boolean resp = false;
        String sql = "UPDATE carro SET marca=?,modelo=?,ano=?,quantidade=?,preco=? WHERE id_carro=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getModelo());
            ps.setInt(3, carro.getAno());
            ps.setInt(4, carro.getQuantidade());
            ps.setInt(5, carro.getPreco());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao atualizar o carro");
            } else {
                System.out.println("Carro atualizado com sucesso!");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean delete(Carro carro) {
        boolean resp = false;
        String sql = "DELETE FROM carro WHERE id_carro=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, carro.getId_carro());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro ao remover o carro");
            } else {
                System.out.println("Carro removido com sucesso!");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

}
