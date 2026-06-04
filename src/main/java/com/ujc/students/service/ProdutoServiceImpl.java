package com.ujc.students.service;

import com.ujc.students.dao.ProdutoDao;
import com.ujc.students.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoDao dao;

    @Override
    public void salvar(Produto produto) {
        dao.save(produto);
    }

    @Override
    public void editar(Produto produto) {
        dao.update(produto);
    }

    @Override
    public void excluir(Integer id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Produto buscarPorId(Integer id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> listarPorAgricultor(Integer agricultorId) {
        return dao.listarPorAgricultor(agricultorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> listarPorCategoria(String categoria) {
        return dao.listarPorCategoria(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> listarPorProvinciaOrigem(String provincia) {
        return dao.listarPorProvinciaOrigem(provincia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produto> listarComStockDisponivel() {
        return dao.listarComStockDisponivel();
    }

    @Override
    public void reduzirStock(Integer produtoId, BigDecimal quantidade) {
        dao.reduzirStock(produtoId, quantidade);
    }
}
