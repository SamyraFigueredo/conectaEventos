package webservice.conectaEventos.Controller.Response;

import webservice.conectaEventos.Dto.UserDTO;

public record TokenResponse(String token, UserDTO user) {
}
