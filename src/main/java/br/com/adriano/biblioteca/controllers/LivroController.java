package br.com.adriano.biblioteca.controllers;

import br.com.adriano.biblioteca.dtos.LivroDto;
import br.com.adriano.biblioteca.models.Livro;
import br.com.adriano.biblioteca.services.LivroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable Integer id) {
        Livro livro = livroService.buscarPorId(id);
        return ResponseEntity.ok().body(new LivroDto(livro));
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> buscarPorCategoria(@RequestParam(value = "categoria", defaultValue = "0") Integer categoriaId) {
        List<Livro> livros = livroService.buscarPorCategoria(categoriaId);
        return ResponseEntity.ok().body(livros.stream().map(livro -> new LivroDto(livro)).collect(Collectors.toList()));
    }
    
    @PostMapping
    public ResponseEntity<LivroDto> salvar(@RequestParam(value = "categoria", defaultValue = "0") Integer categoriaId, @RequestBody LivroDto livroDto) {
        Livro livro = livroService.salvar(categoriaId, livroDto);
        return ResponseEntity.ok().body(modelMapper.map(livro, LivroDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
