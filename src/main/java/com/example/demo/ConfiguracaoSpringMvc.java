package com.example.demo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.example.primeirocontrollerspring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Configuration indica ao Spring que essa é uma classe de configuração.
 * Em seguida, é preciso estender a classe WebMvcConfigurer.
 * @author fagno
 */
@Configuration
public class ConfiguracaoSpringMvc implements WebMvcConfigurer{

    
    /**
     * Com a chamada a registry.addViewController(), estamos registrando um controller automático.
     * Com o método setViewName(), indicamos a view para atender a requisições direcionadas à URL / e /home. 
     * @param registry 
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");

    }

}
