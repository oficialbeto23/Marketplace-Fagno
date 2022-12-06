/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller; 

import com.example.demo.model.entity.PessoaFisica;
import com.example.demo.model.repository.PessoaFisicaRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author CHFS
 */
@Transactional
@Controller
@RequestMapping("pessoasfisica")
public class PessoasFisicaController {
    
    
    @Autowired
    PessoaFisicaRepository repository;
     
    //aqui
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("pessoasfisica", repository.pessoasFisicas());
        //return new 
        return new ModelAndView("/pessoasfisica/list", model);
    }
        
    @GetMapping("/form")
    public ModelAndView form(PessoaFisica pessoaFisica){
        return new ModelAndView("/pessoasfisica/form") ;
    }
    
    @PostMapping("/save")
    public ModelAndView save(@Valid PessoaFisica pessoaFisica, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return form(pessoaFisica);//para manter o objeto com dados preenchidos
        }
        repository.save(pessoaFisica);
        attributes.addFlashAttribute("chave", "Cliente Cadastrado Com Sucesso!");
        return new ModelAndView("redirect:/pessoasfisica/list");
    }
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") int id, RedirectAttributes attributes){
        repository.remove(id);
        attributes.addFlashAttribute("chave", "Excluído Com Sucesso!");
        return new ModelAndView("redirect:/pessoasfisica/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("pessoaFisica", repository.pessoaFisica(id));
        return new ModelAndView("/pessoasfisica/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid PessoaFisica pessoaFisica, RedirectAttributes attributes) {
        repository.update(pessoaFisica);
        attributes.addFlashAttribute("chave", "Cadastro do Cliente Atualizado Com Sucesso!");
        return new ModelAndView("redirect:/pessoasfisica/list");
    }
    
}
