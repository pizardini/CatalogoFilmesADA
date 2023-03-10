package br.com.ada.Catalogo.dao;

import br.com.ada.Catalogo.model.Ator;
import br.com.ada.Catalogo.model.Filme;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AtorDAO {

    private static List<Ator> atores = new ArrayList<>();

    private static int proxId = 1;

    public void adicionar(Ator ator) {
        ator.setId(proxId++);
        atores.add(ator);
    }

    public void editar(Ator ator) {
        for (int i = 0; i < atores.size(); i++) {
            Ator a = atores.get(i);
            if (a.getId() == ator.getId()) {
                atores.set(i, ator);
                break;
            }
        }
    }

    public void remover(int id) {
        atores.removeIf(f -> f.getId() == id);
    }

    public Ator buscaId(int id) {
        return atores.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(null);
    }

    public List<Ator> buscarTodos() {
        return atores;
    }
}
