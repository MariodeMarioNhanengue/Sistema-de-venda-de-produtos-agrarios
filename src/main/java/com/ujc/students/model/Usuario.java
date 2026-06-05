package com.ujc.students.model;

import jakarta.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "usuarios")
public class Usuario extends AbstractEntity<Integer> {

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false, length = 20)
    private Perfil perfil;

    public enum Perfil {
        ADMIN, AGRICULTOR, COMPRADOR
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }
}
