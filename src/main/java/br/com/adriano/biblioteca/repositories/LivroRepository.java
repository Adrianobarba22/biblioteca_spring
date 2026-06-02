package br.com.adriano.biblioteca.repositories;

import br.com.adriano.biblioteca.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    // Busca todos os livros pertencentes a uma categoria (por id da categoria)
    //@Query("SELECT l FROM Livro l WHERE l.categoria.id = :categoriaId")
    List<Livro> findByCategoriaId(Integer categoriaId);
}
