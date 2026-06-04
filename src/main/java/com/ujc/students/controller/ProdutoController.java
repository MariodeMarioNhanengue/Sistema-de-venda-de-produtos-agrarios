package com.ujc.students.controller;

import com.ujc.students.model.Produto;
import com.ujc.students.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    // GET /produto — Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> lista = service.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    // GET /produto/{id} — Consultar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        Produto produto = service.buscarPorId(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }

    // POST /produto — Registar um ou vários produtos
    @PostMapping
    public ResponseEntity<Void> registar(@RequestBody List<Produto> produtos) {
        produtos.forEach(service::salvar);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // PUT /produto/{id} — Actualizar dados de produto
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id,
                                           @RequestBody Produto produto) {
        Produto existente = service.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id);
        service.editar(produto);
        return ResponseEntity.ok().build();
    }

    // DELETE /produto/{id} — Remover produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        Produto existente = service.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
