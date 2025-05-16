package webservice.conectaEventos.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.conectaEventos.Model.Ministrante;
import webservice.conectaEventos.Service.MinistranteService;

import java.util.List;

@RestController
@RequestMapping("/ministrantes")
@CrossOrigin(origins = "*")
public class MinistranteController {

    @Autowired
    private MinistranteService ministranteService;

    @GetMapping
    public List<Ministrante> listarTodos() {
        return ministranteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ministrante> buscarPorId(@PathVariable Long id) {
        return ministranteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ministrante> criar(@RequestBody Ministrante ministrante) {
        Ministrante novo = ministranteService.salvar(ministrante);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ministrante> atualizar(@PathVariable Long id, @RequestBody Ministrante ministrante) {
        try {
            Ministrante atualizado = ministranteService.atualizar(id, ministrante);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ministranteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
