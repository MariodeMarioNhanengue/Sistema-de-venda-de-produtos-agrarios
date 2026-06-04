package com.ujc.students.service;

import com.ujc.students.dao.EntregaDao;
import com.ujc.students.model.Entrega;
import com.ujc.students.model.Entrega.EstadoEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class EntregaServiceImpl implements EntregaService {

    @Autowired
    private EntregaDao dao;

    @Override
    public void salvar(Entrega entrega) {
        dao.save(entrega);
    }

    @Override
    public void editar(Entrega entrega) {
        dao.update(entrega);
    }

    @Override
    public void excluir(Integer id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Entrega buscarPorId(Integer id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entrega> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public void criarEntrega(Entrega entrega) {
        dao.criarEntrega(entrega);
    }

    @Override
    public void confirmarEntrega(Integer entregaId) {
        dao.confirmarEntrega(entregaId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entrega> listarPorProvinciaDestino(String provincia) {
        return dao.listarPorProvinciaDestino(provincia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entrega> listarPorEstado(EstadoEntrega estado) {
        return dao.listarPorEstado(estado);
    }
}
