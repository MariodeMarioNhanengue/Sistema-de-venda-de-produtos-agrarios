package com.ujc.students.dao;

import com.ujc.students.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public Usuario findByUsername(String username) {
        try {
            return em.createQuery(
                "SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
