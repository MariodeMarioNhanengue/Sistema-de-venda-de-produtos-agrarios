package com.ujc.students.dao;

import com.ujc.students.model.Entrega;
import com.ujc.students.model.Entrega.EstadoEntrega;
import java.util.List;

public interface EntregaDao {

    void save(Entrega entrega);
    void update(Entrega entrega);
    void delete(Integer id);
    Entrega findById(Integer id);
    List<Entrega> findAll();

    void criarEntrega(Entrega entrega);
    void confirmarEntrega(Integer entregaId);
    List<Entrega> listarPorProvinciaDestino(String provincia);
    List<Entrega> listarPorEstado(EstadoEntrega estado);
}
