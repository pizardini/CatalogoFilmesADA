package br.com.ada.Catalogo.controller;

import br.com.ada.Catalogo.dao.AtorDAO;
import br.com.ada.Catalogo.model.Ator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ator")
public class AtorController {

    @Autowired
    private AtorDAO atorDAO;

    @GetMapping
    public String listar(Model model) {
        List<Ator> lista = atorDAO.buscarTodos();
        model.addAttribute("atores", lista);
        return "ator_listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Ator ator = atorDAO.buscaId(id);
        model.addAttribute("ator", ator);
        return "ator_editar";
    }

    @PostMapping("/editar")
    public String atualizar(Ator ator) {
        atorDAO.editar(ator);
        return "redirect:/home";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        atorDAO.remover(id);
        return "redirect:/home";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("ator", new Ator());
        return "ator_novo";
    }

    @PostMapping("/novo")
    public String adicionar(Ator ator) {
        atorDAO.adicionar(ator);
        return "redirect:/home";
    }
}
