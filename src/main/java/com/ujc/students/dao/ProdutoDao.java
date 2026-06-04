package com.ujc.students.dao;

import com.ujc.students.model.Produto;
import java.math.BigDecimal;
import java.util.List;

public interface ProdutoDao {

    void save(Produto produto);
    void update(Produto produto);
    void delete(Integer id);
    Produto findById(Integer id);
    List<Produto> findAll();

    List<Produto> listarPorAgricultor(Integer agricultorId);
    List<Produto> listarPorCategoria(String categoria);
    List<Produto> listarPorProvinciaOrigem(String provincia);
    List<Produto> listarComStockDisponivel();
    void reduzirStock(Integer produtoId, BigDecimal quantidade);
}
