package com.ujc.students.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
@Entity
@Table(name = "pedidos")
public class Pedido extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comprador_id", nullable = false)
    private Comprador comprador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade", precision = 12, scale = 2)
    private BigDecimal quantidade;

    @Column(name = "valor_total", precision = 14, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPedido estado;

    public enum EstadoPedido {
        PENDENTE, APROVADO, EM_TRANSPORTE, ENTREGUE, CANCELADO
    }

    @PrePersist
    protected void onCreate() {
        if (dataPedido == null) dataPedido = LocalDateTime.now();
        if (estado == null) estado = EstadoPedido.PENDENTE;
    }

    public Comprador getComprador() { return comprador; }
    public void setComprador(Comprador comprador) { this.comprador = comprador; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }
}
