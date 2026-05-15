package br.com.adriano.biblioteca.controllers;

import br.com.adriano.biblioteca.models.Categoria;
import br.com.adriano.biblioteca.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Integer id){
        Categoria cat =  categoriaService.buscarPorId(id);
        return cat;
        //return categoriaService.buscarPorId(id);
    }

    @GetMapping
    public List<Categoria> buscarTodos(){
        List<Categoria> categorias = categoriaService.buscarTodos();
        return categorias;
    }

    @PostMapping
    public Categoria save(@RequestBody Categoria categoria) {
        Categoria cat = categoriaService.save(categoria);
            return cat;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoriaService.delete(id);
    }
}
