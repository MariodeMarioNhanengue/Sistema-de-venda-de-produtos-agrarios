package com.ujc.students.service;

import com.ujc.students.dao.PedidoDao;
import com.ujc.students.model.Pedido;
import com.ujc.students.model.Pedido.EstadoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoDao dao;

    @Override
    public void salvar(Pedido pedido) {
        dao.save(pedido);
    }

    @Override
    public void editar(Pedido pedido) {
        dao.update(pedido);
    }

    @Override
    public void excluir(Integer id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido buscarPorId(Integer id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public void criarPedido(Pedido pedido) {
        dao.criarPedido(pedido);
    }

    @Override
    public void actualizarEstado(Integer pedidoId, EstadoPedido novoEstado) {
        dao.actualizarEstado(pedidoId, novoEstado);
    }

    @Override
    public void cancelarPedido(Integer pedidoId) {
        dao.cancelarPedido(pedidoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listarPorComprador(Integer compradorId) {
        return dao.listarPorComprador(compradorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listarPorEstado(EstadoPedido estado) {
        return dao.listarPorEstado(estado);
    }
}
