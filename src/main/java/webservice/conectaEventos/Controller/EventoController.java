package webservice.conectaEventos.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.conectaEventos.Controller.Request.EventoRequest;
import webservice.conectaEventos.Controller.Response.EventoResponse;
import webservice.conectaEventos.Model.Evento;
import webservice.conectaEventos.Model.Usuario;
import webservice.conectaEventos.Model.enums.TipoUsuario;
import webservice.conectaEventos.Repository.EventoRepository;
import webservice.conectaEventos.Repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<EventoResponse> criarEvento(@RequestBody @Valid EventoRequest request) {
        TipoUsuario tipoUsuario = request.tipoUsuario();

        Usuario organizador = usuarioRepository.findByTipoUsuario(tipoUsuario)
                .orElseThrow(() -> new RuntimeException("Organizador não encontrado"));

        Evento evento = request.toModel(organizador);
        eventoRepository.save(evento);

        // Retornar a resposta com os dados do evento criado
        EventoResponse eventoResponse = new EventoResponse(evento);
        return ResponseEntity.ok(eventoResponse);
    }

    @GetMapping
    public ResponseEntity<List<EventoResponse>> listarEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        List<EventoResponse> eventoResponses = eventos.stream()
                .map(EventoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> obterEvento(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        EventoResponse eventoResponse = new EventoResponse(evento);
        return ResponseEntity.ok(eventoResponse);
    }
}
