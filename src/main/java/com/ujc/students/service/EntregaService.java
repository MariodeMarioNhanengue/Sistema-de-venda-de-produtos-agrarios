package com.ujc.students.service;

import com.ujc.students.model.Entrega;
import com.ujc.students.model.Entrega.EstadoEntrega;
import java.util.List;

public interface EntregaService {

    void salvar(Entrega entrega);
    void editar(Entrega entrega);
    void excluir(Integer id);
    Entrega buscarPorId(Integer id);
    List<Entrega> buscarTodos();

    void criarEntrega(Entrega entrega);
    void confirmarEntrega(Integer entregaId);
    List<Entrega> listarPorProvinciaDestino(String provincia);
    List<Entrega> listarPorEstado(EstadoEntrega estado);
}
