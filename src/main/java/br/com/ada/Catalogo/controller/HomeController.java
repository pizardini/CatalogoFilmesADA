package br.com.ada.Catalogo.controller;

import br.com.ada.Catalogo.dao.FilmeDAO;
import br.com.ada.Catalogo.dao.NoticiaDAO;
import br.com.ada.Catalogo.model.Filme;
import br.com.ada.Catalogo.model.Noticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private FilmeDAO filmeDAO;
    @Autowired
    private NoticiaDAO noticiaDAO;

    @GetMapping
    public String listarTudo(Model model) {
        List<Filme> listaFilmes = filmeDAO.buscarTodos();
        List<Noticia> listaNoticias = noticiaDAO.buscarTodos();
        model.addAttribute("filmes", listaFilmes);
        model.addAttribute("noticias", listaNoticias);
        return "listar";
    }
}
