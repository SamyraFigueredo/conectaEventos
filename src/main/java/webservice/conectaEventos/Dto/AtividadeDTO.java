package webservice.conectaEventos.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webservice.conectaEventos.Model.Usuario;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeDTO {

    private Long idAtividade;
    private String nomeAtividade;
    private LocalTime horarioAtividade;
    private Long idUsuario;
    private Long idEvento;
    private Usuario tipoUsuario;
}
