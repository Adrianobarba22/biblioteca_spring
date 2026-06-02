package br.com.adriano.biblioteca.controllers;

import br.com.adriano.biblioteca.dtos.CategoriaDto;
import br.com.adriano.biblioteca.dtos.LivroDto;
import br.com.adriano.biblioteca.models.Categoria;
import br.com.adriano.biblioteca.models.Livro;
import br.com.adriano.biblioteca.services.CategoriaService;
import br.com.adriano.biblioteca.services.LivroService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LivroService livroService;


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Integer id) {
        log.info("Buscando categoria com id: " + id);
        Categoria cat = categoriaService.buscarPorId(id);
        log.info("Categoria encontrada: " + cat.getNome());
        CategoriaDto categoriaDto = modelMapper.map(cat, CategoriaDto.class);
        return ResponseEntity.ok().body(categoriaDto);
        //return categoriaService.buscarPorId(id);
    }

    @GetMapping("/nomes/{nome}")
    public ResponseEntity<CategoriaDto> buscarPorNome(@PathVariable String nome) {
        log.info("Buscando categorias com nome: " + nome);
        Categoria cat = categoriaService.buscarPorNome(nome);
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }


    @GetMapping
    public ResponseEntity<List<CategoriaDto>> buscarTodos() {
        log.info("Buscando todas as categorias");
        List<Categoria> categorias = categoriaService.buscarTodos();
        log.info("Categorias encontradas: " + categorias.size());
        return ResponseEntity.ok().body(categorias.stream().map(cat -> modelMapper.
                map(cat, CategoriaDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}/livros")
    public ResponseEntity<List<LivroDto>> buscarLivrosPorCategoria(@PathVariable Integer id) {
        log.info("Buscando livros para a categoria com id: " + id);
        List<Livro> livros = livroService.buscarPorCategoria(id);
        List<LivroDto> livroDtos = livros.stream()
                .map(livro -> modelMapper.map(livro, LivroDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(livroDtos);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> salvar(@Valid @RequestBody CategoriaDto categoriaDto) {
        log.info("Salvando nova categoria: " + categoriaDto.getNome());
        Categoria cat = categoriaService.save(modelMapper.map(categoriaDto, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaDto categoriaDto) {
        log.info("Atualizando categoria com id: " + id);
        categoriaDto.setId(id);
        Categoria cat = categoriaService.update(modelMapper.map(categoriaDto, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        log.info("Deletando categoria com id: " + id);
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
