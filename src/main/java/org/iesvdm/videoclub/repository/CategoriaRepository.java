package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepositoryImplementation<Categoria, Long> {

    //Estrategia por method query para consultas sencillas, para extender usar JPQL
//    public List<Categoria> findByNameContainingIgnoreCase(String name);
}
