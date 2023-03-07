package br.com.ada.Catalogo.dao;

import br.com.ada.Catalogo.model.Filme;
import br.com.ada.Catalogo.model.Noticia;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NoticiaDAO {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<Noticia> noticias = new ArrayList<>();
    private static int proxId = 1;

    static {
        try {
            noticias = objectMapper.readValue(
                    new File("noticias.json"),
                    new TypeReference<List<Noticia>>() {});

            System.out.println("arquivo lido");

            if (noticias.size() > 0) proxId = noticias.get(noticias.size() - 1).getId() +1;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionar(Noticia noticia) {
        noticia.setId(proxId++);
        noticias.add(noticia);
        salvarNoticia();
    }

    public void editar(Noticia noticia) {
        for (int i = 0; i < noticias.size(); i++) {
            Noticia n = noticias.get(i);
            if (n.getId() == noticia.getId()) {
                noticias.set(i, noticia);
                break;
            }
        }
        salvarNoticia();
    }

    public void remover(int id) {
        noticias.removeIf(n -> n.getId() == id);
        salvarNoticia();
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

    private static void salvarNoticia() {
        try {
            objectMapper.writeValue(new File("noticias.json"), noticias);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
