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

    // GET /entrega/{data_pedido} — Consultar entregas por data do pedido (formato: YYYY-MM-DD)
    @GetMapping("/{data_pedido}")
    public ResponseEntity<List<Entrega>> buscarPorDataPedido(@PathVariable("data_pedido") String dataPedido) {
        // Tenta interpretar como data (YYYY-MM-DD); se não corresponder, trata como província
        if (dataPedido.matches("\\d{4}-\\d{2}-\\d{2}")) {
            List<Entrega> lista = service.buscarTodos().stream()
                .filter(e -> e.getDataPedido() != null &&
                             e.getDataPedido().toLocalDate().toString().equals(dataPedido))
                .toList();
            return ResponseEntity.ok(lista);
        }
        // GET /entrega/{provincia_destino} — Consultar entregas por província de destino
        return ResponseEntity.ok(service.listarPorProvinciaDestino(dataPedido));
    }
}
