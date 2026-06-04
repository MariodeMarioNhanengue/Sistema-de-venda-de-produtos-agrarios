package com.ujc.students.controller;

import com.ujc.students.model.Comprador;
import com.ujc.students.service.CompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comprador")
public class CompradorController {

    @Autowired
    private CompradorService service;

    // GET /comprador — Listar todos os compradores
    @GetMapping
    public ResponseEntity<List<Comprador>> listar() {
        List<Comprador> lista = service.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    // GET /comprador/{id} — Consultar comprador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comprador> buscarPorId(@PathVariable Integer id) {
        Comprador comprador = service.buscarPorId(id);
        if (comprador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comprador);
    }

    // GET /comprador/provincia/{provincia} — Consultar comprador por província
    @GetMapping("/provincia/{provincia}")
    public ResponseEntity<List<Comprador>> buscarPorProvincia(@PathVariable String provincia) {
        List<Comprador> lista = service.buscarPorProvinciaResidencia(provincia);
        return ResponseEntity.ok(lista);
    }

    // GET /comprador/empresa/{nomeEmpresa} — Consultar comprador por nome de empresa
    @GetMapping("/empresa/{nomeEmpresa}")
    public ResponseEntity<List<Comprador>> buscarPorNomeEmpresa(@PathVariable String nomeEmpresa) {
        List<Comprador> lista = service.buscarPorNomeEmpresa(nomeEmpresa);
        return ResponseEntity.ok(lista);
    }

    // POST /comprador — Cadastro de um comprador
    @PostMapping()
    public ResponseEntity<Void> cadastrarVarios(@RequestBody List<Comprador> compradores) {
        compradores.forEach(service::salvar);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

  

    // PUT /comprador/{id} — Actualizar dados de comprador
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id,
                                           @RequestBody Comprador comprador) {
        Comprador existente = service.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        comprador.setId(id);
        service.editar(comprador);
        return ResponseEntity.ok().build();
    }

    // DELETE /comprador/{id} — Remover um comprador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        Comprador existente = service.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}