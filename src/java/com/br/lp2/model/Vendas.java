/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model;

import java.util.Date;

/**
 *
 * @author 31338283
 */
public class Vendas {

    private int id_vendas; //primaria
    private int id_carro; //chave estrangeira de carro
    private int id_usuario; //chave estrangeira de usuario
    private Date data_venda;

    public Vendas() {
    }

    public Vendas(int id_vendas, int id_carro, int id_usuario, Date data_venda) {
        this.id_vendas = id_vendas;
        this.id_carro = id_carro;
        this.id_usuario = id_usuario;
        this.data_venda = data_venda;
    }

    public int getId_vendas() {
        return id_vendas;
    }

    public void setId_vendas(int id_vendas) {
        this.id_vendas = id_vendas;
    }

    public int getId_carro() {
        return id_carro;
    }

    public void setId_carro(int id_carro) {
        this.id_carro = id_carro;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    @Override
    public String toString() {
        return "Vendas{" + "id_vendas=" + id_vendas + ", id_carro=" + id_carro + ", id_usuario=" + id_usuario + ", data_venda=" + data_venda + '}';
    }

}
