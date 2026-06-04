package com.ujc.students.dao;

import com.ujc.students.model.Pedido;
import com.ujc.students.model.Pedido.EstadoPedido;
import java.util.List;

public interface PedidoDao {

    void save(Pedido pedido);
    void update(Pedido pedido);
    void delete(Integer id);
    Pedido findById(Integer id);
    List<Pedido> findAll();

    void criarPedido(Pedido pedido);
    void actualizarEstado(Integer pedidoId, EstadoPedido novoEstado);
    void cancelarPedido(Integer pedidoId);
    List<Pedido> listarPorComprador(Integer compradorId);
    List<Pedido> listarPorEstado(EstadoPedido estado);
}
