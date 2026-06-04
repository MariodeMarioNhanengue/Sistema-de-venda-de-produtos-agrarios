package com.ujc.students.controller;

import com.ujc.students.model.Pedido;
import com.ujc.students.model.Pedido.EstadoPedido;
import com.ujc.students.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    // GET /pedido — Listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        List<Pedido> lista = service.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    // GET /pedido/{id} — Consultar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Integer id) {
        Pedido pedido = service.buscarPorId(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    // GET /pedido/nome_produto/{nomeProduto} — Consultar pedidos por nome do produto
    @GetMapping("/nome_produto/{nomeProduto}")
    public ResponseEntity<List<Pedido>> buscarPorNomeProduto(@PathVariable String nomeProduto) {
        List<Pedido> lista = service.buscarTodos().stream()
            .filter(p -> p.getProduto() != null &&
                         p.getProduto().getNomeProduto()
                          .toLowerCase().contains(nomeProduto.toLowerCase()))
            .toList();
        return ResponseEntity.ok(lista);
    }

    // POST /pedido — Criar um ou vários pedidos
    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody List<Pedido> pedidos) {
        pedidos.forEach(service::criarPedido);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // PUT /pedido/{id} — Actualizar estado do pedido
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id,
                                           @RequestBody Pedido pedido) {
        Pedido existente = service.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        pedido.setId(id);
        service.editar(pedido);
        return ResponseEntity.ok().build();
    }
}
