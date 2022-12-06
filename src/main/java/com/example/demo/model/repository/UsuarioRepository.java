/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model.repository;

import com.example.demo.model.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CHFS
 */
@Repository
public class UsuarioRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public Usuario usuario(String login){
        return em.find(Usuario.class, login);
    }
    
}
