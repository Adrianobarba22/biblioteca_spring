package br.com.adriano.biblioteca.controllers;

import br.com.adriano.biblioteca.dtos.CategoriaDto;
import br.com.adriano.biblioteca.models.Categoria;
import br.com.adriano.biblioteca.services.CategoriaService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/nomes/{nome}")
    public ResponseEntity<List<CategoriaDto>> buscarPorNome(@PathVariable String nome) {
        log.info("Buscando categorias com nome: " + nome);
        List<Categoria> categorias = categoriaService.buscarPorNome(nome);
        log.info("Categorias encontradas: " + categorias.size());
        return ResponseEntity.ok().body(categorias.stream().map(cat -> modelMapper.
                map(cat, CategoriaDto.class)).toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Integer id){
        log.info("Buscando categoria com id: " + id);
        Categoria cat =  categoriaService.buscarPorId(id);
        log.info("Categoria encontrada: " + cat.getNome());
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
        //return categoriaService.buscarPorId(id);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> buscarTodos(){
        log.info("Buscando todas as categorias");
        List<Categoria> categorias = categoriaService.buscarTodos();
        log.info("Categorias encontradas: " + categorias.size());
        return ResponseEntity.ok().body(categorias.stream().map(cat -> modelMapper.
                map(cat, CategoriaDto.class)).toList());
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaDto categoriaDto) {
        log.info("Salvando categoria: " + categoriaDto.getNome());
        Categoria cat = categoriaService.save(modelMapper.map(categoriaDto, Categoria.class));
        log.info("Categoria salva com id: " + cat.getId());
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Integer id, @RequestBody CategoriaDto categoriaDto) {
        Categoria cat = categoriaService.buscarPorId(id);
        cat.setNome(categoriaDto.getNome());
        cat.setDescricao(categoriaDto.getDescricao());
        return ResponseEntity.ok().body(modelMapper.map(categoriaService.update(id, cat), CategoriaDto.class));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        log.info("Deletando categoria com id: " + id);
        categoriaService.delete(id);
        log.info("Categoria deletada com id: " + id);
        return ResponseEntity.status(204).build();
    }
}
