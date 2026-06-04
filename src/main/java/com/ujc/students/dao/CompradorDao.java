package com.ujc.students.dao;

import com.ujc.students.model.Comprador;
import java.util.List;

public interface CompradorDao {

    void save(Comprador comprador);
    void update(Comprador comprador);
    void delete(Integer id);
    Comprador findById(Integer id);
    List<Comprador> findAll();

    List<Comprador> buscarPorProvinciaResidencia(String provincia);
    List<Comprador> buscarPorNomeEmpresa(String nomeEmpresa);
}
