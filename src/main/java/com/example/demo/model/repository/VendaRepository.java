/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model.repository;

import com.example.demo.model.entity.Produto;
import com.example.demo.model.entity.Venda; 
import java.time.LocalDate;
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
public class VendaRepository {
    
    @PersistenceContext
    private EntityManager em;

    public void save(Venda venda){
        em.persist(venda);
    }

    public Venda venda(int id){
        return em.find(Venda.class, id);
    }

    
    public List<Venda> vendas(){
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }


    public void remove(int id){
        Venda v = em.find(Venda.class, id);
        em.remove(v);
    }

    public void update(Venda venda){
        em.merge(venda);
    }
    
    public List<Venda> vendas(LocalDate data) {
        String hql = "from Venda as v where v.data = :data";
        Query query = em.createQuery(hql, Venda.class);
        query.setParameter("data", data);
        return query.getResultList();
    }
    
}
