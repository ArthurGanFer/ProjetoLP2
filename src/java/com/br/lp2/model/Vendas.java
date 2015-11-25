/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 31338283
 */
public class Vendas implements Serializable {

    private int id_vendas; //primaria
    private int id_carro; //chave estrangeira de carro
    private int id_usuario; //chave estrangeira de usuario
    private Date data_venda;
    private String venda_status;
    private Usuario_info usuario_info;
    private Carro carro;

    public Vendas() {
    }

    public Vendas(int id_vendas, int id_carro, int id_usuario, Date data_venda, String venda_status, Usuario_info usuario_info, Carro carro) {
        this.id_vendas = id_vendas;
        this.id_carro = id_carro;
        this.id_usuario = id_usuario;
        this.data_venda = data_venda;
        this.venda_status = venda_status;
        this.usuario_info = usuario_info;
        this.carro = carro;
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

    public String getVenda_status() {
        return venda_status;
    }

    public void setVenda_status(String venda_status) {
        this.venda_status = venda_status;
    }

    public Usuario_info getUsuario_info() {
        return usuario_info;
    }

    public void setUsuario_info(Usuario_info usuario_info) {
        this.usuario_info = usuario_info;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    @Override
    public String toString() {
        return "Vendas{" + "id_vendas=" + id_vendas + ", id_carro=" + id_carro + ", id_usuario=" + id_usuario + ", data_venda=" + data_venda + ", venda_status=" + venda_status + ", usuario_info=" + usuario_info + ", carro=" + carro + '}';
    }

}
