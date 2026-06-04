package com.ujc.students.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@Entity
@Table(name = "produtos")
public class Produto extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agricultor_id", nullable = false)
    private Agricultor agricultor;

    @Column(name = "nome_produto", nullable = false, length = 150)
    private String nomeProduto;

    @Column(name = "categoria", length = 100)
    private String categoria;

    @Column(name = "quantidade_disponivel", precision = 12, scale = 2)
    private BigDecimal quantidadeDisponivel;

    @Column(name = "unidade_medida", length = 30)
    private String unidadeMedida;

    @Column(name = "preco_unitario", precision = 12, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "provincia_origem", length = 100)
    private String provinciaOrigem;

    @Column(name = "imagem", length = 500)
    private String imagem;

    public Agricultor getAgricultor() { return agricultor; }
    public void setAgricultor(Agricultor agricultor) { this.agricultor = agricultor; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(BigDecimal quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }

    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }

    public String getProvinciaOrigem() { return provinciaOrigem; }
    public void setProvinciaOrigem(String provinciaOrigem) { this.provinciaOrigem = provinciaOrigem; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
}
