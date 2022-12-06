/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model.repository;

import com.example.demo.model.entity.PessoaFisica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CHFS
 */
@Repository
public class PessoaFisicaRepository {
    
@PersistenceContext
    private EntityManager em;
 
    public void save(PessoaFisica pessoaFisica){
        em.persist(pessoaFisica);
    }

    public PessoaFisica pessoaFisica(int id){
        return em.find(PessoaFisica.class, id);
    }

    public List<PessoaFisica> pessoasFisicas(){
        Query query = em.createQuery("from PessoaFisica");
        return query.getResultList();
    }

    public void remove(int id){
        PessoaFisica p = em.find(PessoaFisica.class, id);
        em.remove(p);
    }

    public void update(PessoaFisica pessoaFisica){
        em.merge(pessoaFisica);
    }
    
    public List<PessoaFisica> pessoasfisicas(String nome){//filtrando cliente
        String hql = "from ClientePF as v where v.nome = :nome";
        Query query = em.createQuery(hql, PessoaFisica.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }
    
    
    
}
