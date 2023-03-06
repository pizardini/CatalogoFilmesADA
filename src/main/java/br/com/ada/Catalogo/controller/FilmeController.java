package br.com.ada.Catalogo.controller;

import br.com.ada.Catalogo.dao.FilmeDAO;
import br.com.ada.Catalogo.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private FilmeDAO filmeDAO;

    @GetMapping
    public String listar(Model model) {
        List<Filme> lista = filmeDAO.buscarTodos();
        model.addAttribute("filmes", lista);
        return "filme_listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Filme filme = filmeDAO.buscaId(id);
        model.addAttribute("filme", filme);
        return "filme_editar";
    }

    @PostMapping("/editar")
    public String atualizar(Filme filme) {
        filmeDAO.editar(filme);
        return "redirect:/filme";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        filmeDAO.remover(id);
        return "redirect:/filme";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("filme", new Filme());
        return "filme_novo";
    }

    @PostMapping("/novo")
    public String adicionar(Filme filme) {
        filmeDAO.adicionar(filme);
        return "redirect:/filme";
    }
}
