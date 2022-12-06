/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model.entity;
 
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author CHFS
 */
@Entity
@DiscriminatorValue("F")
public class PessoaFisica extends Pessoa{
    
    @NotBlank(message = "Nome não pode estar em branco!")
    private String nome;
    //@NotNull
    //@Size(min = 11, max = 11, message ="CPF precisa ter 11 digitos!")
    @CPF( message ="Número de CPF Inválido!")
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
