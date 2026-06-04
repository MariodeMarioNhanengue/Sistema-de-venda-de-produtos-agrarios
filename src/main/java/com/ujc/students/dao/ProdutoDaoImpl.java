package com.ujc.students.dao;

import com.ujc.students.model.Produto;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProdutoDaoImpl extends AbstractDao<Produto, Long> implements ProdutoDao {

    @Override
    public List<Produto> listarPorAgricultor(Long agricultorId) {
        return createQuery(
            "SELECT p FROM Produto p WHERE p.agricultor.id = ?1", agricultorId);
    }

    @Override
    public List<Produto> listarPorCategoria(String categoria) {
        return createQuery(
            "SELECT p FROM Produto p WHERE p.categoria = ?1", categoria);
    }

    @Override
    public List<Produto> listarPorProvinciaOrigem(String provincia) {
        return createQuery(
            "SELECT p FROM Produto p WHERE p.provinciaOrigem = ?1", provincia);
    }

    @Override
    public List<Produto> listarComStockDisponivel() {
        return createQuery(
            "SELECT p FROM Produto p WHERE p.quantidadeDisponivel > ?1",
            BigDecimal.ZERO);
    }

    @Override
    public void reduzirStock(Long produtoId, BigDecimal quantidade) {
        Produto produto = findById(produtoId);
        if (produto == null)
            throw new IllegalArgumentException("Produto não encontrado: " + produtoId);
        BigDecimal novaQtd = produto.getQuantidadeDisponivel().subtract(quantidade);
        if (novaQtd.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalStateException("Stock insuficiente para: " + produto.getNomeProduto());
        produto.setQuantidadeDisponivel(novaQtd);
        update(produto);
    }
}
