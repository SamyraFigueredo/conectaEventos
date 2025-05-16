package webservice.conectaEventos.Controller.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import webservice.conectaEventos.Model.enums.TipoUsuario;

import java.time.ZonedDateTime;

public record EventoRequest(
        @NotNull(message = "O organizador não pode ser nulo")
        TipoUsuario tipoUsuario,

        @NotNull(message = "O nome do evento não pode ser nulo")
        @Size(min = 3, max = 100, message = "O nome do evento deve ter entre 3 e 100 caracteres")
        String nomeEvento,

        @NotNull(message = "A descrição do evento não pode ser nula")
        @Size(max = 1500, message = "A descrição não pode ultrapassar 1500 caracteres")
        String descricaoEvento,

        @NotNull(message = "A data inicial não pode ser nula")
        ZonedDateTime dataInicioEvento,

        @NotNull(message = "A data final não pode ser nula")
        ZonedDateTime dataFimEvento,

        @NotNull(message = "O local do evento não pode ser nulo")
        @Size(min = 3, max = 255, message = "O local do evento deve ter entre 3 e 255 caracteres")
        String localEvento
) {
    public Evento toModel(Usuario organizador) {
        Evento evento = new Evento();
        evento.setOrganizador(organizador);
        evento.setNomeEvento(nomeEvento);
        evento.setDescricaoEvento(descricaoEvento);
        evento.setDataInicioEvento(dataInicioEvento);
        evento.setDataFimEvento(dataFimEvento);
        evento.setLocalEvento(localEvento);

        return evento;
    }
}
