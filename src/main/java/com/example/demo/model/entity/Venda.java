/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model.entity;
 
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList; 
import java.util.Date;   
import java.util.List; 
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany; 
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author CHFS
 */
@Scope("session")
@Component
@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    private LocalDate data = LocalDate.now();

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    @NotNull
    //essa classe Cascade que é responsável em salvar as coisas na tabela ItemVenda
    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST)
    private List<ItemVenda> itemvendas = new ArrayList();
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public List<ItemVenda> getItemvendas() {
        return itemvendas;
    }

    public void setItemvendas(List<ItemVenda> itemvendas) {
        this.itemvendas = itemvendas;
    }
    
    
    public double total(){
        double total=0;
        for(ItemVenda i : itemvendas){
            total += i.total();
        }
        return total;
    }
}
        
   
    