package com.ujc.students.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
@Entity
@Table(name = "entregas")
public class Entrega extends AbstractEntity<Long> {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private Pedido pedido;

    @Column(name = "provincia_destino", length = 100)
    private String provinciaDestino;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_entrega")
    private EstadoEntrega estadoEntrega;

    public enum EstadoEntrega {
        PENDENTE, EM_TRANSPORTE, ENTREGUE, CANCELADA
    }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public String getProvinciaDestino() { return provinciaDestino; }
    public void setProvinciaDestino(String provinciaDestino) { this.provinciaDestino = provinciaDestino; }

    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public LocalDateTime getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDateTime dataEntrega) { this.dataEntrega = dataEntrega; }

    public EstadoEntrega getEstadoEntrega() { return estadoEntrega; }
    public void setEstadoEntrega(EstadoEntrega estadoEntrega) { this.estadoEntrega = estadoEntrega; }
}
