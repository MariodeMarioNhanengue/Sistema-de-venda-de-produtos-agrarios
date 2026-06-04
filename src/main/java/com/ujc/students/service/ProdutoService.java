package com.ujc.students.service;

import com.ujc.students.model.Produto;
import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {

    void salvar(Produto produto);
    void editar(Produto produto);
    void excluir(Long id);
    Produto buscarPorId(Long id);
    List<Produto> buscarTodos();

    List<Produto> listarPorAgricultor(Long agricultorId);
    List<Produto> listarPorCategoria(String categoria);
    List<Produto> listarPorProvinciaOrigem(String provincia);
    List<Produto> listarComStockDisponivel();
    void reduzirStock(Long produtoId, BigDecimal quantidade);
}
