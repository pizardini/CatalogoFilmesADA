package br.com.ada.Catalogo.dao;

import br.com.ada.Catalogo.model.Filme;
import br.com.ada.Catalogo.model.Noticia;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoticiaDAO {

    private static List<Noticia> noticias = new ArrayList<>();
    private static int proxId = 1;

    public void adicionar(Noticia noticia) {
        noticia.setId(proxId++);
        noticias.add(noticia);
    }

    public void editar(Noticia noticia) {
        for (int i = 0; i < noticias.size(); i++) {
            Noticia n = noticias.get(i);
            if (n.getId() == noticia.getId()) {
                noticias.set(i, noticia);
                break;
            }
        }
    }

    public void remover(int id) {
        noticias.removeIf(n -> n.getId() == id);
    }

    public Noticia buscaId(int id) {
        return noticias.stream()
                .filter(n -> n.getId() == id)
                .findAny()
                .orElse(null);
    }

    public List<Noticia> buscarTodos() {
        return noticias;
    }
}
