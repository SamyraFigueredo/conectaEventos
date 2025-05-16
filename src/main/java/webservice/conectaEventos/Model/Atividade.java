package webservice.conectaEventos.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import webservice.conectaEventos.Model.enums.TipoUsuario;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "atividade")
@Data
@Entity
public class Atividade implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade")
    private Long idAtividade;

    @Column(name = "nome_atividade")
    private String nomeAtividade;

    @Column(name = "horario_atividade")
    private LocalTime horarioAtividade;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    @JsonIgnore
    private Evento evento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;
}