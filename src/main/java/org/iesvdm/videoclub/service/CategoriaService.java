package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaCustomRepository;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaCustomRepository categoriaCustomRepository;

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public List<Categoria> allByQueryFiltersStream(Optional<String> buscarOptional, Optional<String> ordenarOptional) {
        return this.categoriaCustomRepository.queryCustomCategoria(buscarOptional, ordenarOptional);

    }

    public Map<String, Object> all(int pagina, int tamanio){

        Pageable paginado = PageRequest.of(pagina,tamanio, Sort.by("id").ascending());

        Page<Categoria> pageAll = this.categoriaRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("categorias", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }


    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map( c -> (id.equals(categoria.getId())  ?
                                                            this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));

    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {
            this.categoriaRepository.delete(p);
            return p;
        }).orElseThrow(() -> new CategoriaNotFoundException(id));
    }


}
