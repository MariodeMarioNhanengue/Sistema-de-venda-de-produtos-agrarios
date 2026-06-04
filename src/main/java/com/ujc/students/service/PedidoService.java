package com.ujc.students.service;

import com.ujc.students.model.Pedido;
import com.ujc.students.model.Pedido.EstadoPedido;
import java.util.List;

public interface PedidoService {

    void salvar(Pedido pedido);
    void editar(Pedido pedido);
    void excluir(Long id);
    Pedido buscarPorId(Long id);
    List<Pedido> buscarTodos();

    void criarPedido(Pedido pedido);
    void actualizarEstado(Long pedidoId, EstadoPedido novoEstado);
    void cancelarPedido(Long pedidoId);
    List<Pedido> listarPorComprador(Long compradorId);
    List<Pedido> listarPorEstado(EstadoPedido estado);
}
