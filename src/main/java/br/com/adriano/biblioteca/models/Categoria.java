package br.com.adriano.biblioteca.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    @OneToMany(mappedBy = "categoria")
    private List<Livro> livro = new ArrayList<>();

    public Categoria(Integer id, String nome, String descricao, List<Livro> livro) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.livro = livro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }

    public Categoria() {




    }
}
