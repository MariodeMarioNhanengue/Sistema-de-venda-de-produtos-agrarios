package com.ujc.students.service;

import com.ujc.students.model.Pedido;
import com.ujc.students.model.Pedido.EstadoPedido;
import java.util.List;

public interface PedidoService {

    void salvar(Pedido pedido);
    void editar(Pedido pedido);
    void excluir(Integer id);
    Pedido buscarPorId(Integer id);
    List<Pedido> buscarTodos();

    void criarPedido(Pedido pedido);
    void actualizarEstado(Integer pedidoId, EstadoPedido novoEstado);
    void cancelarPedido(Integer pedidoId);
    List<Pedido> listarPorComprador(Integer compradorId);
    List<Pedido> listarPorEstado(EstadoPedido estado);
}
