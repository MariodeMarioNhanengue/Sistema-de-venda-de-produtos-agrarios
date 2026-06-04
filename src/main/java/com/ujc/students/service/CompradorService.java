package com.ujc.students.service;

import com.ujc.students.model.Comprador;
import java.util.List;

public interface CompradorService {

    void salvar(Comprador comprador);
    void editar(Comprador comprador);
    void excluir(Integer id);
    Comprador buscarPorId(Integer id);
    List<Comprador> buscarTodos();

    List<Comprador> buscarPorProvinciaResidencia(String provincia);
    List<Comprador> buscarPorNomeEmpresa(String nomeEmpresa);
}
