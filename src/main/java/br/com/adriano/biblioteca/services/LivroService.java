package br.com.adriano.biblioteca.services;

import br.com.adriano.biblioteca.dtos.LivroDto;
import br.com.adriano.biblioteca.exceptions.ObjectNotFoundException;
import br.com.adriano.biblioteca.models.Categoria;
import br.com.adriano.biblioteca.models.Livro;
import br.com.adriano.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro buscarPorId(Integer id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new ObjectNotFoundException("Livro não encontrado com este id: " + id);
    }

    public List<Livro> buscarPorCategoria(Integer categoriaId) {
        categoriaService.buscarPorId(categoriaId); // Verifica se a categoria existe
        List<Livro> livros = livroRepository.findByCategoriaId(categoriaId);
        return livros;
    }

    public void deletar(Integer id) {
        buscarPorId(id); // Verifica se o livro existe
        livroRepository.deleteById(id);
    }

    public Livro salvar(Integer categoriaId, LivroDto livroDto) {
        Categoria categoria = categoriaService.buscarPorId(categoriaId); // Verifica se a categoria existe
        Livro livro = new Livro();
        livro.setTitulo(livroDto.getTitulo());
        livro.setAutor(livroDto.getAutor());
        livro.setTexto(livroDto.getTexto());
        livro.setEdicao(livroDto.getEdicao());
        livro.setCategoria(categoria);
        return livroRepository.save(livro);
    }
}

