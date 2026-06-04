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
        List<Entrega> lista = service.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    // GET /entrega/data_pedido/{dataPedido} — Consultar entregas por data do pedido
    @GetMapping("/data_pedido/{dataPedido}")
    public ResponseEntity<List<Entrega>> buscarPorDataPedido(@PathVariable String dataPedido) {
        List<Entrega> lista = service.buscarTodos().stream()
            .filter(e -> e.getDataPedido() != null &&
                         e.getDataPedido().toLocalDate().toString().equals(dataPedido))
            .toList();
        return ResponseEntity.ok(lista);
    }

    // GET /entrega/provincia_destino/{provinciaDestino} — Consultar entregas por província de destino
    @GetMapping("/provincia_destino/{provinciaDestino}")
    public ResponseEntity<List<Entrega>> buscarPorProvinciaDestino(@PathVariable String provinciaDestino) {
        List<Entrega> lista = service.listarPorProvinciaDestino(provinciaDestino);
        return ResponseEntity.ok(lista);
    }

    // POST /entrega — Criar uma ou várias entregas
    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody List<Entrega> entregas) {
        entregas.forEach(service::criarEntrega);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
