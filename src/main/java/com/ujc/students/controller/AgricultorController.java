package com.ujc.students.controller;

import com.ujc.students.model.Agricultor;
import com.ujc.students.service.AgricultorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agricultor")
public class AgricultorController {

    @Autowired
    private AgricultorService service;

    // GET /agricultor — Listar todos os agricultores
    @GetMapping
    public ResponseEntity<List<Agricultor>> listar() {
        List<Agricultor> lista = service.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    // GET /agricultor/{id} — Consultar agricultor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Agricultor> buscarPorId(@PathVariable Long id) {
        Agricultor agricultor = service.buscarPorId(id);
        if (agricultor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agricultor);
    }

    // GET /agricultor/provincia/{provincia} — Consultar agricultor por província
    @GetMapping("/provincia/{provincia}")
    public ResponseEntity<List<Agricultor>> buscarPorProvincia(@PathVariable String provincia) {
        List<Agricultor> lista = service.buscarPorProvincia(provincia);
        return ResponseEntity.ok(lista);
    }

    // POST /agricultor — Registar agricultor
    @PostMapping
    public ResponseEntity<Void> registar(@RequestBody Agricultor agricultor) {
        service.salvar(agricultor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // PUT /agricultor/{id} — Actualizar dados de agricultor
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Long id,
                                           @RequestBody Agricultor agricultor) {
        Agricultor existente = service.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        agricultor.setId(id);
        service.editar(agricultor);
        return ResponseEntity.ok().build();
    }

    // DELETE /agricultor/{id} — Remover agricultor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Agricultor existente = service.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
