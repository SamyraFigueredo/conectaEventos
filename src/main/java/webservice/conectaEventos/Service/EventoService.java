package webservice.conectaEventos.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webservice.conectaEventos.Model.Evento;
import webservice.conectaEventos.Repository.EventoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com o ID: " + id));
    }

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {
        Evento eventoExistente = buscarPorId(id);

        eventoExistente.setNomeEvento(eventoAtualizado.getNomeEvento());
        eventoExistente.setDataInicioEvento(eventoAtualizado.getDataInicioEvento());
        eventoExistente.setDataFimEvento(eventoAtualizado.getDataFimEvento());
        eventoExistente.setLocalEvento(eventoAtualizado.getLocalEvento());
        eventoExistente.setDescricaoEvento(eventoAtualizado.getDescricaoEvento());
        eventoExistente.setIconeEvento(eventoAtualizado.getIconeEvento());

        return eventoRepository.save(eventoExistente);
    }

    public void deletarEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException("Evento não encontrado com o ID: " + id);
        }
        eventoRepository.deleteById(id);
    }
}
