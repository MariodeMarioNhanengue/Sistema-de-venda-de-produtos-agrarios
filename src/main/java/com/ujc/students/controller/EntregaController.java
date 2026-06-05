package com.ujc.students.controller;

import com.ujc.students.model.Entrega;
import com.ujc.students.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

    @Autowired
    private EntregaService service;

    // GET /entrega — Listar todas as entregas
    @GetMapping
    public ResponseEntity<List<Entrega>> listar() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    // POST /entrega — Criar uma ou várias entregas (pedido deve estar APROVADO)
    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody List<Entrega> entregas) {
        entregas.forEach(service::criarEntrega);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // PUT /entrega/{id}/confirmar — Confirmar entrega (estado → ENTREGUE, pedido → ENTREGUE)
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmar(@PathVariable Integer id) {
        Entrega existente = service.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        service.confirmarEntrega(id);
        return ResponseEntity.ok().build();
    }

    // GET /entrega/{data_pedido} — Consultar por data (YYYY-MM-DD) ou por província
    @GetMapping("/{data_pedido}")
    public ResponseEntity<List<Entrega>> buscarPorDataOuProvincia(
            @PathVariable("data_pedido") String valor) {
        if (valor.matches("\\d{4}-\\d{2}-\\d{2}")) {
            List<Entrega> lista = service.buscarTodos().stream()
                .filter(e -> e.getDataPedido() != null &&
                             e.getDataPedido().toLocalDate().toString().equals(valor))
                .toList();
            return ResponseEntity.ok(lista);
        }
        return ResponseEntity.ok(service.listarPorProvinciaDestino(valor));
    }
}
