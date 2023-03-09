package br.com.ada.Catalogo.dao;

import br.com.ada.Catalogo.model.Filme;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmeDAO {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static List<Filme> filmes = new ArrayList<>();
    private static List<Filme> favoritos = new ArrayList<>();
    private static int proxId = 1;

    static {
        try {
            filmes = objectMapper.readValue(
                    new File("filmes.json"),
                    new TypeReference<List<Filme>>() {});

            System.out.println("arquivo lido");

            if (filmes.size() > 0) proxId = filmes.get(filmes.size() - 1).getId() +1;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionar(Filme filme) {
        filme.setId(proxId++);
        filme.setLikes(0);
        filme.setFavorito(false);
        filmes.add(filme);
        salvarFilme();
    }

    public void editar(Filme filme) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == filme.getId()) {
                filmes.set(i, filme);
                break;
            }
        }
        salvarFilme();
    }

    public void remover(int id) {
        filmes.removeIf(f -> f.getId() == id);
        salvarFilme();
    }

    public void like(int id) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == id) {
                f.setLikes(f.getLikes()+1);
            }
        }
    }

    public void dislike(int id) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme f = filmes.get(i);
            if (f.getId() == id) {
                f.setLikes(f.getLikes()-1);
            }
        }
    }

//    public void favoritar(int id) {
//        for (int i = 0; i < filmes.size(); i++) {
//            Filme f = filmes.get(i);
//            if (f.getId() == id) {
//                favoritos.add(f);
//            }
//        }
//    }
//
//    public void desfavoritar(int id) {
//        favoritos.removeIf(f -> f.getId() == id);
//    }

    public void favoritar(int id) {
        filmes.stream()
                .filter(filme -> filme.getId() == id)
                .forEach(filmeResposta -> filmeResposta.setFavorito(true));
    }

    public void desfavoritar(int id) {
        filmes.stream()
                .filter(filme -> filme.getId() == id)
                .forEach(filmeResposta -> filmeResposta.setFavorito(false));
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


    private static void salvarFilme() {
        try {
            objectMapper.writeValue(new File("filmes.json"), filmes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Filme> buscarFavoritos(){
        return filmes.stream()
                .filter(Filme::getFavorito)
                .collect(Collectors.toList());
    }
}
