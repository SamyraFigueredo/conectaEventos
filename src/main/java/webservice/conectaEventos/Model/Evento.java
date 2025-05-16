package webservice.conectaEventos.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "evento")
@Data
@Entity
public class Evento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    @Column(name = "nome_evento")
    private String nomeEvento;

    @Column(name = "data_inicio_evento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private ZonedDateTime dataInicioEvento;

    @Column(name = "data_fim_evento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private ZonedDateTime dataFimEvento;

    @Column(name = "local_evento")
    private String localEvento;

    @Column(name = "descricao_evento")
    private String descricaoEvento;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Atividade> atividades = new ArrayList<>();

    @Lob
    @Column(name = "icone_evento")
    private byte[] iconeEvento;

    @ManyToOne
    @JoinColumn(name = "id_organizador")
    private Usuario organizador;

}
