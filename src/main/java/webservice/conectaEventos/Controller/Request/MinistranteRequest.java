package webservice.conectaEventos.Controller.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import webservice.conectaEventos.Model.Ministrante;
import webservice.conectaEventos.Model.Usuario;

public record MinistranteRequest(
        @NotNull(message = "O id do usuário não pode ser nulo")
        Long usuarioId,

        @Size(max = 500, message = "A biografia não pode ter mais de 500 caracteres")
        String biografia,

        @NotNull(message = "O tipo de atividade não pode ser nulo")
        Ministrante.TipoAtividade tipoAtividade
) {

    public Ministrante toModel(Usuario usuario) {
        Ministrante ministrante = new Ministrante();
        ministrante.setUsuario(usuario); // Associa o usuário
        ministrante.setBiografia(biografia); // Define a biografia
        ministrante.setTipoAtividade(tipoAtividade); // Define o tipo de atividade
        return ministrante;
    }
}
