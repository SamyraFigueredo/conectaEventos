package webservice.conectaEventos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.conectaEventos.Model.Certificado;
import webservice.conectaEventos.Service.CertificadoService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/certificados")
@CrossOrigin(origins = "*")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;

    @GetMapping
    public List<Certificado> listarTodos() {
        return certificadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificado> buscarPorId(@PathVariable Long id) {
        return certificadoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Certificado> buscarPorCodigo(@PathVariable String codigo) {
        return certificadoService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Certificado> criar(@RequestBody Certificado certificado) {
        Certificado novo = certificadoService.salvar(certificado);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificado> atualizar(@PathVariable Long id, @RequestBody Certificado certificado) {
        try {
            Certificado atualizado = certificadoService.atualizar(id, certificado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (certificadoService.buscarPorId(id).isPresent()) {
            certificadoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadCertificado(@PathVariable Long id) {
        try {
            Path caminho = certificadoService.obterCaminhoArquivoCertificado(id);
            if (!Files.exists(caminho) || !Files.isReadable(caminho)) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(caminho.toUri());
            String contentType = Files.probeContentType(caminho);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + caminho.getFileName() + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
