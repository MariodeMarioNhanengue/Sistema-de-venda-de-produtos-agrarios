package com.ujc.students.dao;

import com.ujc.students.model.Agricultor;
import java.util.List;

public interface AgricultorDao {

    void save(Agricultor agricultor);
    void update(Agricultor agricultor);
    void delete(Integer id);
    Agricultor findById(Integer id);
    List<Agricultor> findAll();

    List<Agricultor> buscarPorProvincia(String provincia);
    List<Agricultor> buscarPorDistrito(String distrito);
    List<Agricultor> buscarPorTelefone(String telefone);
}
