package webservice.conectaEventos.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "ministrante")
@Data
@Entity
public class Ministrante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ministrante")
    private Long idMinistrante;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "biografia", length = 500)
    private String biografia;

    @Column(name = "tipo_atividade", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAtividade tipoAtividade;

    @OneToMany(mappedBy = "ministrante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades;

    // Enum para definir os tipos de atividades
    public enum TipoAtividade {
        PALESTRA,
        OFICINA,
        MINICURSO,
        OUTRO
    }
}
