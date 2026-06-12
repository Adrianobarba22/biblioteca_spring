package br.com.adriano.biblioteca.services;

import br.com.adriano.biblioteca.exceptions.ObjectNotFoundException;
import br.com.adriano.biblioteca.models.Categoria;
import br.com.adriano.biblioteca.repositories.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> cat = categoriaRepository.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException(" Categoria não encontrada " + id));

    }

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Categoria categoria) {
        buscarPorId(categoria.getId());
        procurarPorNome(categoria);
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria buscarPorNome(String nome) {
        Optional<Categoria> cat = categoriaRepository.findByNomeContainingIgnoreCase(nome);
        if (cat.isPresent()) {
            return cat.get();
        }
        throw new ObjectNotFoundException("Categoria não encontrada com este nome: " + nome);
    }

    private void procurarPorNome(Categoria categoria) {
        Optional<Categoria> cat = categoriaRepository.findByNomeIgnoreCase(categoria.getNome());
        if (cat.isPresent()) {
            if (cat.get().getId() != categoria.getId()) {
                throw new IllegalArgumentException("Categoria já cadastrada com este nome: " + categoria.getNome());
            }
        }
    }
}