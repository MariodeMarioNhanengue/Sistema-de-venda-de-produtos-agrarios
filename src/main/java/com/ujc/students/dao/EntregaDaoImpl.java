package com.ujc.students.dao;

import com.ujc.students.model.Entrega;
import com.ujc.students.model.Entrega.EstadoEntrega;
import com.ujc.students.model.Pedido.EstadoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EntregaDaoImpl extends AbstractDao<Entrega, Long> implements EntregaDao {

    @Autowired
    private PedidoDao pedidoDao;

    @Override
    public void criarEntrega(Entrega entrega) {
        if (entrega.getPedido() == null)
            throw new IllegalArgumentException("Pedido não pode ser nulo.");
        if (entrega.getPedido().getEstado() != EstadoPedido.APROVADO)
            throw new IllegalStateException("Só é possível criar entrega para pedidos APROVADOS.");
        entrega.setDataPedido(LocalDateTime.now());
        entrega.setEstadoEntrega(EstadoEntrega.PENDENTE);
        save(entrega);
        pedidoDao.actualizarEstado(entrega.getPedido().getId(), EstadoPedido.EM_TRANSPORTE);
    }

    @Override
    public void confirmarEntrega(Long entregaId) {
        Entrega entrega = findById(entregaId);
        if (entrega == null)
            throw new IllegalArgumentException("Entrega não encontrada: " + entregaId);
        entrega.setEstadoEntrega(EstadoEntrega.ENTREGUE);
        entrega.setDataEntrega(LocalDateTime.now());
        update(entrega);
        pedidoDao.actualizarEstado(entrega.getPedido().getId(), EstadoPedido.ENTREGUE);
    }

    @Override
    public List<Entrega> listarPorProvinciaDestino(String provincia) {
        return createQuery(
            "SELECT e FROM Entrega e WHERE e.provinciaDestino = ?1", provincia);
    }

    @Override
    public List<Entrega> listarPorEstado(EstadoEntrega estado) {
        return createQuery(
            "SELECT e FROM Entrega e WHERE e.estadoEntrega = ?1", estado);
    }
}
