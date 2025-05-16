package webservice.conectaEventos.Controller.Response;

import webservice.conectaEventos.Model.Evento;
import java.time.ZonedDateTime;
import java.util.Base64;

public record EventoResponse(
        Long id,
        String nomeEvento,
        String descricaoEvento,
        ZonedDateTime dataInicioEvento,
        ZonedDateTime dataFimEvento,
        String localEvento,
        String iconeEventoBase64,
        Long organizadorId
) {
    public EventoResponse(Evento evento) {
        this(
                evento.getIdEvento(),
                evento.getNomeEvento(),
                evento.getDescricaoEvento(),
                evento.getDataInicioEvento(),
                evento.getDataFimEvento(),
                evento.getLocalEvento(),
                evento.getIconeEvento() != null ? Base64.getEncoder().encodeToString(evento.getIconeEvento()) : null,
                evento.getOrganizador() != null ? evento.getOrganizador().getIdUsuario() : null
        );
    }
}
