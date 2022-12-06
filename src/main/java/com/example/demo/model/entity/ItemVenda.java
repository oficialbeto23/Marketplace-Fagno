/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;  
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne; 
import javax.persistence.Table; 
import javax.validation.constraints.Positive;

/**
 *
 * @author CHFS
 */
@Entity
@Table(name = "tb_itemVenda")
public class ItemVenda implements Serializable{
    
    
    @Id
    @GeneratedValue
    private int id;
    
    @Positive(message="Insira um valor para quantidade Maior ou Igual a 1")
    private double qtd;
   
    
    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    
    //associaçao entre Produto e ItemVenda
    @OneToOne
    //@JoinColumn(name = "id_produto")
    private Produto produto;
    
    //associaçao entre Venda e ItemVenda
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    
    public double total(){
        return qtd*produto.getValor();
    }

    
    
    
    
}
