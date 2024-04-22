package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar"})
    public List<Categoria> all() {
        log.info("Accediendo a todas las categorías");
        return this.categoriaService.all();
    }

    @GetMapping(value = {"","/"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam("buscar")Optional<String> buscarOptional, @RequestParam("ordenar")Optional<String> ordenarOptional,
                                   @RequestParam(value = "pagina", defaultValue = "0") int pagina, @RequestParam(value = "tamanio", defaultValue = "3") int tamanio) {

        log.info("Accediendo a todas las categorías",
            buscarOptional.orElse("Void"),
            ordenarOptional.orElse("Void"));

        //Se utiliza Map y no Page, porque no esta bien soportado
        Map<String, Object> responseAll = this.categoriaService.all(pagina, tamanio);

        List<Categoria> filtroCategoria = this.categoriaService.allByQueryFiltersStream(buscarOptional, ordenarOptional);

        responseAll.put("filtroCategoria", filtroCategoria);

        return ResponseEntity.ok(responseAll);
    }


    @PostMapping({"","/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        return this.categoriaService.save(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id) {
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        return this.categoriaService.replace(id, categoria);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
    }


}
