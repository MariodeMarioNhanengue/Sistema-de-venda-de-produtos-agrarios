package com.ujc.students.dao;

import com.ujc.students.model.Comprador;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CompradorDaoImpl extends AbstractDao<Comprador, Integer> implements CompradorDao {

    @Override
    public List<Comprador> buscarPorProvinciaResidencia(String provincia) {
        return createQuery(
            "SELECT c FROM Comprador c WHERE c.provinciaResidencia = ?1", provincia);
    }

    @Override
    public List<Comprador> buscarPorNomeEmpresa(String nomeEmpresa) {
        return createQuery(
            "SELECT c FROM Comprador c WHERE LOWER(c.nomeEmpresa) LIKE LOWER(?1)",
            "%" + nomeEmpresa + "%");
    }
}
