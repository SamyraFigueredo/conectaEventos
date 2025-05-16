package webservice.conectaEventos.Controller.Response;

import webservice.conectaEventos.Model.Atividade;
import webservice.conectaEventos.Model.Ministrante;
import webservice.conectaEventos.Model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class MinistranteResponse {

    private Long idMinistrante;
    private Usuario usuario;
    private String biografia;
    private Ministrante.TipoAtividade tipoAtividade;
    private List<Atividade> atividades;

    public MinistranteResponse(Long idMinistrante, Usuario usuario, String biografia, Ministrante.TipoAtividade tipoAtividade, List<Atividade> atividades) {
        this.idMinistrante = idMinistrante;
        this.usuario = usuario;
        this.biografia = biografia;
        this.tipoAtividade = tipoAtividade;
        this.atividades = atividades;
    }

    public static MinistranteResponse fromModel(Ministrante ministrante) {
        List<Atividade> atividades = ministrante.getAtividades().stream().collect(Collectors.toList());
        return new MinistranteResponse(
                ministrante.getIdMinistrante(),
                ministrante.getUsuario(),
                ministrante.getBiografia(),
                ministrante.getTipoAtividade(),
                atividades
        );
    }

    public Long getIdMinistrante() {
        return idMinistrante;
    }

    public void setIdMinistrante(Long idMinistrante) {
        this.idMinistrante = idMinistrante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Ministrante.TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(Ministrante.TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
}
