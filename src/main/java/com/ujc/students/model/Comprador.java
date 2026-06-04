package com.ujc.students.model;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "compradores")
public class Comprador extends AbstractEntity<Integer> {

    @Column(name = "nome_empresa", nullable = false, length = 150)
    private String nomeEmpresa;

    @Column(name = "nome_responsavel", nullable = false, length = 150)
    private String nomeResponsavel;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "provincia_residencia", length = 100)
    private String provinciaResidencia;

    @Column(name = "distrito_residencia", length = 100)
    private String distritoResidencia;

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getProvinciaResidencia() { return provinciaResidencia; }
    public void setProvinciaResidencia(String provinciaResidencia) { this.provinciaResidencia = provinciaResidencia; }

    public String getDistritoResidencia() { return distritoResidencia; }
    public void setDistritoResidencia(String distritoResidencia) { this.distritoResidencia = distritoResidencia; }
}
