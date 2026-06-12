package br.com.adriano.biblioteca.controllers;

import br.com.adriano.biblioteca.dtos.CategoriaDto;
import br.com.adriano.biblioteca.dtos.LivroDto;
import br.com.adriano.biblioteca.models.Categoria;
import br.com.adriano.biblioteca.models.Livro;
import br.com.adriano.biblioteca.services.CategoriaService;
import br.com.adriano.biblioteca.services.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "Endpoints de gerenciamento de categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LivroService livroService;

    @GetMapping("/{id}")
    @Operation(summary = "Listar categorias")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Integer id) {
        //log.info("GET /categoria/{} - buscarPorId() called", id);
        Categoria cat = categoriaService.buscarPorId(id);
        //log.info("Result buscarPorId id={} -> {}", id, cat);
        CategoriaDto categoriaDto = modelMapper.map(cat, CategoriaDto.class);
        return ResponseEntity.ok().body(categoriaDto);
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Listar categorias")
    public ResponseEntity<CategoriaDto> buscarPorNome(@PathVariable String nome) {
        Categoria cat = categoriaService.buscarPorNome(nome);
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));

    }

    @GetMapping
    @Operation(summary = "Listar categorias")
    public ResponseEntity<List<CategoriaDto>> buscarTodos() {
        //log.info("GET /categoria - buscarTodos() called");
        List<Categoria> list = categoriaService.buscarTodos();
        //.info("Result buscarTodos -> count={}", list == null ? 0 : list.size());
        return ResponseEntity.ok().body(list.stream().map(x -> modelMapper.
                map(x, CategoriaDto.class)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}/livros")
    @Operation(summary = "Listar categorias")
    public ResponseEntity<List<LivroDto>> buscarLivrosPorCategoria(@PathVariable Integer id) {
        List<Livro> list = livroService.buscarPorCategoria(id);
        List<LivroDto> dtoList = list.stream().map(x -> modelMapper.map(x, LivroDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @PostMapping
    @Operation(summary = "Cadastrar categoria")
    public ResponseEntity<CategoriaDto> save(@Valid @RequestBody CategoriaDto categoriaDto) {
        Categoria cat = categoriaService.save(modelMapper.map(categoriaDto, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Integer id,@Valid @RequestBody CategoriaDto categoriaDto) {
        categoriaDto.setId(id);
        Categoria cat = categoriaService.atualizar(modelMapper.map(categoriaDto, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        //log.info("DELETE /categoria/{} - delete() called", id);
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
        //log.info("Deleted categoria id={}", id);
    }
}