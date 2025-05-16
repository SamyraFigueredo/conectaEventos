package webservice.conectaEventos.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "certificado")
@Data
@Entity
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCertificado;

    @Column(name = "data_emissao_certificado", nullable = false)
    private LocalDate dataEmissaoCertificado;

    @Column(name = "codigo_verificacao_certificado", nullable = false, unique = true)
    private String codigoVerificacaoCertificado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(name = "caminho_arquivo_certificado")
    private String caminhoArquivoCertificado;
}
