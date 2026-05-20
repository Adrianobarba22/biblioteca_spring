package br.com.adriano.biblioteca.dtos;

import br.com.adriano.biblioteca.models.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto {

    private Integer id;
    private String nome;
    private String descricao;
    private List<Livro> livro = new ArrayList<>();

}
