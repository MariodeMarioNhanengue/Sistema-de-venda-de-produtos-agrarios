package com.ujc.students.service;

import com.ujc.students.dao.AgricultorDao;
import com.ujc.students.model.Agricultor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class AgricultorServiceImpl implements AgricultorService {

    @Autowired
    private AgricultorDao dao;

    @Override
    public void salvar(Agricultor agricultor) {
        dao.save(agricultor);
    }

    @Override
    public void editar(Agricultor agricultor) {
        dao.update(agricultor);
    }

    @Override
    public void excluir(Integer id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Agricultor buscarPorId(Integer id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agricultor> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agricultor> buscarPorProvincia(String provincia) {
        return dao.buscarPorProvincia(provincia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agricultor> buscarPorDistrito(String distrito) {
        return dao.buscarPorDistrito(distrito);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agricultor> buscarPorTelefone(String telefone) {
        return dao.buscarPorTelefone(telefone);
    }
}
