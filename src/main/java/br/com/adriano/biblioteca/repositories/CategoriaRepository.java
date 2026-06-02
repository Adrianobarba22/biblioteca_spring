package br.com.adriano.biblioteca.repositories;

import br.com.adriano.biblioteca.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    //@Query("SELECT c FROM Categoria c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    Optional<Categoria> findByNomeContainingIgnoreCase(String nome);
    Optional<Categoria> findByNomeIgnoreCase(String nome);
}

