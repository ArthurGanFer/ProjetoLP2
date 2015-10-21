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
public class Carro {

    private int id_carro; //chave primaria
    private String marca;
    private String modelo;
    private int ano;
    private int quantidade;

    public Carro() {
    }

    public Carro(int id_carro, String marca, String modelo, int ano, int quantidade) {
        this.id_carro = id_carro;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.quantidade = quantidade;
    }

    public int getId_carro() {
        return id_carro;
    }

    public void setId_carro(int id_carro) {
        this.id_carro = id_carro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Carro{" + "id_carro=" + id_carro + ", marca=" + marca + ", modelo=" + modelo + ", ano=" + ano + ", quantidade=" + quantidade + '}';
    }

}
