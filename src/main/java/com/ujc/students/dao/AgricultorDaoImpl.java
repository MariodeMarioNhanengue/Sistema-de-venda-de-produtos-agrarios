package com.ujc.students.dao;

import com.ujc.students.model.Agricultor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AgricultorDaoImpl extends AbstractDao<Agricultor, Long> implements AgricultorDao {

    @Override
    public List<Agricultor> buscarPorProvincia(String provincia) {
        return createQuery(
            "SELECT a FROM Agricultor a WHERE a.provincia = ?1", provincia);
    }

    @Override
    public List<Agricultor> buscarPorDistrito(String distrito) {
        return createQuery(
            "SELECT a FROM Agricultor a WHERE a.distrito = ?1", distrito);
    }

    @Override
    public List<Agricultor> buscarPorTelefone(String telefone) {
        return createQuery(
            "SELECT a FROM Agricultor a WHERE a.telefone = ?1", telefone);
    }
}
