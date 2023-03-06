package br.com.ada.Catalogo.dao;

import br.com.ada.Catalogo.model.Filme;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmeDAO {

    private static List<Filme> filmes = new ArrayList<>();
    private static int proxId = 1;

    public void adicionar(Filme filme) {
        filme.setId(proxId++);
        filmes.add(filme);
    }

    public void editar(Filme filme) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == filme.getId()) {
                filmes.set(i, filme);
                break;
            }
        }
    }

    public void remover(int id) {
        filmes.removeIf(f -> f.getId() == id);
    }

    public Filme buscaId(int id) {
        return filmes.stream()
                .filter(f -> f.getId() == id)
                .findAny()
                .orElse(null);
    }

    public List<Filme> buscarTodos() {
        return filmes;
    }
}
