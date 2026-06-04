package com.ujc.students.service;

import com.ujc.students.model.Produto;
import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {

    void salvar(Produto produto);
    void editar(Produto produto);
    void excluir(Integer id);
    Produto buscarPorId(Integer id);
    List<Produto> buscarTodos();

    List<Produto> listarPorAgricultor(Integer agricultorId);
    List<Produto> listarPorCategoria(String categoria);
    List<Produto> listarPorProvinciaOrigem(String provincia);
    List<Produto> listarComStockDisponivel();
    void reduzirStock(Integer produtoId, BigDecimal quantidade);
}
