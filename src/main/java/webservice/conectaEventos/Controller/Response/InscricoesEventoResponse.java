package webservice.conectaEventos.Controller.Response;

import webservice.conectaEventos.Dto.UserDTO;
import java.time.ZonedDateTime;

public record InscricoesEventoResponse(Long idInscricao, UserDTO participante, String statusInscricao, ZonedDateTime dataInscricao) {
}
