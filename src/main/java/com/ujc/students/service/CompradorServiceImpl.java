package com.ujc.students.service;

import com.ujc.students.dao.CompradorDao;
import com.ujc.students.model.Comprador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class CompradorServiceImpl implements CompradorService {

    @Autowired
    private CompradorDao dao;

    @Override
    public void salvar(Comprador comprador) {
        dao.save(comprador);
    }

    @Override
    public void editar(Comprador comprador) {
        dao.update(comprador);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Comprador buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comprador> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comprador> buscarPorProvinciaResidencia(String provincia) {
        return dao.buscarPorProvinciaResidencia(provincia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comprador> buscarPorNomeEmpresa(String nomeEmpresa) {
        return dao.buscarPorNomeEmpresa(nomeEmpresa);
    }
}
