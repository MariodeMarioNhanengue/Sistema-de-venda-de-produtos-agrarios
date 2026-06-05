package com.ujc.students.dao;

import com.ujc.students.model.Usuario;

public interface UsuarioDao {
    void save(Usuario usuario);
    Usuario findByUsername(String username);
}
