package com.ujc.students.dao;

import com.ujc.students.model.Pedido;
import com.ujc.students.model.Pedido.EstadoPedido;
import com.ujc.students.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class PedidoDaoImpl extends AbstractDao<Pedido, Integer> implements PedidoDao {

    @Autowired
    private ProdutoDao produtoDao;

    @Override
    public void criarPedido(Pedido pedido) {
        Produto produto = pedido.getProduto();
        if (produto == null)
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        BigDecimal valorTotal = produto.getPrecoUnitario().multiply(pedido.getQuantidade());
        pedido.setValorTotal(valorTotal);
        pedido.setEstado(EstadoPedido.PENDENTE);
        save(pedido);
        produtoDao.reduzirStock(produto.getId(), pedido.getQuantidade());
    }

    @Override
    public void actualizarEstado(Integer pedidoId, EstadoPedido novoEstado) {
        Pedido pedido = findById(pedidoId);
        if (pedido == null)
            throw new IllegalArgumentException("Pedido não encontrado: " + pedidoId);
        pedido.setEstado(novoEstado);
        update(pedido);
    }

    @Override
    public void cancelarPedido(Integer pedidoId) {
        Pedido pedido = findById(pedidoId);
        if (pedido == null)
            throw new IllegalArgumentException("Pedido não encontrado: " + pedidoId);
        if (pedido.getEstado() != EstadoPedido.PENDENTE)
            throw new IllegalStateException("Apenas pedidos PENDENTES podem ser cancelados.");
        pedido.setEstado(EstadoPedido.CANCELADO);
        update(pedido);
    }

    @Override
    public List<Pedido> listarPorComprador(Integer compradorId) {
        return createQuery(
            "SELECT p FROM Pedido p WHERE p.comprador.id = ?1", compradorId);
    }

    @Override
    public List<Pedido> listarPorEstado(EstadoPedido estado) {
        return createQuery(
            "SELECT p FROM Pedido p WHERE p.estado = ?1", estado);
    }
}
