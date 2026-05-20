package br.com.adriano.biblioteca.services;

import br.com.adriano.biblioteca.models.Categoria;
import br.com.adriano.biblioteca.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> cat = categoriaRepository.findById(id);
        return cat.get();
    }

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Integer id, Categoria categoria) {
        Categoria cat = buscarPorId(id);
        cat.setNome(categoria.getNome());
        cat.setDescricao(categoria.getDescricao());
        return categoriaRepository.save(cat);
    }

    public void delete(Integer id) {
        categoriaRepository.deleteById(id);
    }

    public List<Categoria> buscarPorNome(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }
}
