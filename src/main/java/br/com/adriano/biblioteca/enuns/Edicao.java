package br.com.adriano.biblioteca.enuns;

public enum Edicao {

    PRIMEIRA(0),
    SEGUNDA(1),
    TERCEIRA(2);

    private int codigo;

    Edicao(int codigo){
        this.codigo = codigo;
    }
}
