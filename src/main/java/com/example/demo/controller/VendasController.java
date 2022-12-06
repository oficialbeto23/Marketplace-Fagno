/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller; 
  
import com.example.demo.model.entity.ItemVenda;
import com.example.demo.model.entity.Pessoa;
import com.example.demo.model.entity.PessoaFisica;
import com.example.demo.model.entity.Produto;
import com.example.demo.model.entity.Venda;
import com.example.demo.model.repository.PessoaFisicaRepository;
import com.example.demo.model.repository.ProdutoRepository;
import com.example.demo.model.repository.VendaRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@Scope("request")//esse aqui também eu coloquei agora
@Transactional
@Controller
@RequestMapping("vendas")
public class VendasController {
    
    @Autowired
    VendaRepository repository;
    
    @Autowired
    ProdutoRepository repositoryP;
    
    @Autowired
    PessoaFisicaRepository repositoryC;
    
    
    @Autowired
    Venda venda ;
    
    
    
    
    
    public VendasController(){
        
    }
    
    
    @ResponseBody
    @GetMapping("/exemplo")
    public String exemplo(){
        return "Controller de Vendas!";
    }


    
    
    @GetMapping("/list_detalhes/{id}")
    public ModelAndView listarvendas(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/vendas/list_detalhes", model);
    }
    

    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("vendas", repository.vendas());
        return new ModelAndView("/vendas/list", model);
    }
    
    
    
    
    //método para adicionar ao carrinho
    @GetMapping("/carrinho")
    public ModelAndView carrinho( ModelMap model) {
        model.addAttribute("produtos", repositoryP.produtos());
        //model.addAttribute("pessoafisica", repositoryC.pessoasFisica());
        return new ModelAndView("/vendas/carrinho", model);
    }
     
    
    @GetMapping("/form")
    public ModelAndView form(ItemVenda item, ModelMap model) {
        model.addAttribute("produtos", repositoryP.produtos());
        model.addAttribute("pessoas", repositoryC.pessoasFisicas());
        return new ModelAndView("/vendas/list_para_vendas", model);
    }
    
    @PostMapping("/add") 
    public ModelAndView add(@Valid ItemVenda item, BindingResult result, ModelMap model) {
       if (result.hasErrors()) {
           return form(item, model);//para manter o objeto com dados preenchidos
       }
        Produto produto = repositoryP.produto(item.getProduto().getId());
        item.setProduto(produto);   
        item.setVenda(venda);
        venda.getItemvendas().add(item);
        return new ModelAndView("redirect:/vendas/form");
    }
    
    //ate aqui
    
    
    
    
    /*
    @GetMapping("/form")
    public String form(Produto produto){
        return "/vendas/form";
    }
    */
    
    
    //tenho que continuar a mexer deste código aqui em diante
     
    @PostMapping("/save")
    public ModelAndView save( PessoaFisica pf, RedirectAttributes attributes ) {//precisa ClientePF
        Calendar c = Calendar.getInstance();
        if(pf.getId() == 0){
            attributes.addFlashAttribute("errocliente", "Selecione um cliente!!!");  
        }
        
        if(venda.getItemvendas().isEmpty()){
            attributes.addFlashAttribute("erro", "Não possui itens no carrinho!!!");
        } 
        if(!attributes.getFlashAttributes().isEmpty()){
            return new ModelAndView("redirect:/vendas/form");
        }
        
        PessoaFisica pessoa = repositoryC.pessoaFisica(pf.getId());
        venda.setPessoa(pessoa);
        this.venda.setId(0);
        repository.save(venda);  
        venda.getItemvendas().clear();
        attributes.addFlashAttribute("chave", "Venda Concluída com Sucesso!!");
        return new ModelAndView("redirect:/vendas/list");//o metodo do controller
    }
    
   

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("venda", repository.venda(id));
        return new ModelAndView("/vendas/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        repository.update(venda);
        return new ModelAndView("redirect:/vendas/list");
    }
    
    
    @GetMapping("/remove/{posicao}")
    public ModelAndView remove(@PathVariable("posicao") int posicao, RedirectAttributes attributes) {
        venda.getItemvendas().remove(posicao);
        attributes.addFlashAttribute("chave", "Produto Excluído do Carrinho!!");
        return new ModelAndView("redirect:/vendas/form");
    }
    
}
