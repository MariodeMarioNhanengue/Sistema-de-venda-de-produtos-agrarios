package com.ujc.students.model;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "agricultores")
public class Agricultor extends AbstractEntity<Integer> {

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "apelido", nullable = false, length = 100)
    private String apelido;

    @Column(name = "provincia", length = 100)
    private String provincia;
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 20)
    private Genero genero;

    @Column(name = "distrito", length = 100)
    private String distrito;

    @Column(name = "telefone", length = 20)
    private String telefone;

    public enum Genero { MASCULINO, FEMININO, OUTRO }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
