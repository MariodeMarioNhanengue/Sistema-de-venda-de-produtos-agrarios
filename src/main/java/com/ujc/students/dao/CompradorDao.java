package com.ujc.students.dao;

import com.ujc.students.model.Comprador;
import java.util.List;

public interface CompradorDao {

    void save(Comprador comprador);
    void update(Comprador comprador);
    void delete(Long id);
    Comprador findById(Long id);
    List<Comprador> findAll();

    List<Comprador> buscarPorProvinciaResidencia(String provincia);
    List<Comprador> buscarPorNomeEmpresa(String nomeEmpresa);
}
