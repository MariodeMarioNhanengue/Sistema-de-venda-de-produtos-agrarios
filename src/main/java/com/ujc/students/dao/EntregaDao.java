package com.ujc.students.dao;

import com.ujc.students.model.Entrega;
import com.ujc.students.model.Entrega.EstadoEntrega;
import java.util.List;

public interface EntregaDao {

    void save(Entrega entrega);
    void update(Entrega entrega);
    void delete(Long id);
    Entrega findById(Long id);
    List<Entrega> findAll();

    void criarEntrega(Entrega entrega);
    void confirmarEntrega(Long entregaId);
    List<Entrega> listarPorProvinciaDestino(String provincia);
    List<Entrega> listarPorEstado(EstadoEntrega estado);
}
