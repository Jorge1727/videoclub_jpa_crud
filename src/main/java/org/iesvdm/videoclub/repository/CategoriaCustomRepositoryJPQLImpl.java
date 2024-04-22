package org.iesvdm.videoclub.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class CategoriaCustomRepositoryJPQLImpl implements CategoriaCustomRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Categoria> queryCustomCategoria(Optional<String> buscarOptional, Optional<String> ordenarOptional) {

        StringBuilder queryBuilder = new StringBuilder("SELECT C FROM Categoria C");

        if (buscarOptional.isPresent()) {
            queryBuilder.append(" WHERE C.nombre LIKE :nombre");
        }
        if (ordenarOptional.isPresent()) {
            if ("asc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ORDER BY C.nombre ASC");
            } else if ("desc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ORDER BY C.nombre DESC");
            }
        }

        Query query = em.createQuery(queryBuilder.toString());

        if (buscarOptional.isPresent()) {
            query.setParameter("nombre", "%" + buscarOptional.get() + "%");
        }

        return query.getResultList();
    }


}
