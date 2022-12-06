/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;


import com.example.demo.model.repository.ProdutoRepository;
import com.example.demo.model.entity.Produto;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author CHFS
 */
@Transactional
@Controller
@RequestMapping("produtos")
public class ProdutosController {
    @Autowired
    ProdutoRepository repository;
    
    public ProdutosController(){
        
    }
    
    @ResponseBody
    @GetMapping("/exemplo")
    public String exemplo(){
        return "Controller de Produtos!";
    }


    

    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("produtos", repository.produtos());
        //return new 
        return new ModelAndView("/produtos/list", model);
    }
    
    @GetMapping("/listparacompra")
    public ModelAndView listarparacompra(ModelMap model){
        model.addAttribute("produtos", repository.produtos());
        //return new 
        return new ModelAndView("/produtos/listparacompra", model);
    }
    
    
        
    
    @GetMapping("/form")
    public ModelAndView form(Produto produto) {//alterei para modelandview em todos
        return new ModelAndView ("/produtos/form"); //a pagina html
    }
    
    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            return form(produto);//para manter o objeto com dados preenchidos
        }
        repository.save(produto);
        attributes.addFlashAttribute("chave", "Produto Cadastrado Com Sucesso!");
        return new ModelAndView("redirect:/produtos/list");
    }
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") int id, RedirectAttributes attributes){
        repository.remove(id);
        attributes.addFlashAttribute("chave", "Excluído Com Sucesso!");
        return new ModelAndView("redirect:/produtos/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("produto", repository.produto(id));
        return new ModelAndView("/produtos/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return form(produto);//para manter o objeto com dados preenchidos
        }
        repository.update(produto);
        attributes.addFlashAttribute("chave", "Produto Atualizado Com Sucesso!");
        return new ModelAndView("redirect:/produtos/list");
    }
    
    

}
