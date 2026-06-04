package com.ujc.students.service;

import com.ujc.students.model.Agricultor;
import java.util.List;

public interface AgricultorService {

    void salvar(Agricultor agricultor);
    void editar(Agricultor agricultor);
    void excluir(Long id);
    Agricultor buscarPorId(Long id);
    List<Agricultor> buscarTodos();

    List<Agricultor> buscarPorProvincia(String provincia);
    List<Agricultor> buscarPorDistrito(String distrito);
    List<Agricultor> buscarPorTelefone(String telefone);
}
