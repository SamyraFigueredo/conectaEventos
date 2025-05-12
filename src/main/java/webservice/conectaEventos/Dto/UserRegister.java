package webservice.conectaEventos.Dto;

import java.time.LocalDate;

public record UserRegister(
        String nome,
        String email,
        String senha,
        LocalDate dataNascimento
) { }
